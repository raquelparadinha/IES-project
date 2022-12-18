import json
from random import randint
from time import sleep
from numpy.random import normal

from pymongo import MongoClient

from classes import Inmate, Area, Sensor, Workstation

HEALTHFILE = 'data/health.json'

RIOT_CHANCE = 100 # %

class Simulator():
    def __init__(self, host, port, username='user1', password='user1'):
        def loadfile(filename):
            try:
                with open(filename, 'r') as f:
                    data = json.load(f)
            except:
                print('Error opening {} file. Exiting...'.format(filename))
                exit(1)
            
            return data

        self.host = host
        self.port = port
        self.username = username
        self.password = password

        try:
            connstr = 'mongodb://' + self.host + ':' + str(self.port) + '/'
            self.client = MongoClient(connstr)
        except:
            print('Connection to mongo container failed. Exiting.')
            exit(1)

        self.db = self.client['lockedin']
        
        areadata = [a for a in self.db['area'].find()]
        sensordata = [s for s in self.db['moveSensor'].find()]
        workstationdata = [w for w in self.db['workStation'].find()]
        inmatedata = [i for i in self.db['inmate'].find()]

        healthdata = loadfile(HEALTHFILE)

        # init areas
        self.areas = []
        for i in areadata:
            id = i['_id']
            name = i['name']
            capacity = i['capacity']
            access = i['access']
            self.areas.append(Area(id, name, access, capacity))

        # init sensors
        self.sensors = []
        for i in sensordata:
            id = i['_id']
            entry = [l for l in self.areas if l.id == i['entryAreaId']][0]
            out = [l for l in self.areas if l.id == i['exitAreaId']][0]
            active = i['active']
            self.sensors.append(Sensor(id, entry, out, active))

        # init health values
        self.healthvalues = {}
        for i in healthdata:
            name = i['name']
            genvals = i['genvals']
            self.healthvalues[name] = genvals

        # init workstations
        self.workstations = []
        for i in workstationdata:
            id = i['_id']
            name = i['name']
            self.workstations.append(Workstation(id, name))

        # init inamtes
        self.inmates = []
        possibleblocks = [l for l in self.areas if l.id in [7, 8]]
        for i in inmatedata:
            id = i['_id']
            startarea = possibleblocks[randint(1, len(possibleblocks)) - 1]
            self.inmates.append(Inmate(id, startarea))

    # constric/expand generated data according to msg received
    def processmsg(self, msg):
        jmsg = json.loads(msg)
        print('recv', jmsg)
        msgtype = jmsg['type']

        if msgtype in ['lock', 'unlock']:
            sensorid = jmsg['sensorid']
            sensor = [s for s in self.sensors if s.id == sensorid][0]
            sensor.active = False if msgtype == 'lock' else True

        elif msgtype in ['newinmate', 'delinmate']:
            inmateid = jmsg['inmateid']
            if msgtype == 'newinmate':
                areaid = jmsg['areaid']
                area = [a for a in self.areas if a.id == areaid][0]
                self.inmates.append(Inmate(inmateid, area))
            elif msgtype == 'delinmate':
                inmate = [i for i in self.inmates if i.id == inmateid][0]
                self.inmates.remove(inmate)

    # main loop method
    def run(self):
        messages = []

        inmate, sensor = self.moveInmate()
        if inmate != None and sensor != None:
            msg = {'type': 'sensor'}
            msg['inmateid'] = inmate.id
            msg['sensorid'] = sensor.id
            messages.append(msg)

            if self.tryRiot(sensor.exit):
                msg = {'type': 'riot'}
                msg['areaid'] = sensor.exit.id
                messages.append(msg)

            if sensor.entry.name == 'jobwing':
                ws, wq = self.makeWork()
                msg = {'type': 'work'}
                msg['inmateid'] = inmate.id
                msg['workstationid'] = ws.id
                msg['workquota'] = wq
                messages.append(msg)

            elif sensor.exit.name == 'infirmary':
                hc = self.makeHealthcheck()
                msg = {'type': 'healthcheck'}
                msg['inmateid'] = inmate.id
                msg['healthcheck'] = hc
                messages.append(msg)

        return messages

    # random generation methods
    def moveInmate(self):
        inmateidx = randint(1, len(self.inmates)) - 1
        inmate = self.inmates[inmateidx]

        possiblesensors = [s for s in self.sensors if s.active and s.entry == inmate.area]
        if possiblesensors == []:
            print(inmate.area)
            return None, None

        sensoridx = randint(1, len(possiblesensors)) - 1
        sensor = possiblesensors[sensoridx]

        inmate.area = sensor.exit
        return inmate, sensor

    def tryRiot(self, area):
        count = len([inmate for inmate in self.inmates if inmate.area == area])
        if count > area.capacity:
            if randint(0, 100) >= RIOT_CHANCE:
                return False
            return True

    def makeHealthcheck(self):
        def genValue(key):
            val = 0
            minimum = self.healthvalues[key]['mean'] - self.healthvalues[key]['range']
            maximum = self.healthvalues[key]['mean'] + self.healthvalues[key]['range']

            while not minimum < val < maximum:
                val = normal(self.healthvalues[key]['mean'], self.healthvalues[key]['range'], 1)[0]
            return val


        healthcheck = {key: int(genValue(key))  for key in self.healthvalues.keys()}
        healthcheck = {key: healthcheck[key] if healthcheck[key] > 0 else 0 for key in healthcheck.keys()}
        return healthcheck

    def makeWork(self):
        workstationidx = randint(1, len(self.workstations)) - 1
        workstation = self.workstations[workstationidx]
        workquota = randint(0, 100)
        
        return workstation, workquota
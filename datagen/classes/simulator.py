import json
from random import randint
from time import sleep

from classes import Area, Inmate, Sensor, Workstation
from numpy.random import normal
from pymongo import MongoClient

HEALTHFILE = 'data/health.json'

RIOT_CHANCE = 10 # %

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

        self.riotcounter = 0
        self.lastriot = None

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
        for i in inmatedata:
            id = i['_id']
            startarea = [a for a in self.areas if i['areaId'] == a.id][0]
            solitary = i['solitary']
            self.inmates.append(Inmate(id, startarea, solitary))

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

        elif msgtype in ['insolitary', 'outsolitary']:
            inmateid = jmsg['inmateid']
            inmate = [i for i in self.inmates if i.id == inmateid][0]
            solitary = [a for a in self.areas if a.name == 'solitary'][0]
            if msgtype == 'insolitary':
                inmate.solitary = True
                inmate.area = solitary
            elif msgtype == 'outsolitary':
                inmate.solitary = False

    # main loop method
    def run(self):
        messages = []

        inmate, sensor = self.moveInmate()
        if inmate != None and sensor != None:
            msg = {'type': 'sensor'}
            msg['inmateid'] = inmate.id
            msg['sensorid'] = sensor.id
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
        
        area, bool = self.tryRiot()
        if bool:
            msg = {'type': 'riot'}
            msg['areaid'] = area.id
            messages.append(msg)

        return messages

    # random generation methods
    def moveInmate(self):
        inmates = [i for i in self.inmates if not i.solitary]
        inmateidx = randint(1, len(inmates)) - 1
        inmate = inmates[inmateidx]

        possiblesensors = [s for s in self.sensors if s.active and s.entry == inmate.area]
        if inmate.motivate():
            logareas = ['infirmary', 'jobwing']
            possiblesensors = [s for s in possiblesensors if s.active and s.exit.name in logareas]

        if possiblesensors == []:
            print(inmate.area)
            return None, None

        sensoridx = randint(1, len(possiblesensors)) - 1
        sensor = possiblesensors[sensoridx]

        if len([i for i in self.inmates if i.area == sensor.exit]) < sensor.exit.capacity:
            inmate.area = sensor.exit
            return inmate, sensor
            
        area = sensor.exit
        areainmates = [i for i in self.inmates if i.area == area and not i.solitary]
        inmate = areainmates[randint(1, len(areainmates)) - 1]
        possiblesensors = [s for s in self.sensors if s.active and s.entry == area and len([i for i in self.inmates if i.area == s.exit]) < s.exit.capacity]
        sensor = possiblesensors[randint(1, len(possiblesensors)) - 1]
        return inmate, sensor

    def tryRiot(self):
        area = [a for a in self.areas if a.name != 'solitary'][randint(1, len(self.areas)) - 1]
        if self.lastriot == area:
            self.riotcounter += 1
            if self.riotcounter <= 50:
                return None, False

        count = len([inmate for inmate in self.inmates if inmate.area == area])
        if count > (area.capacity * 0.85):
            if randint(0, 100) >= RIOT_CHANCE:
                return None, False
            
            self.riotcounter = 0
            self.lastriot = area
            return area, True
        return None, False

    def makeHealthcheck(self):
        def genValue(key):
            val = 0
            minimum = self.healthvalues[key]['mean'] - self.healthvalues[key]['range']
            maximum = self.healthvalues[key]['mean'] + self.healthvalues[key]['range']

            while not minimum < val < maximum:
                val = normal(self.healthvalues[key]['mean'], self.healthvalues[key]['range'], 1)[0]
            return val

        healthcheck = {key: int(genValue(key)) for key in self.healthvalues.keys()}
        healthcheck['toxic_screen'] = 0 if healthcheck['toxic_screen'] < 70 else 1 if healthcheck['toxic_screen'] < 90 else 2
        return healthcheck

    def makeWork(self):
        workstationidx = randint(1, len(self.workstations)) - 1
        workstation = self.workstations[workstationidx]
        workquota = randint(0, 100)
        
        return workstation, workquota
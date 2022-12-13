import json
from random import randint
from time import sleep

from classes import Inmate, Area, Sensor, Workstation
from numpy.random import normal

RIOT_CHANCE = 4 # %

class Simulator():
    def __init__(self, areasfile, sensorfile, workstationsfile, inmatesfile, healthfile):
        try: 
            with open(areasfile, 'r') as f:
                layoutf = json.load(f)
        except:
            print('Error opening areas file. Exiting...')
            exit(1)

        try:
            with open(sensorfile, 'r') as f:
                sensorf = json.load(f)
        except:
            print('Error opening sensors file. Exiting...')
            exit(1)

        try:
            with open(healthfile, 'r') as f:
                healthf = json.load(f)
        except:
            print('Error opening health file. Exiting...')
            exit(1)

        try:
            with open(workstationsfile, 'r') as f:
                workstationsf = json.load(f)
        except:
            print('Error opening workstations file. Exiting...')
            exit(1)

        try:
            with open(inmatesfile, 'r') as f:
                inmatesf = json.load(f)
        except:
            print('Error opening inmates file. Exiting...')

            exit(1)

        # print(layoutf)
        # print(sensorf)
        # print(workstationsf)
        # print(healthf)
        # print(inmatesf)

        # init areas
        self.areas = []
        for i in layoutf:
            id = i['_id']
            name = i['name']
            capacity = i['capacity']
            access = i['access']
            self.areas.append(Area(id, name, access, capacity))

        # init sensors
        self.sensors = []
        for i in sensorf:
            id = i['_id']
            entry = [l for l in self.areas if l.id == i['entryAreaId']][0]
            out = [l for l in self.areas if l.id == i['exitAreaId']][0]
            active = i['active']
            self.sensors.append(Sensor(id, entry, out, active))

        # init health values
        self.healthvalues = {}
        for i in healthf:
            name = i['name']
            genvals = i['genvals']
            self.healthvalues[name] = genvals

        # init workstations
        self.workstations = []
        for i in workstationsf:
            id = i['_id']
            name = i['name']
            listings = i['capacity']
            self.workstations.append(Workstation(id, name, listings))

        # init inamtes
        self.inmates = []
        possibleblocks = [l for l in self.areas if l.id in [7, 8]]
        for i in inmatesf:
            id = i['_id']
            startarea = possibleblocks[randint(1, len(possibleblocks)) - 1]
            self.inmates.append(Inmate(id, startarea))

        # print(self.areas)
        # print(self.sensors)
        # print(self.healthvalues)
        # print(self.workstations)
        # print(self.inmates)

        # for l in self.areas: print(l)
        # for s in self.sensors: print(s)
        # for hv in self.healthvalues: print(hv)
        # for w in self.workstations: print(w)
        # for i in self.inmates: print(i)

    def moveInmate(self):
        inmateidx = randint(1, len(self.inmates)) - 1
        inmate = self.inmates[inmateidx]

        possiblesensors = [s for s in self.sensors if s.active and s.entry == inmate.area]
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
        healthcheck = {key: normal(self.healthvalues[key]['mean'], self.healthvalues[key]['range'], 1)[0] for key in self.healthvalues.keys()}
        healthcheck = {key: int(healthcheck[key]) if healthcheck[key] > 0 else 0 for key in healthcheck.keys()}

        return healthcheck

    def makeWork(self):
        workstationidx = randint(1, len(self.workstations)) - 1
        workstation = self.workstations[workstationidx]
        workquota = randint(0, 100)
        
        return workstation, workquota

    def run(self):
        messages = []

        inmate, sensor = self.moveInmate()
        msg = {'type': 'sensor'}
        msg['inmateid'] = inmate.id
        msg['sensorid'] = sensor.id
        messages.append(msg)

        if self.tryRiot(sensor.exit):
            msg = {'type': 'riot'}
            msg['areaid'] = sensor.exit.id
            messages.append(msg)

        if sensor.exit.name == 'jobwing':
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

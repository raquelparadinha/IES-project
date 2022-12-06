import json
from random import randint
from time import sleep

from classes import Inmate, Location, Sensor, Workstation
from numpy.random import normal

max_n = 1

class Simulator():
    def __init__(self, layoutfile, sensorfile, healthfile, workstationsfile, inmatesfile):
        try: 
            with open(layoutfile, 'r') as f:
                layoutf = json.load(f)
        except:
            print('Error opening layout file. Exiting...')
            exit(1)

        try:
            with open(sensorfile, 'r') as f:
                sensorf = json.load(f)
        except:
            print('Error opening sensor file. Exiting...')
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

        # init locations
        self.locations = []
        for i in layoutf:
            id = i['id']
            name = i['name']
            access = i['access']
            connections = i['connections']
            self.locations.append(Location(id, name, access, connections))

        # init sensors
        self.sensors = []
        for i in sensorf:
            id = i['id']
            entry = [l for l in self.locations if i['entry'] == l.id][0]
            out = [l for l in self.locations if i['exit'] == l.id][0]
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
            id = i['id']
            name = i['name']
            listings = i['listings']
            workvalues = i['workvalues']
            self.workstations.append(Workstation(id, name, listings, workvalues["minimum"], workvalues["maximum"], workvalues["expected"]))

        # init inamtes
        self.inmates = []
        possibleblocks = [l for l in self.locations if l.id in [7, 8]]
        for i in inmatesf:
            id = i['id']
            startlocation = possibleblocks[randint(1, len(possibleblocks)) - 1]
            self.inmates.append(Inmate(id, startlocation))

        #for l in self.locations: print(l)
        #for s in self.sensors: print(s)
        #for hv in self.healthvalues: print(hv)
        #for w in self.workstations: print(w)
        #for i in self.inmates: print(i)

    def moveInmate(self):
        inmateidx = randint(1, len(self.inmates)) - 1
        inmate = self.inmates[inmateidx]

        possiblesensors = [s for s in self.sensors if s.active and s.entry == inmate.location]
        sensoridx = randint(1, len(possiblesensors)) - 1
        sensor = possiblesensors[sensoridx]

        inmate.location = sensor.exit
        return inmate, sensor

    def makeHealthcheck(self):
        healthcheck = {key: normal(self.healthvalues[key]['mean'], self.healthvalues[key]['range'], 1)[0] for key in self.healthvalues.keys()}
        healthcheck = {key: int(healthcheck[key]) if healthcheck[key] > 0 else 0 for key in healthcheck.keys()}

        return healthcheck

    def workstationApply(self):
        available = [w for w in self.workstations if len(w.workers) < w.listings]
        workstationidx = randint(1, len(available)) - 1
        
        return self.workstations[workstationidx]

    def workstationWork(self, inmate):
        workstation = inmate.workstation
        workquota = randint(workstation.minimum, workstation.maximum)
        
        return workquota
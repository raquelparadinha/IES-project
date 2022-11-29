from random import randint
from numpy.random import normal
from time import sleep

from classes import Inmate, Location, Sensor, Workstation

max_n = 1

class Simulator():
    def __init__(self, layoutfile, healthfile, workstationsfile):
        try: 
            with open(layoutfile, 'r') as f:
                layoutf = f.readlines()
        except:
            print('Error opening layout file. Exiting...')
            exit(1)

        try:
            with open(healthfile, 'r') as f:
                healthf = f.readlines()
        except:
            print('Error opening health file. Exiting...')
            exit(1)

        try:
            with open(workstationsfile, 'r') as f:
                workstationsf = f.readlines()
        except:
            print('Error opening workstations file. Exiting...')
            exit(1)

        # init locations
        self.locations = []
        for line in layoutf:
            d = line.split(' - ')
            name = d[0]
            access = True if d[1] == 'true' else False
            connections = {l.rstrip() for l in d[2].split(' ')}        
            self.locations.append(Location(name, access, connections))

        for l in self.locations: print(l)

        # init sensors
        self.sensors = []
        for l in self.locations:
            if l.access:
                for c in [spot for spot in self.locations if spot.name in l.connections]:
                    if c.access:
                        self.sensors.append(Sensor(l, c))

        for s in self.sensors: print(s)

        # init health values
        self.healthvalues = {}
        for line in healthf:
            d = line.split(' - ')
            key = d[0]
            values = [int(v.rstrip()) for v in d[1].split(' ')]
            valuesdict = {'mean': values[0], 'range': values[1]}
            self.healthvalues[key] = valuesdict

        print(self.healthvalues)

        # init workstations
        self.workstations = []
        for line in workstationsf:
            d = line.split(' - ')
            name = d[0]
            listings = int(d[1])
            minimum = int(d[2])
            maximum = int(d[3])
            expected = int(d[4])
            self.workstations.append(Workstation(name, listings, minimum, maximum, expected))

        for w in self.workstations: print(w)

        # init inamtes
        self.inmates = []
        for i in range(max_n):
            cellblocks = [l for l in self.locations if l.name.startswith('Cell')]
            block = randint(0, len(cellblocks) - 1)
            self.inmates.append(Inmate(cellblocks[block]))

        for i in self.inmates: print(i)

    def moveInmate(self):
        inmateidx = randint(1, len(self.inmates)) - 1

        possiblesensors = [s for s in self.sensors if s.entry == self.inmates[inmateidx].location]
        sensoridx = randint(1, len(possiblesensors)) - 1

        self.inmates[inmateidx].location = possiblesensors[sensoridx].exit
        return self.inmates[inmateidx], possiblesensors[sensoridx]

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
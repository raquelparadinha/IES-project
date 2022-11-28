from random import randint
from time import sleep

max_n = 500

class Inmate():
    cntr = 0
    instances = []

    def __init__(self, startlocation):
        Inmate.cntr += 1
        self.id = Inmate.cntr

        self.location = startlocation

        Inmate.instances.append(self)

    def __str__(self):
        return 'Inmate {} (is at {})'.format(self.id, self.location)
 
class Location():
    cntr = 0
    instances = []

    def __init__(self, name: str, access: bool, connections: set):
        Location.cntr += 1
        self.id = Location.cntr

        self.name = name
        self.access = access
        self.connections = connections

        Location.instances.append(self)

    def __str__(self):
        return '{}'.format(self.name)

class Sensor():
    cntr = 0
    instances = []

    def __init__(self, entry, exit):
        Sensor.cntr += 1
        self.id = Sensor.cntr

        self.entry = entry
        self.exit = exit

        Sensor.instances.append(self)

    def __str__(self):
        return 'Sensor ({}, {})'.format(self.entry.name, self.exit.name)


if __name__ == '__main__':
    with open('prison-layout.txt', 'r') as f:
        layout = f.readlines()

    # init locations
    for line in layout:
        d = line.split(' - ')
        name = d[0]
        access = True if d[1] == 'true' else False
        connections = {l.rstrip() for l in d[2].split(' ')}        
        l = Location(name, access, connections)


    # init inamtes
    for i in range(max_n):
        cellblocks = [l for l in Location.instances if l.name.startswith('Cell')]
        block = randint(0, 1)
        p = Inmate(cellblocks[block])

    # init sensors
    for l in Location.instances:
        if l.access:
            for c in [spot for spot in Location.instances if spot.name in l.connections]:
                if c.access:
                    s = Sensor(l, c)

    for i in range(20):
        inmate = randint(1, max_n)

        sensors = [s for s in Sensor.instances if s.entry == Inmate.instances[inmate - 1].location]
        sensor = randint(1, len(sensors))

        print('{} has passed through {}'.format(Inmate.instances[inmate - 1], sensors[sensor - 1]))
        Inmate.instances[inmate - 1].location = sensors[sensor - 1].exit
        sleep(1)

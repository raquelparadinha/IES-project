import json

from classes import *

with open('data/layout.json', 'r') as f:
    layoutf = json.load(f)

locations = []
for i in layoutf:
    id = i['id']
    name = i['name']
    access = i['access']
    connections = i['connections']
    locations.append(Location(id, name, access, connections))

sensors = []
idcnt = 0
for l in locations:
    for c in [loc for loc in locations if loc.id in l.connections]:
        idcnt += 1
        sensors.append(Sensor(idcnt, l, c, l.access and c.access))

for l in locations: print(l)
for s in sensors: print(s.id)

with open('data/sensors.json', 'w') as f:
    f.write('[')
    for s in sensors:
        dict = {'id': s.id, 'entry': s.entry.id, 'exit': s.exit.id, 'active': s.active}
        f.write(json.dumps(dict))
        f.write(', \n')
    f.write(']')

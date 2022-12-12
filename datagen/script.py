import json

from classes import *

def main():
    with open('mongodb/seeddata/moveSensors.json', 'r') as f:
        sensordata = json.load(f)

    with open('mongodb/seeddata/areas.json', 'r') as f:
        areadata = json.load(f)

    locations = []
    for i in areadata:
        id = i['_id']
        name = i['name']
        capacity = i['capacity']
        access = i['access']
        locations.append(Location(id, name, access, capacity))
    
    sensors = []
    for i in sensordata:
        id = i['_id']
        entry = i['entryAreaId']
        out = i['exitAreaId']
        entryArea = [l for l in locations if l.id == entry][0]
        outArea = [l for l in locations if l.id == out][0]
        active = entryArea.access and outArea.access
        sensors.append(Sensor(id, entryArea, outArea, active))

    jsonsensors = [{"_id": s.id, "entryAreaId": s.entry.id, "exitAreaId": s.exit.id, "active": s.active, "moveLogIds": []} for s in sensors]

    with open('moveSensors.json', 'w') as f:
        for s in jsonsensors:
            jsonstr = s.__str__().replace('\'', '"').replace("False", "false").replace("True", "true")
            print(jsonstr)
            f.write(jsonstr)
            f.write(',')

if __name__ == '__main__':
    main()
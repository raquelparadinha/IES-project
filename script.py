import json

with open('mongodb/seeddata/inmates.json', 'r') as f:
    jsondata = json.load(f)

for inmate in jsondata:
    if 'workstationId' in inmate:
        del inmate['workstationId']

with open('inmates.json', 'w') as f:
    json.dump(jsondata, f)
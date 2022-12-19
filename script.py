import json
from random import randint

with open('mongodb/seeddata/inmates.json', 'r') as f:
    data = json.load(f)


with open('innmates.json', 'w') as f:
    for i in data:
        i.pop('areaid')
        i['areaId'] = randint(7, 8)
        f.write(json.dumps(i))
        f.write(',\n')
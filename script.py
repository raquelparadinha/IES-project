import json

from random import randint

with open('mongodb/seeddata/inmates.json', 'r') as f:
    data = json.load(f)


with open('innmates.json', 'w') as f:
    for i in data:
        i.pop('location')
        f.write(json.dumps(i))
        f.write(',\n')
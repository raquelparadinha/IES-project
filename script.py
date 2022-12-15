import json

from random import randint

with open('mongodb/seeddata/inmates.json', 'r') as f:
    data = json.load(f)


with open('innmates.json', 'w') as f:
    for i in data:
        i['location'] = randint(6, 7)
        f.write(json.dumps(i))
        f.write(',\n')
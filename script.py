import json

with open('mongodb/seeddata/moveSensorLogs.json', 'r') as f:
    data = json.load(f)



with open('moveSensorLogs.json', 'w') as f:
    for i, d in enumerate(data):
        d['_id'] = i + 1
        f.write(json.dumps(d))
        f.write(',\n')
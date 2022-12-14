var fs = require('fs');

db = db.getSiblingDB('lockedin');


db.createUser(
    {
        user:'user1',
        pwd:'user1',
        roles:[
            {
                role:'readWrite',
                db:'lockedin'
            }
        ]
    }
);

db.createCollection('prison');
var data = fs.readFileSync('./seeddata/prisons.json')
var docs = JSON.parse(data.toString())
db.prison.insertMany(docs)
console.log('\nCHECKPOINT -> prisons done')

db.createCollection('warden');
var data = fs.readFileSync('./seeddata/wardens.json')
var docs = JSON.parse(data.toString())
db.warden.insertMany(docs)
console.log('\nCHECKPOINT -> wardens done')

db.createCollection('guard');
var data = fs.readFileSync('./seeddata/guards.json')
var docs = JSON.parse(data.toString())
db.guard.insertMany(docs)
console.log('\nCHECKPOINT -> guards done')

db.createCollection('area');
var data = fs.readFileSync('./seeddata/areas.json')
var docs = JSON.parse(data.toString())
db.area.insertMany(docs)
console.log('\nCHECKPOINT -> areas done')

db.createCollection('moveSensor');
var data = fs.readFileSync('./seeddata/moveSensors.json')
var docs = JSON.parse(data.toString())
db.moveSensor.insertMany(docs)
console.log('\nCHECKPOINT -> moveSensors done')

db.createCollection('workStation');
var data = fs.readFileSync('./seeddata/workstations.json')
var docs = JSON.parse(data.toString())
db.workStation.insertMany(docs)
console.log('\nCHECKPOINT -> workstations done')

db.createCollection('inmate');
var data = fs.readFileSync('./seeddata/inmates.json')
var docs = JSON.parse(data.toString())
db.inmate.insertMany(docs)
console.log('\nCHECKPOINT -> inmates done')

db.createCollection('healthLog', { capped: true, size: 1000000, max: 5000 });
var data = fs.readFileSync('./seeddata/healthLogs.json')
var docs = JSON.parse(data.toString())
db.healthLog.insertMany(docs)
console.log('\nCHECKPOINT -> healthLogs done')

db.createCollection('moveSensorLog', { capped: true, size: 2000000, max: 10000 });
var data = fs.readFileSync('./seeddata/moveSensorLogs.json')
var docs = JSON.parse(data.toString())
db.moveSensorLog.insertMany(docs)
console.log('\nCHECKPOINT -> moveSensorLogs done')


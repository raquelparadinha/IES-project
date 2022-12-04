db = new Mongo().getDB("lockedin");

db.createCollection("movesensordata", { capped: true, size: 5242880, max: 5000});
db.createCollection("healthchecks", { capped: true, size: 5242880, max: 5000});
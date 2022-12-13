# Data Generation Module
This module generates random data according to the application context, in order to simulate the prison environment.

****
## Message Protocol
All messages are JSON encoded, and sent through a message broker, 

### Messages Sent
There are 4 types of data being generated, and therefore 4 types of messages being sent to the queue.

* **Sensor Message**  
This message simulates inmate movement inside the facility.  
It specifies who moved, through which sensor.  
Example: `{"type": "sensor", "inmateid": 1348, sensorid: 24}`

* **Riot Message**  
This messages simulates a prison riot.  
It specifies in which location is a riot happening.  
Example: `{"type": "riot", "locationid": 6}`

* **Healthcheck Message**  
This message simulates a prison healthcheck.  
It specifies who the healthcheck belongs to, and it's values.  
Example: `{"type": "healthcheck", "inmateid": 1295, "healthcheck": healthcheck}`

* **Work Message**  
This message simulates an inmate working inside the prison.
It specifies who worked, where they worked, and their work performance (%).  
Example: `{"type": "work", "inmateid": 1410, "workstationid": 2, "workquota": 67}`

### Messages Received

* **Sensor Management**  
This type of messages serves to lock and unlock sensors, making it so no inmates can go through them, effectively restricting the data being generated.  
Example: `{"type": "lock", "sensorid": 5}`

* **Inmate Management**  
This type of messages serves to update the data regarding inmates, so that the data generation module takes into account all existing inmates, and none more.  
Example: `{"type": "newinmate", "inmateid": 1143}`
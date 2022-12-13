# Data Generation Module
This module generates random data according to the application context, in order to simulate the prison environment.

****
## Message Protocol
All messages are JSON encoded, and sent through a message broker, 

### Messages Sent
There are 4 types of data being generated, and therefore 4 types of messages being sent to the queue.

* **Move Message**  
This message simulates inmate movement inside the facility.  
It specifies who moved, through which sensor.  
Example: {"type": "move", "inmateid": 1348, sensorid: 24}

* **Riot Message**  
This messages simulates a prison riot.  
It specifies in which location is a riot happening.  
Example: {"type": "riot", "locationid": 6}

* **Healthcheck Message**  
This message simulates a prison healthcheck.  
It specifies who the healthcheck belongs to, and it's values.  
Example: {"type": "healthcheck", "inmateid": 1295, "healthcheck": healthcheck}

* **Work Message**  
This message simulates an inmate working inside the prison.
It specifies who worked, where they worked, and their work performance (%).  
Example: {"type": "work", "inmateid": 1410, "workstationid": 2, "workquota": 67}

### Messages Received
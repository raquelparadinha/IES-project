from time import sleep

from classes import *
from comms import *

areasfile = 'mongodb/seeddata/areas.json'
sensorfile = 'mongodb/seeddata/moveSensors.json'
healthfile = 'datagen/data/health.json'
workstationsfile = 'mongodb/seeddata/workstations.json'
inmatesfile = 'mongodb/seeddata/inmates.json'

def main():
    sim = Simulator(areasfile, sensorfile, workstationsfile, inmatesfile, healthfile)
    sender = Sender()
    # receiver = Receiver()

    sender.conninit()
    
    while(True):
        inmate, sensor = sim.moveInmate()
        sender.publish('sensor', inmate.id, sensor.id)

        if sensor.exit.id == 4:
            if inmate.workstation == None:
                ws = sim.workstationApply() 
                sender.publish('apply', inmate.id, ws.id)

            else:
                wq = sim.workstationWork(inmate)
                sender.publish('work', inmate.id, wq)

        elif sensor.exit.id == 6:
            hc = sim.makeHealthcheck()
            sender.publish('health', inmate.id, hc)

        sleep(1)

    sender.connclose()

if __name__ == '__main__':
    main()
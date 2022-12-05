from time import sleep

from classes import *
from comms import *

layoutfile = 'data/layout.json'
sensorfile = 'data/sensors.json'
healthfile = 'data/health.json'
workstationsfile = 'data/workstations.json'
inmatesfile = 'data/inmates_single.json'

def main():
    sim = Simulator(layoutfile, sensorfile, healthfile, workstationsfile, inmatesfile)
    sender = Sender()
    receiver = Receiver()

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
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
        messages = sim.run()
        for m in messages:
            sender.publish(m)

        sleep(1)

    sender.connclose()

if __name__ == '__main__':
    main()
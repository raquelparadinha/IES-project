from time import sleep

from threading import Thread

from classes import *
from comms import *

areasfile = 'mongodb/seeddata/areas.json'
sensorfile = 'mongodb/seeddata/moveSensors.json'
healthfile = 'datagen/data/health.json'
workstationsfile = 'mongodb/seeddata/workstations.json'
inmatesfile = 'mongodb/seeddata/inmates.json'

def main():
    sim = Simulator(areasfile, sensorfile, workstationsfile, inmatesfile, healthfile)
    receiver = Receiver()
    sender = Sender()

    consumer_thread = Thread(target=receiver.recv)
    consumer_thread.start()
    
    control = 0
    while(control < 10):
        messages = sim.run()
        for m in messages:
            sender.send(m)

        sleep(1)
        control += 1
    
    consumer_thread.join()
    receiver.__exit__()
    sender.__exit__()

if __name__ == '__main__':
    main()
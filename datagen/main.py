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

    consumer_thread = Thread(target=receiver.recv, args=(sim,))
    consumer_thread.start()
    
    control = 0
    while(control < 10):
        messages = sim.run()
        for m in messages:
            sender.send(m)

        sleep(1)
        control += 1

    print('1')
    consumer_thread.join()
    print('2')
    receiver.__exit__()
    print('3')
    sender.__exit__()
    print('4')

if __name__ == '__main__':
    main()
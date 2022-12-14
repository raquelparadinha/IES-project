from time import sleep

from threading import Thread

from classes import *
from comms import *

areasfile = 'mongodb/seeddata/areas.json'
sensorfile = 'mongodb/seeddata/moveSensors.json'
workstationsfile = 'mongodb/seeddata/workstations.json'
inmatesfile = 'mongodb/seeddata/inmates.json'

MONGO_HOST = "mongodb"
MONGO_PORT = 27017

def main():
    sim = Simulator(MONGO_HOST, MONGO_PORT)
    receiver = Receiver()
    sender = Sender()

    consumer_thread = Thread(target=receiver.recv, args=(sim,))
    consumer_thread.start()
    
    while(True):
        messages = sim.run()
        for m in messages:
            sender.send(m)

        sleep(0.5)

    print('1')
    consumer_thread.join()
    print('2')
    receiver.__exit__()
    print('3')
    sender.__exit__()
    print('4')

if __name__ == '__main__':
    main()
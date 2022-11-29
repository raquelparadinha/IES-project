from time import sleep

from classes import *
from comms import *

layoutfile = 'data/prison-layout.txt'
healthfile = 'data/health-values.txt'
workstationsfile = 'data/workstations.txt'

def main():
    sim = Simulator(layoutfile, healthfile, workstationsfile)
    sender = Sender()
    receiver = Receiver()
    
    while(True):
        inmate, sensor = sim.moveInmate()
        print('Move: {} {}'.format(inmate, sensor))
        sender.publish('sensor', {'inmateid': inmate.id, 'newlocation': sensor.exit})

        if sensor.exit.name == 'JobWing':
            if inmate.workstation == None:
                ws = sim.workstationApply() 
                print('Apply: {} {}'.format(inmate, ws))
                sender.publish('apply', {'inmate': inmate.id, 'workstation': ws.name})
            else:
                wq = sim.workstationWork(inmate)
                print('Work: {} {}'.format(inmate, wq))
                sender.publish('work', {'inmate': inmate.id, 'quota': wq})

        elif sensor.exit.name == 'Infirmary':
            hc = sim.makeHealthcheck()
            print('Healthcheck: {} {}'.format(inmate, hc))
            sender.publish('health', {'healthcheck': hc})

        sleep(1)

if __name__ == '__main__':
    main()
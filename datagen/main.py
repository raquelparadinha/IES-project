from classes import *

layoutfile = 'data/prison-layout.txt'
healthfile = 'data/health-values.txt'
workstationsfile = 'data/workstations.txt'

def main():
    gen = Simulator(layoutfile, healthfile, workstationsfile)
    
    # while(True):
    for i in range(10):
        # move inmate
        # if infarmary -> healthcheck
        # if jobwing
        #   if hobo 
        #       if jobs available -> apply for job
        #   if not hobo -> work
        inmate, sensor = gen.moveInmate()
        if sensor.exit.name == 'JobWing':
            pass
        elif sensor.exit.name == 'Infirmary':
            hc = gen.makeHealthcheck()
            print('Healthcheck: {} {}'.format(inmate, hc))

if __name__ == '__main__':
    main()
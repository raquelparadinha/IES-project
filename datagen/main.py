from classes import Generator

layoutfile = 'data/prison-layout.txt'
healthfile = 'data/health-values.txt'
workstationsfile = 'data/workstations.txt'

def main():
    gen = Generator(layoutfile, healthfile, workstationsfile)
    
    # while(True):
    for i in range(10):
        # move inmate
        # if infarmary -> healthcheck
        # if jobwing
        #   if hobo 
        #       if jobs available -> apply for job
        #   if not hobo -> work

        


if __name__ == '__main__':
    main()
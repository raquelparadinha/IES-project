class Location():
    def __init__(self, id: int, name: str, access: bool, connections: set):

        self.id = id
        self.name = name
        self.access = access
        self.connections = connections

    def __str__(self):
        return '{}'.format(self.name)

class Sensor():
    def __init__(self, id: int, entry: Location, exit: Location, active: bool):

        self.id = id
        self.entry = entry
        self.exit = exit
        self.active = active
        

    def __str__(self):
        return '{} -> {}'.format(self.entry.name, self.exit.name)

class Workstation():
    def __init__(self, id: int, name: str, listings: int, minimum: int, maximum: int, expected: int):

        self.id = id
        self.name = name
        self.listings = listings
        self.minimum = minimum
        self.maximum = maximum
        self.expected = expected

        self.workers = {}

    def __str__(self):
        return '{}'.format(self.name)

class Inmate():
    cntr = 0
    def __init__(self, id: int, startlocation: Location, workstation: Workstation = None, application: Workstation = None):
        
        self.id = id
        
        self.location = startlocation
        self.workstation = workstation
        self.application = application

    def __str__(self):
        return 'Inmate{} is at {}'.format(self.id, self.location)

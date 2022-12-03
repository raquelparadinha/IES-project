class Inmate():
    cntr = 0
    def __init__(self, startlocation, workstation=None, application=None):
        self.id = Inmate.cntr
        Inmate.cntr += 1

        self.location = startlocation
        self.workstation = workstation
        self.application = application

    def __str__(self):
        return 'Inmate{} is at {}'.format(self.id, self.location)

class Location():
    def __init__(self, name: str, access: bool, connections: set):

        self.name = name
        self.access = access
        self.connections = connections

    def __str__(self):
        return '{}'.format(self.name)

class Sensor():
    def __init__(self, entry, exit):

        self.entry = entry
        self.exit = exit

    def __str__(self):
        return '{} -> {}'.format(self.entry.name, self.exit.name)

class Workstation():
    def __init__(self, name, listings, minimum, maximum, expected):

        self.name = name
        self.listings = listings
        self.minimum = minimum
        self.maximum = maximum
        self.expected = expected

        self.workers = {}

    def __str__(self):
        return '{}'.format(self.name)

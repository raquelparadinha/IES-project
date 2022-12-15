class Area():
    def __init__(self, id: int, name: str, access: bool, capacity: int):

        self.id = id
        self.name = name
        self.capacity = capacity
        self.access = access

    def __str__(self):
        return 'a[name: {}]'.format(self.name)

class Sensor():
    def __init__(self, id: int, entry: Area, exit: Area, active: bool):

        self.id = id
        self.entry = entry
        self.exit = exit
        self.active = active

    def __str__(self):
        active = "yes" if self.active else "no"
        return 's[in: {}, out: {}, act: {}]'.format(self.entry.name, self.exit.name, active)

class Workstation():
    def __init__(self, id: int, name: str):

        self.id = id
        self.name = name

    def __str__(self):
        return 'ws[name: {}]'.format(self.name)

class Inmate():
    cntr = 0
    def __init__(self, id: int, startarea: Area):
        
        self.id = id
        
        self.area = startarea


    def __str__(self):
        return 'inm[id: {}, area: {}]'.format(self.id, self.area)

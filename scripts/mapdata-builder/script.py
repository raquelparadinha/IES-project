def main():
    lines = []
    with open('data.js', 'r') as f:
        lines = f.readlines()

    index = -1
    lock = False

    name = 0
    nameincr = 0
    coords = ""
    
    data = {0: {}, 1: {}, 2: {}}
    
    for l in lines:
        l = l.strip()
        print(lock, l)

        if l.startswith('export const'):
            index += 1

        elif l.startswith('name'):
            name = l.replace('name: "', '').replace('"', '').replace(',', '').replace(' ', '').lower()
        
        elif l.startswith('coordinates'):
            lock = True
        
        elif l.startswith('['):
            aux = l.replace(',', ' ').replace('[', '').replace(']', '').replace('  ', ' ')
            coords += aux
        elif lock and not l.startswith('//'):
            print(name, coords)
            data[index][name] = coords.strip()
            coords = ""
            
            nameincr += 1
            name = nameincr
            lock = False


    with open('mapdata.js', 'w') as f:
        f.write("export const buildingData = [")
        for i in data[0]:
            f.write("{" + "{}: \"{}\"".format(i, data[0][i]) + "},\n")
        f.write("];")

        f.write("export const areaData = [")
        for i in data[1]:
            f.write("{" + "{}: \"{}\"".format(i, data[1][i]) + "},\n")
        f.write("];")

        f.write("export const sensorData = [")
        for i in data[2]:
            f.write("{" + "{}: \"{}\"".format(i, data[2][i]) + "},\n")
        f.write("];")


if __name__ == '__main__':
    main()
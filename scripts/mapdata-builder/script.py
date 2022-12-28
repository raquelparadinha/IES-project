def main():
    lines = []
    with open('olddata.js', 'r') as f:
        lines = f.readlines()

    index = -1
    lock = False

    name = 0
    n = 0
    coords = ""
    
    data = {0: {}, 1: {}, 2: {}}
    
    for l in lines:
        l = l.strip()

        if l.startswith('export const'):
            index += 1
            n = 0

        elif l.startswith('name'):
            name = l.replace('name: "', '').replace('"', '').replace(',', '').replace(' ', '').lower()
        
        elif l.startswith('coordinates'):
            lock = True
        
        elif l.startswith('['):
            aux = l.replace(',', ' ').replace('[', '').replace(']', '').replace('  ', ' ')
            coords += aux
        elif lock and not l.startswith('//'):
            if type(name) == int:
                data[index][name] = {"points": coords.strip()}
            else:
                data[index][n] = {"name": name, "points": coords.strip()}
            coords = ""
            
            n += 1
            name = n
            lock = False

    # for i in data:
    #     print(i)
    #     for j in data[i]:
    #         print("   ", data[i][j])

    with open('mapdata.js', 'w') as f:
        f.write("export const buildingData = [")
        for i in data[0]:
            f.write("{" + "{}: {{points: \"{}\"}}".format(i, data[0][i]['points']) + "},\n")
        f.write("];")

        f.write("export const areaData = [")
        for i in data[1]:
            f.write("{" + "{}: {{name: \"{}\", points:\"{}\"}}".format(i, data[1][i]['name'], data[1][i]['points']) + "},\n")
        f.write("];")

        f.write("export const sensorData = [")
        for i in data[2]:
            f.write("{" + "{}: {{name: \"{}\", points:\"{}\"}}".format(i, data[2][i]['name'], data[2][i]['points']) + "},\n")
        f.write("];")


if __name__ == '__main__':
    main()
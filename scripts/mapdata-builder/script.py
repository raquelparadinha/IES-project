def main():
    lines = []
    with open('olddata.js', 'r') as f:
        lines = f.readlines()

    idx = 0
    with open('mapdata.js', 'w') as f:
        while(idx < 30):
            l = lines[idx]
            idx += 1

            points = l.split('points: ')[1].replace(' },', '').replace('"', '').strip();

            coords = [int(n) for n in points.split()]

            x = [n for i, n in enumerate(coords) if i % 2 == 0]
            y = [n for i, n in enumerate(coords) if i % 2 != 0]
            coords = [(x[i], y[i]) for i in range(len(x))]

            if (abs(list(set(x))[0] - list(set(x))[1]) == 20):
                newx = int(sum(set(x)) / len(set(x)))
                xvals = [n for n in set(x) if x.count(n) > 1]
                xvals.append(newx)
                yvals = set(y)
            else:
                xvals = set(x)
                newy = sum(set(y)) / len(set(y))
                yvals = [n for n in set(y) if y.count(n) > 1]
                yvals.append(newy)
                
            newcoords = [(a, b) for a in xvals for b in yvals]

            s = ""
            for (x, y) in newcoords:
                s += str(x) + " " + str(y) + " "
            f.write(s.strip())
            f.write('\n')



if __name__ == '__main__':
    main()
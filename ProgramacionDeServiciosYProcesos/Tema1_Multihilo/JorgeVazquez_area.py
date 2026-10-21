import threading

class area():
    areaTotal = 0

def calculoRectangulo(x,y,i):
    print(f'Calculando rectangulo{i}', flush=True)
    area.areaTotal += x*y
    print(area.areaTotal)

def calcularTriangulo(x,y,i):
    print(f'Calculando triangulo {i}', flush=True)
    area.areaTotal += (x*y)/2
    print(area.areaTotal)

def main():
    x,y= 0,0
    for i in range(0,4):
        if i<2 :
            if i == 1:
                x = 8
                y = 12
            else:
                x = 6
                y = 5

            t = threading.Thread(target=calculoRectangulo, args=(x,y,i))
            t.start()
            t.join()


        else:
            if i == 3:
                x = 10
                y = 12
            else:
                x = 2
                y = 5
            t = threading.Thread(target=calcularTriangulo, args=(x,y,i))
            t.start()
            t.join()

    print(f'Area total : {area.areaTotal}', flush=True)


main()

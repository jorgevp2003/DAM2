from collections import deque
import multiprocessing as mp


def Lectura(dir):
    with open(dir, "r", encoding="UTF-8") as r:
        print(r.read())


def Escritura(cola: deque, dir):
    with open(dir, "a", encoding="UTF-8") as w:
        while len(cola) > 0:   
            w.write(cola.pop(0) + "\n")
        w.flush()

    p2 = mp.Process(target=Lectura, args=(dir,))
    p2.start()
    p2.join()
    p2.kill

def main():

    texto = "0"
    cola = []

    while texto != "==":
        texto = input("Introduce un texto (== para salir  -1 mostrar): ")
        
        if texto == "-1":
            p1 = mp.Process(target=Escritura,args=(cola,"texto3.txt"))
            p1.start()
            p1.join()
            cola = []
            p1.kill
        else: 
            cola.append(texto) 


if __name__ == "__main__":
    main()
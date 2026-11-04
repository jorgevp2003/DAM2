import threading
from multiprocessing import Queue

class usar():
    textoOrigen = ""
    textoVuelta =""

def escritor(pos):
    impar = False
    long = len(usar.textoOrigen)
    mitad = 0
    if long%2!=0 :
        impar = True

    if impar == True:
        mitad = (long/2)+0.5
    else:
        mitad = long/2

    if pos == 1 :
        for i in range(int(mitad)):
            usar.textoVuelta += usar.textoOrigen[i]
    else:
        for i in range(int(mitad),int(long)):
            usar.textoVuelta += usar.textoOrigen[i]


def main():
    while True:
        usar.textoOrigen = input("Que quieres que escriban los hilos?")
        if(usar.textoOrigen!="-1"):
            for i in range(1,3):
                p = threading.Thread(target=escritor,args=(i,))
                p.start()
            print(usar.textoVuelta)
            usar.textoVuelta = ""
            
        else: 
            exit


if __name__ == "__main__":
    main()
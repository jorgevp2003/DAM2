#Define una variable global contador con valor 0 y un Lock.
#Arranca 4 hilos. Cada uno recibe su número y suma 1 al contador 100 000 veces.
#El programa principal espera a todos e imprime el valor final, que debe ser exactamente 400 000.

import threading

contador = 0
candado = threading.Lock()

def sumar():
    global contador

    for i in range(100000):
        contador +=1
        print(contador)

colaThreadings = []

def main():
    global contador
    for i in range(4):
        t = threading.Thread(target=sumar)
        t.start()
        colaThreadings.append(t)

    for t in colaThreadings:
        t.join()

    


if __name__ == "__main__":
    main()

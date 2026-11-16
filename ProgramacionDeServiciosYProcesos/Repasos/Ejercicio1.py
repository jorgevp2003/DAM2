#Pide un nombre por consola.
#Si el nombre es distinto de -1, arranca un proceso (multiprocessing.Process) que recibe el nombre como parámetro, imprime Hola, <nombre> y su PID, y termina.
#El padre espera a que el hijo termine, imprime su propio PID y vuelve a pedir otro nombre.
#Repite hasta que el nombre introducido sea -1.
import multiprocessing as mp
import os
import time
def hijo(texto,):
    print(f"Hola, {texto} mi pid es {os.getpid()}")

def main():
    while True:
        nombre = input("Introduce tu nombre, -1 para salir")
        if(nombre != "-1"):
            p = mp.Process(target= hijo, args=(nombre,))
            p.start()
            p.join()
            print(f"Soy el padre {os.getpid()}")
        else:   
            break

if __name__ == "__main__":
    main()
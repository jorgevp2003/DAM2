#Pide un nombre por consola.
#Si es distinto de -1, arranca 2 hilos que reciben el nombre y un número de hilo (1 o 2). 
# Cada hilo imprime 3 veces Hilo X saluda a <nombre>, con medio segundo de espera entre cada mensaje.
#El programa principal espera a que terminen los dos, imprime Hilos terminados y vuelve a pedir otro nombre.
#Repite hasta que el nombre sea -1.

import threading
import time
class variables():
    nombre = ""

def hilo(id,nombre):
    for _ in range(3):
        time.sleep(0.5)
        print(f"Hilo {id} saluda a {nombre}")

def main():
    while True:
        hilos = []
        variables.nombre = input("Introduce un nombre: \n")
        if(variables.nombre != "-1"):
            for i in range(2):
                t = threading.Thread(target=hilo, args=(i,variables.nombre))
                t.start()
                hilos.append(t)

            for t in hilos:
                t.join()
            print("Hilos terminados ")
        else: 
            break
       
            
if __name__ == "__main__":
    main()
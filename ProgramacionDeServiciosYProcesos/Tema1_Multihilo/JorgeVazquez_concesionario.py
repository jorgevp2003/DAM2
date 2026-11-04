import threading
import random
from multiprocessing import Queue

def linea(colaCoches):
    for i in range(50):
        coche = random.randint(1,4)
        if coche == 1:
            colaCoches.put('modelo:Polo,precio:19000')
        elif(coche == 2):
            colaCoches.put('modelo:Golf,precio:13000')
        elif(coche == 3):
            colaCoches.put('modelo:Tiguan,precio:22000')
        else:
            colaCoches.put('modelo:Passat,precio:17000')
    exit

def concesionario(colaCoches,id):
    while True:
        if(colaCoches.qsize()>0):
            print(f"Concesionario {id} ha vendido {colaCoches.get()}")
        else:
            exit


def fabrica(fabricas,concesionarios,colaCoches):
    for i in range(int(fabricas)):
        p = threading.Thread(target=linea, args=(colaCoches,))
        p.start()

    for i in range(int(concesionarios)):
        p = threading.Thread(target=concesionario,args=(colaCoches,i))
        p.start()

colaCoches = Queue()
def main():
    print("Indica cuantas lines y concesionarios quieres arrancar:")
    lineas = input("Empecemos por las lineas.")
    concesionarios = input("Ahora los concesionarios.")

    fabrica(lineas,concesionarios,colaCoches)


if __name__ == "__main__":
    main()
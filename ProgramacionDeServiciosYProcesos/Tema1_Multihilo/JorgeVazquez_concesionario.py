import threading
import random
from colas import cola
import time

def linea(colaCoches):
    i = 0
    while i < 50:
        if(colaCoches.qSize()<50):
            coche = random.randint(1,4)
            if coche == 1:
                colaCoches.put('modelo:Polo,precio:19000')
            elif(coche == 2):
                colaCoches.put('modelo:Golf,precio:13000')
            elif(coche == 3):
                colaCoches.put('modelo:Tiguan,precio:22000')
            else:
                colaCoches.put('modelo:Passat,precio:17000')

            i+=1
            print(f"Linea ha generado un coche {i}")
            time.sleep(0.5)
            
        else:
            print(f"En espera {i}")
            time.sleep(0.5)
           
    exit

def concesionario(colaCoches,id):
    while True:
        iteraciones = 0
        
        if(colaCoches.qSize()>0):
            print(f"Concesionario {id} ha vendido {colaCoches.get()}")
            time.sleep(0.5)
        elif iteraciones == 5:
            exit
        else:
            iteraciones = iteraciones + 1
            time.sleep(0.5)


def fabrica(fabricas,concesionarios,colaCoches):
    for i in range(int(fabricas)):
        p = threading.Thread(target=linea, args=(colaCoches,))
        p.start()
        
    for i in range(int(concesionarios)):
        p = threading.Thread(target=concesionario,args=(colaCoches,i))
        p.start()

colaCoches = cola()
def main():
    print("Indica cuantas lines y concesionarios quieres arrancar:")
    lineas = input("Empecemos por las lineas.")
    concesionarios = input("Ahora los concesionarios.")

    fabrica(lineas,concesionarios,colaCoches)


if __name__ == "__main__":
    main()
# =============================================================================
# JorgeVazquez_concesionario.py
# Problema productor-consumidor con HILOS:
#   - linea()        (productor): fabrica 50 coches y los mete en la cola.
#   - concesionario() (consumidor): saca coches de la cola y los "vende".
# La cola es la clase propia 'cola' definida en colas.py (máximo 50 elementos).
# Flujo: main() -> fabrica() -> lanza N hilos linea + M hilos concesionario
#        linea --put--> colaCoches --get--> concesionario
# =============================================================================
import threading
import random
from colas import cola  # Cola propia (ver colas.py)
import time

def linea(colaCoches):
    """Hilo productor: genera 50 coches aleatorios y los mete en la cola."""
    i = 0  # Coches fabricados por esta línea
    while i < 50:
        # Solo fabricamos si la cola no está llena
        if(colaCoches.qSize()<50):
            # Elegimos un modelo al azar
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
            # Cola llena: esperamos a que los concesionarios vendan
            print(f"En espera {i}")
            time.sleep(0.5)

    exit  # NOTA: no hace nada (sin paréntesis); el hilo acaba solo al salir del while

def concesionario(colaCoches,id):
    """Hilo consumidor: saca coches de la cola y los vende."""
    while True:
        # NOTA: al reiniciarse en cada vuelta, 'iteraciones' nunca llega a 5,
        # y 'exit' sin paréntesis no detiene el hilo (habría que usar break).
        iteraciones = 0

        if(colaCoches.qSize()>0):
            print(f"Concesionario {id} ha vendido {colaCoches.get()}")
            time.sleep(0.5)
        elif iteraciones == 5:
            exit
        else:
            # Cola vacía: esperamos un poco antes de volver a mirar
            iteraciones = iteraciones + 1
            time.sleep(0.5)


def fabrica(fabricas,concesionarios,colaCoches):
    """Arranca los hilos productores (líneas) y consumidores (concesionarios)."""
    for i in range(int(fabricas)):
        p = threading.Thread(target=linea, args=(colaCoches,))
        p.start()

    for i in range(int(concesionarios)):
        p = threading.Thread(target=concesionario,args=(colaCoches,i))
        p.start()

colaCoches = cola()  # Cola compartida por todos los hilos
def main():
    """Pide cuántas líneas y concesionarios arrancar y lanza la simulación."""
    print("Indica cuantas lines y concesionarios quieres arrancar:")
    lineas = input("Empecemos por las lineas.")
    concesionarios = input("Ahora los concesionarios.")

    fabrica(lineas,concesionarios,colaCoches)


if __name__ == "__main__":
    main()

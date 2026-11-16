# =============================================================================
# JorgeVazquez_MPComQueue2.py
# Variante de MPComQueue: los textos se acumulan en una lista ("cola") y solo
# al escribir -1 se lanza un proceso Escritura que vuelca todos en
# 'texto3.txt'; después ese proceso lanza Lectura para mostrar el fichero.
# Se sale escribiendo ==.
# Flujo: main() acumula -> Proceso Escritura -> Proceso Lectura
# =============================================================================
from collections import deque
import multiprocessing as mp


def Lectura(dir):
    """Proceso que lee e imprime todo el contenido del fichero 'dir'."""
    with open(dir, "r", encoding="UTF-8") as r:
        print(r.read())


def Escritura(cola: deque, dir):
    """Proceso que vuelca todos los textos de 'cola' al fichero y lanza la lectura."""
    # NOTA: en realidad se recibe una lista (no un deque); pop(0) saca el primero (FIFO).
    # El proceso hijo trabaja con una COPIA de la lista, no con la de main().
    with open(dir, "a", encoding="UTF-8") as w:
        while len(cola) > 0:
            w.write(cola.pop(0) + "\n")
        w.flush()  # Forzamos a que se escriba en disco antes de leerlo

    p2 = mp.Process(target=Lectura, args=(dir,))
    p2.start()
    p2.join()  # Esperamos a que termine la lectura
    p2.kill    # NOTA: sin paréntesis no hace nada (y tras join() ya no hace falta)

def main():
    """Acumula textos y los vuelca al fichero al escribir -1; sale con ==."""

    texto = "0"
    cola = []  # Textos pendientes de escribir

    while texto != "==":
        texto = input("Introduce un texto (== para salir  -1 mostrar): ")

        if texto == "-1":
            # Lanzamos la escritura de todo lo acumulado y esperamos
            p1 = mp.Process(target=Escritura,args=(cola,"texto3.txt"))
            p1.start()
            p1.join()
            cola = []  # Vaciamos la lista en el proceso principal
            p1.kill    # NOTA: sin paréntesis no hace nada
        else:
            # NOTA: también se añade "==" a la lista justo antes de salir
            cola.append(texto)


# Necesario en Windows para que los procesos hijos no vuelvan a ejecutar main()
if __name__ == "__main__":
    main()

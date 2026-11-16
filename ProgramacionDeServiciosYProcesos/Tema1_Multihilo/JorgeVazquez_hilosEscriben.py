# =============================================================================
# JorgeVazquez_hilosEscriben.py
# Dos HILOS reconstruyen un texto: el hilo 1 copia la primera mitad y el
# hilo 2 la segunda mitad en la variable compartida usar.textoVuelta.
# Se repite con cada texto que introduce el usuario (-1 para salir).
# =============================================================================
import threading
from multiprocessing import Queue  # NOTA: no se usa

class usar():
    """Contenedor de las variables compartidas entre hilos."""
    textoOrigen = ""   # Texto introducido por el usuario
    textoVuelta =""    # Texto reconstruido por los hilos

def escritor(pos):
    """Hilo: copia la primera (pos=1) o la segunda (pos=2) mitad del texto."""
    impar = False
    long = len(usar.textoOrigen)
    mitad = 0
    if long%2!=0 :
        impar = True

    # Si la longitud es impar, la primera mitad se queda con el carácter central
    if impar == True:
        mitad = (long/2)+0.5
    else:
        mitad = long/2

    if pos == 1 :
        # Primera mitad: de 0 a mitad-1
        for i in range(int(mitad)):
            usar.textoVuelta += usar.textoOrigen[i]
    else:
        # Segunda mitad: de mitad hasta el final
        for i in range(int(mitad),int(long)):
            usar.textoVuelta += usar.textoOrigen[i]


def main():
    """Pide textos y lanza los dos hilos escritores para cada uno."""
    while True:
        usar.textoOrigen = input("Que quieres que escriban los hilos?")
        if(usar.textoOrigen!="-1"):
            for i in range(1,3):
                p = threading.Thread(target=escritor,args=(i,))
                p.start()
            # NOTA: no se hace join(), así que puede imprimirse antes de que los
            # hilos acaben o con las mitades en orden cambiado.
            print(usar.textoVuelta)
            usar.textoVuelta = ""

        else:
            exit  # NOTA: sin paréntesis no sale; habría que usar break


if __name__ == "__main__":
    main()

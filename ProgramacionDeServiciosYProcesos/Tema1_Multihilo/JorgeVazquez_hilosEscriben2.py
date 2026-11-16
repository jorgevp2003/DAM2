# =============================================================================
# JorgeVazquez_hilosEscriben2.py
# Versión 2: los dos HILOS se arrancan una sola vez y se quedan vigilando la
# variable compartida info.texto. Cuando cambia, el hilo 1 escribe la primera
# mitad y el hilo 2 la segunda en info.textoFinal. El hilo principal pide
# el texto, espera 1 s y muestra el resultado.
# =============================================================================
import threading
import time
class info():
    """Contenedor de las variables compartidas entre hilos."""
    texto = ""        # Último texto introducido por el usuario
    textoFinal = ""   # Texto reconstruido por los hilos

def hilo(pos):
    """Hilo: detecta un texto nuevo y escribe su mitad (pos=1 primera, pos=2 segunda)."""
    textoDentro = ""  # Último texto procesado por este hilo
    while True:  # Espera activa: comprueba continuamente si hay texto nuevo
        if info.texto == "-1":
            exit  # NOTA: sin paréntesis no para el hilo; habría que usar break
        if textoDentro != info.texto :
            textoDentro = info.texto

            long = len(textoDentro)

            # Calculamos el punto medio del texto
            if(long%2!=0):
                mitad = (long+1) //2
            else:
                mitad = long/2  # NOTA: '/' da float y range() fallará; debería ser '//'

            if(pos==1):
                # Primera mitad, añadiéndola por delante del resultado
                # NOTA: range(mitad,0,-1) incluye 'mitad' y no incluye el índice 0
                for i in range(mitad,0,-1):
                    info.textoFinal = textoDentro[i] +info.textoFinal
            else:
                # Segunda mitad, añadiéndola por detrás del resultado
                for i in range(mitad,int(long)):
                    info.textoFinal += textoDentro[i]


def main():
    """Arranca los dos hilos y luego pide textos en bucle."""
    for i in range(1,3):
        print(f"Iniciando proceso {i}")
        p = threading.Thread(target=hilo, args=(i,))
        p.start()

    while True:
        txt =input("Introduce el texto a introducir")
        info.texto = txt   # Los hilos detectan el cambio y trabajan
        time.sleep(1)      # Damos tiempo a los hilos para terminar
        print(info.textoFinal)
        # NOTA: textoFinal no se vacía entre textos, así que se van acumulando
        if(txt == "-1"):
            exit  # NOTA: sin paréntesis no sale; habría que usar break




if __name__ == "__main__":
    main()

# =============================================================================
# JorgeVazquez_Camaras.py
# Simulación de 5 cámaras de vigilancia, cada una en su propio PROCESO.
# Cada cámara envía su estado por una cola compartida (mp.Queue) con el
# formato "id:<num>:<estado>". El proceso principal lee los mensajes, cuenta
# las alarmas de cada cámara y la "mata" cuando acumula 3 alarmas.
# Flujo: main() -> lanza cam() x5 -> cam() llama a vigilaHorizonte()
#        -> cam() mete el mensaje en la cola -> main() lo lee y procesa.
# =============================================================================
import multiprocessing as mp
import random
import time
from collections import deque  # NOTA: no se usa


def main():
    """Proceso principal: crea las cámaras y procesa sus mensajes."""
    print("Hola")
    cola = mp.Queue()   # Cola compartida entre procesos para recibir los mensajes
    sum = [0]*5         # Número de alarmas por cámara (NOTA: 'sum' tapa la función sum())
    tot = 0             # Número de cámaras apagadas
    procesos = []       # Guardamos los procesos para poder matarlos después

    # Creamos y arrancamos 5 procesos cámara
    for i in range(5):
        print("Creando bot")
        i = mp.Process(target=cam ,args=(i,cola))  # NOTA: reutiliza 'i' para el proceso
        i.start()
        procesos.append(i)


    # Seguimos leyendo hasta que se hayan apagado las 5 cámaras
    while tot != 5 :
        msg = cola.get()        # Bloquea hasta que llega un mensaje
        sep = msg.split(":")    # sep = ['id', '<num>', '<estado>']
        for i in range(1):      # NOTA: solo da una vuelta, i siempre vale 0
            print(msg)
            print(sep[2])
            # Si el estado es una alarma, sumamos una a la cámara que la envió
            if(sep[2] in ('Alarma leve', 'Alarma Media', 'Alarma Grave')):
                num = int(sep[1])
                sum[num] +=1
            # NOTA: siempre comprueba sum[1] (i+1 = 1), no la cámara que envió el mensaje;
            # debería ser sum[num]. Además, al seguir siendo 3, intentaría matarla varias veces.
            if sum[i+1] == 3:
                print("Matando camara {i}")  # NOTA: falta la 'f' para que se muestre el valor
                num = i+1
                procesos[num].kill()  # Termina el proceso de esa cámara
                tot +=1

            time.sleep(1)

        # Mostramos el recuento de alarmas de cada cámara
        for i in range(5):
         print(f"{i} + {sum[i]}")

def cam(id, cola):
    """Proceso cámara: cada 3 s vigila y envía su estado a la cola."""
    while 1 != 2:  # Bucle infinito (equivale a while True)
        msg = vigilaHorizonte()
        cola.put(f"id:{id}:{msg}")
        time.sleep(3)

def vigilaHorizonte():
    """Devuelve un estado aleatorio (70% 'Calma', 30% algún tipo de alarma)."""
    situacion = ['Calma', 'Calma', 'Calma', 'Calma', 'Calma', 'Calma', 'Calma', 'Alarma leve', 'Alarma Media', 'Alarma Grave']
    i = random.randint(0, 9)
    return(situacion[i])


# Necesario en Windows para que los procesos hijos no vuelvan a ejecutar main()
if __name__ == "__main__":
    main()

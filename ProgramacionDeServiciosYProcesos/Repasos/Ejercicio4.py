# Crea dos colas: una de resultados y una de control.
# Crea 3 trabajadores (1 proceso por trabajador), pasándoles su ID (1, 2, 3) y las dos colas.
# Va recibiendo los mensajes de la cola de resultados y los muestra. Ejemplo: Trabajador 2: Pieza defectuosa.
# Cuenta el total de mensajes recibidos. Cuando llega a 10, envía la orden "Para" por la cola de control, una por cada trabajador,
# espera a que terminen todos e imprime cuántos mensajes ha enviado cada uno.

from multiprocessing import Queue
import multiprocessing as mp
import random
import time

def revisaPieza():
    estados = ['Correcta', 'Correcta', 'Correcta', 'Correcta', 'Correcta', 'Correcta',
               'Pieza defectuosa', 'Pieza rayada', 'Falta tornillo', 'Medida errónea']
    i = random.randint(0, 9)
    return estados[i]

def trabajador(cola1, cola2, id):
    while True:
        if (cola2.qsize() > 0):
            break
        else:
            estadoPieza = revisaPieza()
            cola1.put(f"Trabajador {id}: {estadoPieza}")
            time.sleep(0.2)
            




def main():
    colaResultados = Queue(10)
    colaControl = Queue()
    for i in range(3):
        p = mp.Process(target=trabajador, args=(colaResultados,colaControl,i))
        p.start()

    while True:
        if(colaResultados.qsize()==10):
            colaControl.put("Fin")
            break


    for i in range(colaResultados.qsize()):
        print(colaResultados.get())
    


if __name__ == "__main__":
    main()
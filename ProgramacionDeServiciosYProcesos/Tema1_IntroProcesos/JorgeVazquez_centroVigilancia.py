# =============================================================================
# JorgeVazquez_centroVigilancia.py
# Centro de vigilancia con 5 cámaras (PROCESOS) y dos colas:
#   - cola_alarma : las cámaras envían ahí sus alertas (estado distinto de 'Calma').
#   - cola_control: el centro envía órdenes a las cámaras (p.ej. "Apagate").
# El proceso principal pregunta al usuario si quiere mandar una orden y
# muestra cuántas alertas se han acumulado.
# =============================================================================
from multiprocessing import Queue, Process
import multiprocessing as mp
import random
import time

def vigilaHorizonte():
    """Devuelve un estado aleatorio (70% 'Calma', 30% algún tipo de alarma)."""
    situacion = ['Calma', 'Calma', 'Calma', 'Calma', 'Calma', 'Calma', 'Calma', 'Alarma leve', 'Alarma Media', 'Alarma Grave']
    i = random.randint(0, 9)
    return(situacion[i])

def camara(id,cola_control,cola_alarma):
    """Proceso cámara: cada 2 s vigila, envía alertas y revisa las órdenes."""
    while True:
        estado = vigilaHorizonte()
        # Solo enviamos a la cola los estados de alarma
        if estado!="Calma":
            cola_alarma.put(f"La camara {id} esta en estado {estado}")
        # Comprobamos si hay alguna orden en la cola de control
        if(cola_control.qsize()!=0):
            orden = cola_control.get()
            if(orden == "Apagate"):
                # Volvemos a dejar la orden para que la lean las demás cámaras
                # NOTA: falta un 'break' para que la cámara se apague de verdad
                cola_control.put("Apagate")
        time.sleep(2)


# Programa principal (protegido para que los procesos hijos no lo ejecuten)
if __name__ == "__main__":
    cola_control = Queue()  # Órdenes del centro hacia las cámaras
    cola_alarma = Queue()   # Alertas de las cámaras hacia el centro

    #Creamos 5 camaras
    for i in range(5):
                p = Process(target=camara, args=(i,cola_control,cola_alarma))
                p.start()

    
    while True:

        
        pregunta = input("Quieres enviar algo a la cola de control? (NO) o (Mensaje)")
        # Cualquier respuesta distinta de "no" se envía como orden
        if(pregunta!="no"):
            cola_control.put(pregunta)
        if(pregunta=="Apagate"):
            break

        # Mostramos cuántas alertas hay pendientes
        print(f"Hay {cola_alarma.qsize()} de alertas")
        time.sleep(1)

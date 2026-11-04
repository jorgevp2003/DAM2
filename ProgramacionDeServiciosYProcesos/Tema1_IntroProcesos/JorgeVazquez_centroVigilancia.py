from multiprocessing import Queue, Process
import multiprocessing as mp
import random
import time

def vigilaHorizonte():
    situacion = ['Calma', 'Calma', 'Calma', 'Calma', 'Calma', 'Calma', 'Calma', 'Alarma leve', 'Alarma Media', 'Alarma Grave']
    i = random.randint(0, 9)    
    return(situacion[i])

def camara(id,cola_control,cola_alarma):
    while True:
        estado = vigilaHorizonte()
        if estado!="Calma":
            cola_alarma.put(f"La camara {id} esta en estado {estado}")
        if(cola_control.qsize()!=0):
            orden = cola_control.get()
            if(orden == "Apagate"):
                cola_control.put("Apagate")
        time.sleep(2)


if __name__ == "__main__":
    cola_control = Queue()
    cola_alarma = Queue()
    while True:
        

        for i in range(5):
            p = Process(target=camara, args=(i,cola_control,cola_alarma))
            p.start()
        
        pregunta = input("Quieres enviar algo a la cola de control? (NO) o (Mensaje)")
        if(pregunta!="no"):
            cola_control.put(pregunta)
        if(pregunta=="Apagate"):
            break

        print(f"Hay {cola_alarma.qsize()} de alertas")
        time.sleep(1)
        
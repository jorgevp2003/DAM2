import random
from multiprocessing import Process, Queue
import threading


def impresora(num,cola_trabajo,cola_resultado):
    while True:
        trabajo = cola_trabajo.get()
        if trabajo == "None":
            cola_trabajo.put("None")
            break
        if(funcionaBien()): 
            cola_resultado.put("Ok")
            print(f"Impresora {num} ha impreso el mensaje {trabajo}")
        else:
            cola_trabajo.put(trabajo)
        

    

def funcionaBien():
    i = random.randint(0, 5)
    if i == 3:
        return(True)
    else:
        return(False)

if __name__ == "__main__":

    cola_trabajos = Queue()
    cola_resultados = Queue()

    procesos = []
    for i in range(5):
        p = Process(target=impresora,args=(i,cola_trabajos,cola_resultados))
        p.start()
        procesos.append(p)
    resp = True

    while resp == True:
        msg = input("Introduce que quieres imprimir. (None para finalizar cerrando los procesos)")
        if(msg!="None" ):
            cola_trabajos.put(msg)
            print(f"Cola trabajo: {cola_trabajos.qsize()}")
            print(f"Cola resultados {cola_resultados.qsize()}")
        else:
            cola_trabajos.put(msg)
            break


       


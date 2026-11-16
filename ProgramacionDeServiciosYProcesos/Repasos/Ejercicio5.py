#Crea una cola de avisos, compartida por todos los buzones, y una cola de control por cada buzón.
#Crea 4 buzones (1 proceso por buzón), pasándoles su ID (1 a 4), la cola de avisos y su cola de control.
#Recibe los avisos por la cola de avisos y los muestra. Ejemplo: Buzón 3: Buzón lleno.
#Cuenta los avisos de cada buzón. Cuando un buzón llega a 3, lo apaga enviando la orden "Cierra" por la cola de control de ese buzón.
#Cuando los 4 buzones están apagados, espera a que terminen todos los procesos e imprime Oficina cerrada.

#En bucle, llama a compruebaBuzon y espera medio segundo entre llamadas.
#Si el resultado no es "Vacío", crea un dict con su ID y el resultado y lo pone en la cola de avisos.
#En cada vuelta consulta su cola de control sin bloquearse. Si hay una orden "Cierra", imprime Buzón X cerrado con su PID y termina.
from multiprocessing import Process,Queue
import random
import time
import os

def compruebaBuzon():
    estados = ['Vacío', 'Vacío', 'Vacío', 'Vacío', 'Vacío', 'Vacío',
               'Carta nueva', 'Paquete nuevo', 'Buzón lleno', 'Puerta abierta']
    i = random.randint(0, 9)
    return estados[i]

def buzon(colaGeneral,colaIndividual,id):
    while True:

        if(colaIndividual.qsize()>0):
            resIndividual = colaIndividual.get()
            if(resIndividual == "Cierra"):
                print(f"Cerrando buzon {id}, con PID {os.getpid()}")
                break

        
        resultado = compruebaBuzon()
        time.sleep(0.5)
        if resultado != "Vacío":
            colaGeneral.put(f"Buzon {id} : {resultado}")




def main():
    colaAvisos = Queue()
    colaBuzones = [Queue() for i in range(4)]
    numeroAvisos = [0]*4
    

    for i in range(1,5):
        p = Process(target=buzon, args =(colaAvisos,colaBuzones[i-1],i))
        p.start()

    total = 0
    while True:
       
        if colaAvisos.qsize()>0:
            resultado = colaAvisos.get()
            resSeparado = resultado.split(" ")

            numeroAvisos[int(resSeparado[1])-1] +=1

            if(numeroAvisos[int(resSeparado[1])-1]==3):
                colaBuzones[int(resSeparado[1])-1].put("Cierra")
                total +=1

        if total == 4: 
            break

if __name__ == "__main__":
    main() 
import multiprocessing as mp
import random
import time
from collections import deque


def main():
    print("Hola")
    cola = mp.Queue()
    sum = [0]*5
    tot = 0
    procesos = []
    
    for i in range(5):
        print("Creando bot")
        i = mp.Process(target=cam ,args=(i,cola))
        i.start()
        procesos.append(i)
        

    while tot != 5 :
        msg = cola.get()
        sep = msg.split(":")
        for i in range(1):
            print(msg)
            print(sep[2])
            if(sep[2] in ('Alarma leve', 'Alarma Media', 'Alarma Grave')):
                num = int(sep[1])
                sum[num] +=1
            if sum[i+1] == 3:
                print("Matando camara {i}")
                num = i+1
                procesos[num].kill()
                tot +=1
            
            time.sleep(1)

        for i in range(5):
         print(f"{i} + {sum[i]}")

def cam(id, cola):
    while 1 != 2:
        msg = vigilaHorizonte()
        cola.put(f"id:{id}:{msg}")
        time.sleep(3)

def vigilaHorizonte():
    situacion = ['Calma', 'Calma', 'Calma', 'Calma', 'Calma', 'Calma', 'Calma', 'Alarma leve', 'Alarma Media', 'Alarma Grave']
    i = random.randint(0, 9)    
    return(situacion[i])


if __name__ == "__main__":
    main()
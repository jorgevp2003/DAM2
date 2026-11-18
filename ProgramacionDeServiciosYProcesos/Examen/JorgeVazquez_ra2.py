from multiprocessing import Queue
import threading
import random
import time

def vigilaHorizonte():
    situacion = ['Calma','Calma','Calma','Calma','Calma','Calma','Alarma leve', ' Alarma Media', ' Alarma Grave']
    i = random.randint(0,8)
    return(situacion[i])

def dron(colaA,colaC,id):
    print(f"Dron {id} despegando")
    while True:
        estado = vigilaHorizonte()
        if(estado != 'Calma'):
            colaA.put(f"Dron {id} en estado de: {estado}")
            time.sleep(0.3)

        if(colaC.qsize!= 0):
            if(colaC.get()=="Aterrizar"):
                print(f"Apagando dron {id}")
                break
        
            
        
def centroControl(colaAlarmas):
   while True:
      
       if(colaAlarmas.qsize()>0):
           print(colaAlarmas.get())
            

def main():
    colaAlarmas = Queue()
    colaControl = Queue()
    cc = threading.Thread(target=centroControl, args=(colaAlarmas))
    numDrones = 0
    while True:
        accion = input("Que quieres hacer? (Despegar - Inicia un dron), (Aterrizar - Apaga un dron)")
        if(accion == "Despegar"):
            t = threading.Thread(target=dron, args=(colaAlarmas,colaControl,numDrones))
            t.start()
            numDrones +=1
        elif(accion == "Aterrizar"):
            print("Orden a")
            



if __name__ == "__main__":
    main()
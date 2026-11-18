#Crea una cola de pedidos (queue.Queue) y una lista global preparados.
#Lanza 2 hilos (camareros) que, en bucle, sacan pedidos de la cola, esperan 1 segundo simulando la preparación, imprimen Camarero X prepara: <pedido> y añaden el pedido a la lista preparados.
#El programa principal pide pedidos por consola y los mete en la cola. No espera a que se preparen: sigue pidiendo mientras los camareros trabajan.
#Si el texto introducido es -1, el principal avisa a los camareros para que terminen, espera a que acaben los pedidos pendientes e imprime la lista completa de preparados con su total.

#Los dos camareros deben trabajar a la vez: si metes 4 pedidos de golpe, se reparten de dos en dos.
#Al terminar no se puede quedar ningún pedido sin preparar.
#El acceso a la lista preparados debe ser seguro.
from multiprocessing import Queue
import threading
import time

colaPreparados = Queue()

def camarero(colaPedidos,id):
    global colaPreparados
    while True:
        if(colaPedidos.qsize()>0):
            pedido = colaPedidos.get()
            if(pedido == "-1"):
                colaPedidos.put("-1")
                break
            else:
                colaPreparados.put(pedido)
                print(f"\n Camarero {id} saca: {pedido}")
                time.sleep(1)

def main():
    global colaPreparados
    colaPedidos = Queue()
    hilos = []
    for i in range(2):
        t = threading.Thread(target=camarero, args=(colaPedidos,i))
        t.start()

    for i in hilos:
        i.join()

    while True:
        pedido = input("¿Qué quieres pedir?")
        colaPedidos.put(pedido)

        if(pedido == "-1"):
            break

    print(f"Numero de pedidos :{colaPreparados.qsize()}")
    for i in range (colaPreparados.qsize()):
        print(f"{colaPreparados.get()}")



if __name__ == "__main__":
    main()
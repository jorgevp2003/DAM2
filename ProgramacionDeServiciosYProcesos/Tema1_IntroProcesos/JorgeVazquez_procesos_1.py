import time
import multiprocessing as mp
import os


r = open("texto.txt", "r",encoding="UTF-8")

def int_Texto(txt):
    with open("texto.txt","a",encoding="UTF=8") as w:
         w.write(txt+"\n")
    


def imprimir():
    time.sleep(5)
    print("==========================")
    print(r.read())
    print("==========================")

def mostrar_Datos():
    print(f"Proceso ID:{mp.current_process().pid}")

def main():
    salir = False
    while salir != True:
        resp = input("Introduce el texto: (-1 para salir, -P para ver datos) ")
        if resp == '-1':
            mostrar_Datos()
            salir = True
           
        elif (resp == '-P'):
            p1 = mp.Process(target=imprimir)
            p1.start()
            time.sleep(6)
            p1.kill
        else:
            int_Texto(resp)

        
        


if __name__ == "__main__":
    main()
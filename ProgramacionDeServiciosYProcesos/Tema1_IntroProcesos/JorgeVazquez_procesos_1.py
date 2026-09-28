import time
import multiprocessing as mp
import os

w = open("texto.txt", "w",encoding="UTF-8")
r = open("texto.txt", "r",encoding="UTF-8")

def int_Texto(txt):
    if (txt != 'exit') & (txt != '-1'):
         w.write(txt)
    w.flush


def imprimir():
    time(500)
    print(r.read)

def mostrar_Datos():
    print(mp.current_process().pid)

def main():
    resp = 'Respuesta'
    while resp != 'exit':
        if resp == '-1':
            mostrar_Datos()
        else:
            resp = input("Introduce el texto: ")
            int_Texto(resp)


main()
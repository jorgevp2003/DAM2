#Pide un texto por consola.
#Si el texto es distinto de -1:
#Arranca un proceso (escritor) que añade el texto a un fichero y termina.
#Cuando la escritura ha terminado, lanza otro proceso (lector) que lee todo el fichero, lo saca por pantalla e imprime su PID.
#El padre espera al lector antes de volver a pedir texto.
#Repite hasta que el texto introducido sea -1.
import multiprocessing as mp
import time

def escritor(texto,fichero):
    with open(fichero, "a", encoding="UTF-8") as f:
        f.write(texto + "\n")

def lector(fichero):
    with open(fichero, "r", encoding="UTF-8") as f:
        print(f.read())   


def main():
    fichero = "textoEjercicio3.txt"
    while True:
        resp = input("Introduce el texto")
        if resp!= "-1":
            p = mp.Process(target=escritor, args=(resp,fichero))
            p.start()
            p.join()
            l = mp.Process(target=lector, args=(fichero,))
            l.start()
            p.join()
            time.sleep(0.2)

if __name__ == "__main__":
    main()
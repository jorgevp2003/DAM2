#Si la línea es -1, imprime su PID y finaliza.
#Si la línea es -N, arranca un proceso que recibe el nombre del fichero y, tras un retraso de 3 segundos (time.sleep(3)),
# imprime entre dos líneas de símbolos cuántas líneas tiene el fichero, imprime su PID y termina. El padre no espera al hijo: sigue pidiendo líneas mientras tanto.
#En cualquier otro caso, añade la línea al fichero y vuelve a pedir otra.
import os 
import multiprocessing as mp
import time

def leer(texto):
    r = open(texto, encoding="UTF-8")
    lineas = len(r.readlines())
    time.sleep(3)
    print("\n")
    print("===========================================")
    print(f"El fichero {texto} tiene {lineas} lineas de texto")
    print("===========================================")
   

def main():
    while True:
        resp = input("Introduce el nombre de fichero.")
        if(resp == "-1"):
            print(f"Cerrando proceso main = {os.getpid()}")
            break
        else:
            p = mp.Process(target=leer, args=(resp))
            p.start()
            with open("textoEjercicio3.txt", "a", encoding="UTF-8") as f:
                f.write(resp + "\n")



if __name__ == "__main__":
    main()
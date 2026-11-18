
import subprocess
import multiprocessing as mp
import time

def lector(palabra, fich):
    while True:
        with open(fich, "r", encoding="UTF-8") as f:
           texto =  f.read()

        for i in texto.split():
            i = i.strip("-.,¿?!¡-_")
            if quitar_acentos(i.casefold()) == quitar_acentos(texto.casefold()):
                print(f"Se ha encontrado {palabra}")

            else:
                print("No se ha encontrado la palabra")

        time.sleep(0.5)


def quitar_acentos(texto):
    return''.join(
        c for c in unicodedata.normalize('NFD', texto)
        if unicodedata.category(c)!= 'Mn'
      
    )


def main():
    
    fich = input("Introduce en nombre de fichero: ")
    with open(fich, "a", encoding="UTF-8") as f:
        f.write("")

    palabraClave = input("Introduce la palabra clave:")

    proc = subprocess.Popen('notepad.exe', __file__ == fich)

    p = mp.Process(target=lector, args=(palabraClave,fich))
    p.start()
        

if __name__ == "__main__":
      main()
        

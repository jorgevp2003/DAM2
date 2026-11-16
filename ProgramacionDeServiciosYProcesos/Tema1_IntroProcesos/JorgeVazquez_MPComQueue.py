# =============================================================================
# JorgeVazquez_MPComQueue.py
# Encadenamiento de procesos: por cada texto que introduce el usuario,
# main() lanza un proceso Escritura que lo añade a 'texto2.txt', y este a su
# vez lanza un proceso Lectura que muestra el contenido completo del fichero.
# Flujo: main() -> Proceso Escritura -> Proceso Lectura
# =============================================================================
import multiprocessing as mp


def Lectura(dir):
    """Proceso que lee e imprime todo el contenido del fichero 'dir'."""
    with open(dir, "r", encoding="UTF-8") as r:
        print(r.read())


def Escritura(texto, dir):
    """Proceso que añade 'texto' al final del fichero y lanza la lectura."""
    with open(dir, "a", encoding="UTF-8") as w:
        w.write(texto + "\n")
        w.flush()  # Forzamos a que se escriba en disco antes de leerlo

    # NOTA: args debe ser una tupla -> args=(dir,). Con args=dir se pasa cada
    # letra del nombre como un argumento distinto y Lectura falla.
    p2 = mp.Process(target=Lectura, args=dir)
    p2.start()
    p2.join()  # Esperamos a que termine la lectura


def main():
    """Pide textos al usuario hasta que escriba -1."""
    texto = "0"

    while texto != "-1":
        texto = input("Introduce un texto (-1 para salir): ")

        if texto != "-1":
            # Lanzamos el proceso de escritura y esperamos a que acabe
            p1 = mp.Process(target=Escritura,args=(texto,"texto2.txt"))
            p1.start()
            p1.join()


# Necesario en Windows para que los procesos hijos no vuelvan a ejecutar main()
if __name__ == "__main__":
    main()

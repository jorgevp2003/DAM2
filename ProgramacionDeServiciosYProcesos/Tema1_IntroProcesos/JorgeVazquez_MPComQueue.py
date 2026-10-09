import multiprocessing as mp


def Lectura(dir):
    with open(dir, "r", encoding="UTF-8") as r:
        print(r.read())


def Escritura(texto, dir):
    with open(dir, "a", encoding="UTF-8") as w:
        w.write(texto + "\n")
        w.flush()

    p2 = mp.Process(target=Lectura, args=dir)
    p2.start()
    p2.join()


def main():
    texto = "0"

    while texto != "-1":
        texto = input("Introduce un texto (-1 para salir): ")

        if texto != "-1":
            p1 = mp.Process(target=Escritura,args=(texto,"texto2.txt"))
            p1.start()
            p1.join()


if __name__ == "__main__":
    main()
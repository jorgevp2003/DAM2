import procesoExterno
import subprocess as sb


def main():
    salida = 0
    inte = 0
    while salida != 7 :
        proc = sb.Popen(["python","procesoExterno.py"], stdout=sb.PIPE)
        salida = (int) (proc.stdout.read().decode("cp850"))
        print(salida)
        print("===============  ")
        inte += 1

    print(f"Intentos: {inte}")

if __name__ == "__main__" :
    main()


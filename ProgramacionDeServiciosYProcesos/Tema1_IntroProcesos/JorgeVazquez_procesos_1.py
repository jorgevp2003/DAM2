# =============================================================================
# JorgeVazquez_procesos_1.py
# Primer ejercicio de procesos: el usuario escribe textos que se añaden a
# 'texto.txt'. Con -P se lanza un proceso hijo que muestra el fichero, y con
# -1 se muestra el PID del proceso principal y se sale.
# =============================================================================
import time
import multiprocessing as mp
import os  # NOTA: no se usa (os.getpid() sería otra forma de obtener el PID)


# Abrimos el fichero para lectura a nivel de módulo (lo usará imprimir())
# NOTA: en Windows el proceso hijo vuelve a ejecutar esta línea y abre el fichero
# desde el principio, por eso imprimir() puede ver el contenido.
r = open("texto.txt", "r",encoding="UTF-8")

def int_Texto(txt):
    """Añade el texto recibido al final de 'texto.txt'."""
    # NOTA: "UTF=8" está mal escrito, debería ser "UTF-8"
    with open("texto.txt","a",encoding="UTF=8") as w:
         w.write(txt+"\n")



def imprimir():
    """Proceso hijo: espera 5 s y muestra el contenido del fichero."""
    time.sleep(5)
    print("==========================")
    print(r.read())
    print("==========================")

def mostrar_Datos():
    """Muestra el PID del proceso que la ejecuta."""
    print(f"Proceso ID:{mp.current_process().pid}")

def main():
    """Bucle de menú: -1 sale, -P muestra el fichero, otro texto se guarda."""
    salir = False
    while salir != True:
        resp = input("Introduce el texto: (-1 para salir, -P para ver datos) ")
        if resp == '-1':
            mostrar_Datos()
            salir = True

        elif (resp == '-P'):
            # Lanzamos el proceso de impresión y esperamos 6 s a que termine
            p1 = mp.Process(target=imprimir)
            p1.start()
            time.sleep(6)  # (p1.join() sería la forma correcta de esperar)
            p1.kill        # NOTA: sin paréntesis no hace nada
        else:
            int_Texto(resp)





# Necesario en Windows para que los procesos hijos no vuelvan a ejecutar main()
if __name__ == "__main__":
    main()

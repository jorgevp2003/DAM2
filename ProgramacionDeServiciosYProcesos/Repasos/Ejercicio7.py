#Define una variable global texto_total vacía.
#Pide un texto por consola. Si es distinto de -1:
#Arranca 2 hilos (escritores): el primero añade la primera mitad del texto a la variable global y el segundo el resto. 
#El texto debe quedar en el orden original.
#Cuando la escritura ha terminado, lanza otro hilo (lector) que lee toda la variable y la presenta en pantalla.
#Repite hasta que el texto introducido sea -1.
import threading
class variables():
    texto_total = ""
    pos = 0

def hiloEscritor(texto, pos):
    longitud = len(texto)
    if longitud%2 ==0:
         mitad = int(len(texto)/2)
    else:
         mitad = int((len(texto)/2)+0.5)
   

    if pos == 0:
        for _ in range(mitad):
            variables.texto_total += texto[_]
    else:
        for _ in range(mitad,longitud):
            variables.texto_total += texto[_]

def main():
    while True:
        resp = input("Introduce tu frase")
        hilos = []
        if resp!= "-1":
            for i in range(2):
                t = threading.Thread(target=hiloEscritor, args=(resp,i))
                t.start()

            for t in hilos:
                t.join()
            print(variables.texto_total)
            variables.texto_total = ""
        else:
            break
    

if __name__ == "__main__":
    main()
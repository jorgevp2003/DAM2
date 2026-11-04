import threading
import time
class info():
    texto = ""
    textoFinal = ""

def hilo(pos):
    textoDentro = ""
    while True:
        if info.texto == "-1":
            exit
        if textoDentro != info.texto :
            textoDentro = info.texto
           
            long = len(textoDentro)
        
            if(long%2!=0):
                mitad = (long+1) //2
            else:
                mitad = long/2

            if(pos==1):
                for i in range(mitad,0,-1):
                    info.textoFinal = textoDentro[i] +info.textoFinal
            else: 
                for i in range(mitad,int(long)):
                    info.textoFinal += textoDentro[i]

    
def main():
    for i in range(1,3):
        print(f"Iniciando proceso {i}")
        p = threading.Thread(target=hilo, args=(i,))
        p.start()

    while True:
        txt =input("Introduce el texto a introducir")
        info.texto = txt
        time.sleep(1)
        print(info.textoFinal)
        if(txt == "-1"):
            exit




if __name__ == "__main__":
    main()
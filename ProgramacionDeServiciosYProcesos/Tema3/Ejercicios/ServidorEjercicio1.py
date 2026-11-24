#Tenemos una cámara de vigilancia que detecta diversos tipos de objetos: persona, coche, moto y bicicletas.
#En otra máquina (puede ser la misma para desarrollo), tenemos un programa que recibe el resultado de la detección de la cámara y contabiliza los objetos. 

#Se pide:
    #Programa Python que levanta un socket y contabiliza en la información que recibe cuántos objetos se han detectado. Imprime un mensaje en pantalla cada vez que detecta un objeto.
    #Otro programa simula la cámara mediante un mecanismo aleatorio y envía por el socket anterior lo que detecta la cámara simulada. Puede usarse - si se quiere, no es obligatorio - el siguiente código que hace que el número de detecciones no sea homogéneo.
 #objetos = ['-', 'persona', 'coche', 'moto', 'bicicleta']
 #obj = random.randint(-10,4)
 #if obj <= 0:
 #   obj = 0
 #objetoDetectado = objetos[obj]
 

import socket

UDP_IP = "127.0.0.1"
UDP_PORT = 5005

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM) # UDP
sock.bind((UDP_IP,UDP_PORT))

cont = 0
print("Inicio de server")
while True:
    
    cad, add  = sock.recvfrom(1024)
    if cad.decode() != '-':
        if cad.decode() == 'persona':
            print("Se ha detectado una persona.")
            cont += 1
        elif cad.decode() == 'coche':
            print("Se ha detectado un coche.")
            cont += 1
        elif cad.decode() == 'moto':
            print("Se ha detectado una moto.")
            cont += 1
        elif cad.decode() == 'bicicleta':
            print("Se ha detectado una bicicleta.")
            cont += 1
        print(f"Numero de objetos detectados {cont}")

    
    
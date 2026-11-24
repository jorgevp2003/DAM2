import socket
 
UDP_IP = "127.0.0.1"
UDP_PORT = 5005
 
sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM) # UDP
sock.bind((UDP_IP, UDP_PORT))

while True:
    print("Comienzo a escuchar...")
    cad, add = sock.recvfrom(1024)
    if cad.decode() == '-1':
        print("Salimos...")
    else:   
        print("Recibido: {} desde {}".format(cad.decode(), add))
        #sock.bind((cad.decode(), add))
        #sock.send("Mensaje Recibido".encode())

resp = 0
total = 0
producto = 1
par = 0
impar = 0

while resp != -1:
    resp = int(input("Introduce un numero [-1 para terminar]"))
    if(resp == -1):
           print("Saliendo")
    elif(resp%2 == 0):
            par +=1
            total += resp
    else: 
            impar +=1
            producto = producto *resp

print("La suma de los pares es {} y hay {} pares".format(total,par))
print(f"El producto de los impares es {producto} y hay {impar} impares")
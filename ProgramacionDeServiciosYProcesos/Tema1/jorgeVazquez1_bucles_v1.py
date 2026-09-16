''' 
    Pedir de uno en uno 6 números enteros en pantalla
    Ccontar cuántos son pares y cuantos impares.
    Sumar los pares y hallar el producto de los impares
    Presentar en pantalla:

   número de elementos par: ... // Suma:
   número de elementos impar: ... // Producto:


Versión 2: Pedir números enteros en pantalla hasta que introduzcas -1, momento en el cual se presentan los resultados en pantalla y se acaba el bucle.

'''
total = 0
producto = 1
par = 0
impar = 0
for i in range(6):
    resp = int(input(f"Introduce el numero"))
    if(resp%2 == 0):
        par +=1
        total += resp
    else: 
        impar +=1
        producto = producto *resp

print(f"La suma de los pares es {total} y hay {par} pares")
print(f"El producto de los impares es {producto} y hay {inpar} inpares")
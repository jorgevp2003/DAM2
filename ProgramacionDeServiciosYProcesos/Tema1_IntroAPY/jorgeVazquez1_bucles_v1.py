# =============================================================================
# jorgeVazquez1_bucles_v1.py
# Versión 1 (bucle for): pide 6 números, cuenta pares e impares, suma los
# pares y multiplica los impares.
# =============================================================================
'''
    Pedir de uno en uno 6 números enteros en pantalla
    Ccontar cuántos son pares y cuantos impares.
    Sumar los pares y hallar el producto de los impares
    Presentar en pantalla:

   número de elementos par: ... // Suma:
   número de elementos impar: ... // Producto:


Versión 2: Pedir números enteros en pantalla hasta que introduzcas -1, momento en el cual se presentan los resultados en pantalla y se acaba el bucle.

'''
total = 0     # Suma de los números pares
producto = 1  # Producto de los impares (empieza en 1, el neutro de la multiplicación)
par = 0       # Cantidad de números pares
impar = 0     # Cantidad de números impares
# Pedimos exactamente 6 números
for i in range(6):
    resp = int(input(f"Introduce el numero"))
    # Si el resto entre 2 es 0, el número es par
    if(resp%2 == 0):
        par +=1
        total += resp
    else:
        impar +=1
        producto = producto *resp

# Mostramos los resultados
print("La suma de los pares es {} y hay {} pares".format(total,par))
print(f"El producto de los impares es {producto} y hay {impar} impares")

# =============================================================================
# jorgeVazquez1_bucles_v2.py
# Versión 2 (bucle while): pide números hasta que se introduce -1. Cuenta
# pares e impares, suma los pares y multiplica los impares.
# =============================================================================

resp = 0      # Último número introducido (inicializado para entrar en el while)
total = 0     # Suma de los números pares
producto = 1  # Producto de los impares
par = 0       # Cantidad de números pares
impar = 0     # Cantidad de números impares

# Repetimos hasta que el usuario introduzca -1
while resp != -1:
    resp = int(input("Introduce un numero [-1 para terminar]"))
    if(resp == -1):
           # El -1 solo marca el final, no se cuenta como número
           print("Saliendo")
    elif(resp%2 == 0):
            par +=1
            total += resp
    else:
            impar +=1
            producto = producto *resp

# Mostramos los resultados
print("La suma de los pares es {} y hay {} pares".format(total,par))
print(f"El producto de los impares es {producto} y hay {impar} impares")

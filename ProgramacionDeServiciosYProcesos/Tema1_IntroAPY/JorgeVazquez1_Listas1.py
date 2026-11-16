# =============================================================================
# JorgeVazquez1_Listas1.py
# Ejercicio de introducción a listas: máximo, media, inserción, sublistas
# (de forma manual y con slicing) y lista de números aleatorios.
# =============================================================================
import random  # Para generar números aleatorios en la última parte
'''
Crear una lista con 20 números y sobre ella:

    Hallar el máximo de los números
    La media de los números
    Insertar la palabra “hola” en la posición 5 e imprimir la lista en pantalla
    Hacer una sublista de las posiciones 6 a la 12
    Hacer una sublista con los 4 últimos elementos.


'''

media = 0  # Acumulador de la suma (después se convierte en la media)
lst = [2,45,3,65,34,12,31,4,54,6,20,11,23,26,90,16,10,31,71,20]  # Lista de 20 números


# max() devuelve el mayor elemento de la lista
print("El maximo es {}".format(max(lst)))

# Sumamos todos los elementos de la lista
for d in lst:
    media += d
# NOTA: '%' es el RESTO de la división, no la división.
# Para calcular la media debería ser: media = media / len(lst)
media = media % len(lst)
print("La media es {}".format(media))

# insert(posición, valor): mete 'Hola' en el índice 5 y desplaza el resto
lst.insert(5,'Hola')
print("Lista")
# Recorremos la lista e imprimimos cada elemento
for d in lst:
     print(d)

# --- Sublista de las posiciones 6 a 12, hecha a mano (sin slicing) ---
lst2 = []
pos = 0  # Posición donde insertamos dentro de la nueva lista

# range(6,12) recorre los índices 6..11 (el 12 no se incluye)
for d in range(6,12):
     lst2.insert(pos,lst[d])
     pos += 1
# Alternativa equivalente usando slicing:
'''
lst2 = lst[6:12]
'''
print("Lista 2")
for d in lst2:
     print(d)

# --- Sublista con los 4 últimos elementos, hecha a mano ---
lst3 = []
pos = 0

# Recorremos desde len-4 hasta el último índice
for d in range(len(lst)-4,len(lst)):
     lst3.insert(pos,lst[d])
     pos += 1
# Alternativa equivalente usando índices negativos:
'''
lst3 = lst[-4 :]
'''
print("Lista 3")
for d in lst3:
     print(d)


# Creacion de una lista de manera aleatoria
listaRandom = []
# Añadimos 20 números aleatorios entre 1 y 20 (ambos incluidos)
for i in range(20):
     listaRandom.append(random.randint(1,20))

print("Lista random")

# Mostramos la lista aleatoria generada
for i in listaRandom:
     print(i)

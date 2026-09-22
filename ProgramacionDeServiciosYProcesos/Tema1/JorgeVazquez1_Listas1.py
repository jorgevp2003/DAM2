import random
'''
Crear una lista con 20 números y sobre ella:

    Hallar el máximo de los números
    La media de los números
    Insertar la palabra “hola” en la posición 5 e imprimir la lista en pantalla
    Hacer una sublista de las posiciones 6 a la 12
    Hacer una sublista con los 4 últimos elementos.


'''

media = 0
lst = [2,45,3,65,34,12,31,4,54,6,20,11,23,26,90,16,10,31,71,20]


print("El maximo es {}".format(max(lst)))

for d in lst:
    media += d
media = media % len(lst)
print("La media es {}".format(media))

lst.insert(5,'Hola')
print("Lista")
for d in lst:
     print(d)

lst2 = []
pos = 0

for d in range(6,12):
     lst2.insert(pos,lst[d])
     pos += 1
'''
lst2 = lst[6:12]
'''
print("Lista 2")
for d in lst2:
     print(d)

lst3 = []
pos = 0

for d in range(len(lst)-4,len(lst)):
     lst3.insert(pos,lst[d])
     pos += 1
'''
lst3 = lst[-4 :]
'''
print("Lista 3")
for d in lst3:
     print(d)


# Creacion de una lista de manera aleatoria
listaRandom = []
for i in range(20):
     listaRandom.append(random.randint(1,20))

print("Lista random")

for i in listaRandom:
     print(i)
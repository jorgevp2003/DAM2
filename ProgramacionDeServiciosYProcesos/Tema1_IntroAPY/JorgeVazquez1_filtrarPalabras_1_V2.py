import unicodedata

r = open("texto.txt", "r",encoding="UTF-8")
cont = r.read()
exit = False
total = 0
filtro = '¡!¿?_-,.'
palabras = []
veces = []
esta = False
def quitar_acentos(texto):
    return''.join(
        #Convertimos por ejemplo qué en [q u e ´] con NFD
        c for c in unicodedata.normalize('NFD', texto) 
        #Si tiene acento devuelve "Mn" y borramos ese caracter
        if unicodedata.category(c)!= 'Mn'
        #Lo que vuelve es que y no qué
    )


for i in cont.split():#Por cada palabra en el fichero
    #Compruebo que si es la primera palabra la añado si o si
    if len(palabras) == 0:
        palabras.append(quitar_acentos(i.strip(filtro)))
        veces.append(1)
        esta = True
    
    else : 
        #Compruebo si la palabra esta ya en nuestra lista de palabras
        for b in palabras:
            if  quitar_acentos(b.strip(filtro)) == quitar_acentos(i.strip(filtro)):
                pos = palabras.index(quitar_acentos(i.strip(filtro)))
                veces[pos] += 1
                esta = True
    #Si la palabra no ha sido encontrada en la lista
    if esta != True:
         palabras.append(quitar_acentos(i.strip(filtro)))
         veces.append(1)
    esta = False



for i in range(len(palabras)): 
    print(palabras[i], veces[i])

    

       
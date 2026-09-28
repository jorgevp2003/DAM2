import unicodedata

r = open("texto.txt", "r",encoding="UTF-8")
cont = r.read()
exit = False
total = 0
-1
def quitar_acentos(texto):
    return''.join(
        #Convertimos por ejemplo qué en [q u e ´] con NFD
        c for c in unicodedata.normalize('NFD', texto) 
        #Si tiene acento devuelve "Mn" y borramos ese caracter
        if unicodedata.category(c)!= 'Mn'
        #Lo que vuelve es que y no qué
    )


while exit != True :
    resp = input("Introduce la palabra a buscar o -1 para salir:")
    if resp != ('-1'):
        # Split para convertir los caracteres en sus palabras unificadas.
        for i in cont.split():
           #strip para eliminar los caracteres dentro de " "
            i = i.strip("-.,¿?!¡-_")
            # casefold() para que sea todo minuscula
            if quitar_acentos(i.casefold()) == quitar_acentos(resp.casefold()):
                total += 1

        print(f"La palabra {resp} aparece {total} veces")
        total = 0
    else :
        exit = True
print("Adios")
# =============================================================================
# JorgeVazquez1_filtrarPalabras_1.py
# Lee 'texto.txt' y, en bucle, pide una palabra al usuario y cuenta cuántas
# veces aparece en el texto, sin distinguir mayúsculas ni tildes.
# Se sale escribiendo -1.
# =============================================================================
import unicodedata  # Para normalizar el texto y poder quitar las tildes

# Abrimos el fichero en modo lectura y cargamos todo su contenido en 'cont'
r = open("texto.txt", "r",encoding="UTF-8")
cont = r.read()
exit = False  # Bandera de salida del bucle (NOTA: este nombre tapa la función exit() de Python)
total = 0     # Contador de apariciones de la palabra buscada
-1            # NOTA: expresión suelta que no hace nada, se puede borrar

def quitar_acentos(texto):
    """Devuelve el texto recibido sin tildes ni otros diacríticos."""
    return''.join(
        #Convertimos por ejemplo qué en [q u e ´] con NFD
        c for c in unicodedata.normalize('NFD', texto)
        #Si tiene acento devuelve "Mn" y borramos ese caracter
        if unicodedata.category(c)!= 'Mn'
        #Lo que vuelve es que y no qué
    )


# Bucle principal: se repite hasta que el usuario escriba -1
while exit != True :
    resp = input("Introduce la palabra a buscar o -1 para salir:")
    if resp != ('-1'):
        # Split para convertir los caracteres en sus palabras unificadas.
        for i in cont.split():
           #strip para eliminar los caracteres dentro de " "
            i = i.strip("-.,¿?!¡-_")
            # casefold() para que sea todo minuscula
            # Comparamos ambas palabras en minúscula y sin tildes
            if quitar_acentos(i.casefold()) == quitar_acentos(resp.casefold()):
                total += 1

        print(f"La palabra {resp} aparece {total} veces")
        total = 0  # Reiniciamos el contador para la siguiente búsqueda
    else :
        exit = True  # El usuario ha pedido salir
print("Adios")

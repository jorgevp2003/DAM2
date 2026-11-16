# =============================================================================
# JorgeVazquez1_filtrarPalabras_1_V2.py
# Versión 2: lee 'texto.txt' y cuenta cuántas veces aparece CADA palabra.
# Usa dos listas paralelas: palabras[i] es la palabra y veces[i] su contador.
# =============================================================================
import unicodedata  # Para normalizar el texto y poder quitar las tildes

# Abrimos el fichero y cargamos todo su contenido en 'cont'
r = open("texto.txt", "r",encoding="UTF-8")
cont = r.read()
exit = False          # NOTA: no se usa en esta versión
total = 0             # NOTA: no se usa en esta versión
filtro = '¡!¿?_-,.'   # Signos que se quitan del principio y final de cada palabra
palabras = []         # Palabras distintas encontradas
veces = []            # Apariciones de cada palabra (mismo índice que 'palabras')
esta = False          # Indica si la palabra actual ya estaba registrada
def quitar_acentos(texto):
    """Devuelve el texto recibido sin tildes ni otros diacríticos."""
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
        esta = True  # Marcada como encontrada para que no se añada otra vez abajo

    else :
        #Compruebo si la palabra esta ya en nuestra lista de palabras
        for b in palabras:
            if  quitar_acentos(b.strip(filtro)) == quitar_acentos(i.strip(filtro)):
                # Buscamos su índice y sumamos 1 a su contador
                pos = palabras.index(quitar_acentos(i.strip(filtro)))
                veces[pos] += 1
                esta = True
    #Si la palabra no ha sido encontrada en la lista
    # NOTA: no se usa casefold(), así que "Hola" y "hola" cuentan como distintas
    if esta != True:
         palabras.append(quitar_acentos(i.strip(filtro)))
         veces.append(1)
    esta = False  # Reiniciamos la bandera para la siguiente palabra



# Mostramos cada palabra junto a su número de apariciones
for i in range(len(palabras)):
    print(palabras[i], veces[i])

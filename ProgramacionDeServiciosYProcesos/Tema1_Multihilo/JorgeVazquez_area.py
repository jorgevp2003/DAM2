# =============================================================================
# JorgeVazquez_area.py
# Cálculo de áreas con HILOS: se lanzan 4 hilos (2 rectángulos y 2 triángulos)
# que suman su área en una variable compartida (atributo de clase
# area.areaTotal). Como se hace join() tras cada start(), los hilos se
# ejecutan uno detrás de otro y no hay condición de carrera.
# =============================================================================
import threading

class area():
    """Clase usada como contenedor de la variable compartida entre hilos."""
    areaTotal = 0

def calculoRectangulo(x,y,i):
    """Hilo: suma al total el área de un rectángulo (base * altura)."""
    print(f'Calculando rectangulo{i}', flush=True)
    area.areaTotal += x*y
    print(area.areaTotal)

def calcularTriangulo(x,y,i):
    """Hilo: suma al total el área de un triángulo (base * altura / 2)."""
    print(f'Calculando triangulo {i}', flush=True)
    area.areaTotal += (x*y)/2
    print(area.areaTotal)

def main():
    """Lanza los 4 hilos con sus medidas y muestra el área total."""
    x,y= 0,0
    for i in range(0,4):
        # i = 0 y 1 -> rectángulos
        if i<2 :
            if i == 1:
                x = 8
                y = 12
            else:
                x = 6
                y = 5

            t = threading.Thread(target=calculoRectangulo, args=(x,y,i))
            t.start()
            t.join()  # Esperamos a que termine antes de lanzar el siguiente


        # i = 2 y 3 -> triángulos
        else:
            if i == 3:
                x = 10
                y = 12
            else:
                x = 2
                y = 5
            t = threading.Thread(target=calcularTriangulo, args=(x,y,i))
            t.start()
            t.join()

    print(f'Area total : {area.areaTotal}', flush=True)


main()

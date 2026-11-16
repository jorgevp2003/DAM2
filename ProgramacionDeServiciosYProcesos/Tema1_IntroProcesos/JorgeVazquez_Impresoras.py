# =============================================================================
# JorgeVazquez_Impresoras.py
# Simulación de 5 impresoras (PROCESOS) que comparten una cola de trabajos.
# El usuario escribe textos que se meten en 'cola_trabajos'; cada impresora
# coge uno y, si "funciona bien", lo imprime y deja "Ok" en 'cola_resultados';
# si falla, devuelve el trabajo a la cola para que otra lo intente.
# Escribiendo "None" se envía la señal de parada a todas las impresoras.
# =============================================================================
import random
from multiprocessing import Process, Queue



def impresora(num,cola_trabajo,cola_resultado):
    """Proceso impresora: consume trabajos de la cola hasta recibir "None"."""
    while True:
        trabajo = cola_trabajo.get()  # Bloquea hasta que haya un trabajo
        if trabajo == "None":
            # Volvemos a meter "None" para que las demás impresoras también lo reciban y paren
            cola_trabajo.put("None")
            break
        if(funcionaBien()):
            cola_resultado.put("Ok")
            print(f"Impresora {num} ha impreso el mensaje {trabajo}")
        else:
            # Ha fallado: devolvemos el trabajo a la cola para reintentarlo
            cola_trabajo.put(trabajo)




def funcionaBien():
    """Simula si la impresora funciona: True con probabilidad 1/6."""
    i = random.randint(0, 5)
    if i == 3:
        return(True)
    else:
        return(False)

# Programa principal (protegido para que los procesos hijos no lo ejecuten)
if __name__ == "__main__":

    cola_trabajos = Queue()    # Trabajos pendientes de imprimir
    cola_resultados = Queue()  # Un "Ok" por cada trabajo impreso

    # Arrancamos 5 procesos impresora
    procesos = []
    for i in range(5):
        p = Process(target=impresora,args=(i,cola_trabajos,cola_resultados))
        p.start()
        procesos.append(p)
    resp = True

    # Bucle de lectura de trabajos del usuario
    while resp == True:
        msg = input("Introduce que quieres imprimir. (None para finalizar cerrando los procesos)")
        if(msg!="None" ):
            cola_trabajos.put(msg)
            # Mostramos el estado de las dos colas
            print(f"Cola trabajo: {cola_trabajos.qsize()}")
            print(f"Cola resultados {cola_resultados.qsize()}")
        else:
            # Enviamos la señal de parada y salimos
            # NOTA: si quedan trabajos en la cola, "None" puede adelantarlos y quedarse sin imprimir
            cola_trabajos.put(msg)
            break

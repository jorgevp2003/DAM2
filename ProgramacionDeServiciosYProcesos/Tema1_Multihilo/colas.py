# =============================================================================
# colas.py
# Implementación propia de una cola FIFO con capacidad máxima de 50
# elementos. La usa JorgeVazquez_concesionario.py como cola compartida
# entre los hilos productores (líneas) y consumidores (concesionarios).
# =============================================================================
import threading
class cola():
    """Cola FIFO: se inserta por el principio y se extrae por el final."""

    def __init__(self,):
        self.lista = []                # Elementos de la cola
        # NOTA: el lock se crea pero no se usa; para que sea segura entre hilos
        # habría que envolver get() y put() con 'with self.lock:'
        self.lock = threading.Lock()

    def get(self):
        """Saca y devuelve el elemento más antiguo (None si está vacía)."""
        if(len(self.lista)>0):
             return self.lista.pop()

    def put(self,cosa):
        """Añade un elemento al principio si la cola no está llena (máx. 50)."""
        if(len(self.lista)<50):
                   self.lista.insert(0,cosa)

    def isEmpty(self):
         """Devuelve True si la cola está vacía."""
         if len(self.lista)>0:
              return False
         else:
              return True

    def qSize(self):
             """Devuelve el número de elementos que hay en la cola."""
             return len(self.lista)

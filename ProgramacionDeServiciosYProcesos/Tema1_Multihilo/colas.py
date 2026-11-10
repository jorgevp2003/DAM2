import threading
class cola():
    
    def __init__(self,):
        self.lista = []
        self.lock = threading.Lock()
        
    def get(self):
        if(len(self.lista)>0):
             return self.lista.pop()

    def put(self,cosa):
        if(len(self.lista)<50):
                   self.lista.insert(0,cosa)

    def isEmpty(self):
         if len(self.lista)>0:
              return False
         else:
              return True

    def qSize(self):
             return len(self.lista)
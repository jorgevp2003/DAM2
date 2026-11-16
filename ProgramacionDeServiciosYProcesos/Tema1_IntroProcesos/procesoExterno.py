# =============================================================================
# procesoExterno.py
# Script auxiliar que ejecuta JorgeVazquez_procesos_2.py como proceso externo.
# Genera un número aleatorio entre 0 y 20, espera 1 s y lo escribe por la
# salida estándar para que el proceso padre lo lea.
# =============================================================================
import random
import time
import sys


a = random.randint(0,20);  # Número aleatorio entre 0 y 20 (ambos incluidos)
time.sleep(1)              # Simula que el proceso tarda en trabajar
sys.stdout.write(str(a))   # Se escribe sin salto de línea para que el padre lo convierta a int

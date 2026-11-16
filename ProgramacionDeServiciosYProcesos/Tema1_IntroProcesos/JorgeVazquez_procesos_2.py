# =============================================================================
# JorgeVazquez_procesos_2.py
# Lanza repetidamente el script externo 'procesoExterno.py' con subprocess,
# lee el número aleatorio que imprime por su salida estándar y repite hasta
# obtener un 7. Al final muestra cuántos intentos han hecho falta.
# Flujo: main() -> Popen("python procesoExterno.py") -> lee stdout (PIPE)
# =============================================================================
import procesoExterno  # NOTA: no hace falta; importarlo ejecuta el script una vez (1 s de espera)
import subprocess as sb


def main():
    """Ejecuta procesoExterno.py hasta que devuelva 7 y cuenta los intentos."""
    salida = 0  # Último número recibido del proceso externo
    inte = 0    # Número de intentos
    while salida != 7 :
        # Lanzamos el proceso externo redirigiendo su salida a una tubería (PIPE)
        proc = sb.Popen(["python","procesoExterno.py"], stdout=sb.PIPE)
        # Leemos los bytes de la salida, los decodificamos (cp850 = consola de Windows) y pasamos a int
        salida = (int) (proc.stdout.read().decode("cp850"))
        print(salida)
        print("===============  ")
        inte += 1

    print(f"Intentos: {inte}")

if __name__ == "__main__" :
    main()


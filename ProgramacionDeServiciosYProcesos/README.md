# Comandos por import

## Índice
- [threading](#threading)
- [multiprocessing](#multiprocessing)
- [queue](#queue)
- [subprocess](#subprocess)
- [os](#os)
- [sys](#sys)
- [time](#time)
- [random](#random)
- [collections (deque)](#collections-deque)
- [unicodedata](#unicodedata)

---

## threading
```python
import threading
```

### Crear un hilo
| Comando | Qué hace |
|---|---|
| `threading.Thread(target=f)` | Crea un hilo que ejecutará `f` |
| `threading.Thread(target=f, args=(1,))` | Le pasa argumentos (tupla, con coma) |
| `threading.Thread(target=f, kwargs={"x": 1})` | Le pasa argumentos con nombre |
| `threading.Thread(target=f, name="H1")` | Le da un nombre |
| `threading.Thread(target=f, daemon=True)` | Hilo demonio: muere al acabar el principal |
| `class MiHilo(threading.Thread)` + `def run(self)` | Hilo como clase (llamar a `super().__init__()`) |

### Métodos del hilo (`t`)
| Comando | Qué hace |
|---|---|
| `t.start()` | Inicia el hilo |
| `t.join()` | Espera a que termine |
| `t.join(2)` | Espera como máximo 2 segundos |
| `t.is_alive()` | `True` si sigue ejecutándose |
| `t.run()` | Código que ejecuta el hilo (se redefine en la clase; no llamarlo directo) |
| `t.name` | Nombre del hilo |
| `t.ident` | Identificador del hilo |
| `t.native_id` | Id del hilo en el sistema operativo |
| `t.daemon` | `True`/`False`, se asigna antes de `start()` |

### Funciones del módulo
| Comando | Qué hace |
|---|---|
| `threading.current_thread()` | Hilo que se está ejecutando |
| `threading.main_thread()` | Hilo principal |
| `threading.active_count()` | Número de hilos vivos |
| `threading.enumerate()` | Lista de hilos vivos |
| `threading.get_ident()` | Id del hilo actual |
| `threading.get_native_id()` | Id del hilo actual en el SO |
| `threading.local()` | Datos propios de cada hilo (`datos.x = 1`) |

### Lock / RLock
| Comando | Qué hace |
|---|---|
| `lock = threading.Lock()` | Crea un cerrojo |
| `lock = threading.RLock()` | Cerrojo reentrante (el mismo hilo puede cogerlo varias veces) |
| `lock.acquire()` | Lo coge (espera si está cogido) |
| `lock.acquire(timeout=2)` | Lo intenta coger 2 s, devuelve `True`/`False` |
| `lock.release()` | Lo suelta |
| `lock.locked()` | `True` si está cogido |
| `with lock:` | Lo coge y lo suelta automáticamente |

### Semaphore
| Comando | Qué hace |
|---|---|
| `sem = threading.Semaphore(3)` | Deja pasar a 3 hilos a la vez |
| `sem = threading.BoundedSemaphore(3)` | Igual, pero da error si se suelta de más |
| `sem.acquire()` | Ocupa un hueco (espera si no hay) |
| `sem.release()` | Libera un hueco |
| `with sem:` | Ocupa y libera automáticamente |

### Event
| Comando | Qué hace |
|---|---|
| `ev = threading.Event()` | Crea una señal (empieza desactivada) |
| `ev.set()` | Activa la señal y despierta a los que esperan |
| `ev.clear()` | Desactiva la señal |
| `ev.is_set()` | `True` si está activada |
| `ev.wait()` | Espera hasta que se active |
| `ev.wait(5)` | Espera máx. 5 s, devuelve `True`/`False` |

### Condition
| Comando | Qué hace |
|---|---|
| `cond = threading.Condition()` | Crea una condición (lleva un lock dentro) |
| `with cond:` | Coge el lock de la condición |
| `cond.wait()` | Suelta el lock y espera a un `notify` |
| `cond.wait(5)` | Igual, máximo 5 s |
| `cond.wait_for(lambda: x > 0)` | Espera hasta que se cumpla la condición |
| `cond.notify()` | Despierta a un hilo que espera |
| `cond.notify(n)` | Despierta a n hilos |
| `cond.notify_all()` | Despierta a todos |

### Barrier
| Comando | Qué hace |
|---|---|
| `bar = threading.Barrier(3)` | Barrera para 3 hilos |
| `bar.wait()` | Espera a que lleguen los 3 |
| `bar.reset()` | Reinicia la barrera |
| `bar.abort()` | La rompe (los que esperan reciben `BrokenBarrierError`) |
| `bar.parties` | Número de hilos necesarios |
| `bar.n_waiting` | Hilos esperando ahora |

### Timer
| Comando | Qué hace |
|---|---|
| `tm = threading.Timer(5, f, args=(1,))` | Ejecuta `f` dentro de 5 segundos |
| `tm.start()` | Arranca la cuenta atrás |
| `tm.cancel()` | La cancela |

---

## multiprocessing
```python
import multiprocessing as mp
from multiprocessing import Process, Queue, Pipe, Lock, Value, Array, Manager, Pool
```
> Todo lo que crea procesos va dentro de `if __name__ == "__main__":`

### Crear un proceso
| Comando | Qué hace |
|---|---|
| `mp.Process(target=f)` | Crea un proceso que ejecutará `f` |
| `mp.Process(target=f, args=(1,))` | Le pasa argumentos (tupla, con coma) |
| `mp.Process(target=f, kwargs={"x": 1})` | Argumentos con nombre |
| `mp.Process(target=f, name="P1")` | Le da un nombre |
| `mp.Process(target=f, daemon=True)` | Muere al acabar el padre |
| `class MiProc(mp.Process)` + `def run(self)` | Proceso como clase |

### Métodos del proceso (`p`)
| Comando | Qué hace |
|---|---|
| `p.start()` | Inicia el proceso |
| `p.join()` | Espera a que termine |
| `p.join(2)` | Espera como máximo 2 s |
| `p.is_alive()` | `True` si sigue vivo |
| `p.terminate()` | Lo termina (SIGTERM) |
| `p.kill()` | Lo mata (SIGKILL) |
| `p.close()` | Libera sus recursos (ya terminado) |
| `p.run()` | Código que ejecuta (se redefine en la clase) |
| `p.pid` | PID del proceso |
| `p.name` | Nombre |
| `p.exitcode` | `None` vivo, `0` OK, negativo si lo mataron |
| `p.daemon` | `True`/`False`, antes de `start()` |

### Funciones del módulo
| Comando | Qué hace |
|---|---|
| `mp.current_process()` | Proceso actual (`.pid`, `.name`) |
| `mp.parent_process()` | Proceso padre (`None` en el principal) |
| `mp.active_children()` | Lista de hijos vivos |
| `mp.cpu_count()` | Número de núcleos |
| `mp.set_start_method("spawn")` | Forma de crear procesos (`spawn`, `fork`, `forkserver`) |
| `mp.get_start_method()` | Forma actual |

### Queue
| Comando | Qué hace |
|---|---|
| `q = mp.Queue()` | Cola entre procesos |
| `q = mp.Queue(50)` | Cola con tamaño máximo |
| `q.put(x)` | Mete (espera si está llena) |
| `q.put(x, timeout=2)` | Espera máx. 2 s (`queue.Full`) |
| `q.put_nowait(x)` | Mete sin esperar |
| `q.get()` | Saca (espera si está vacía) |
| `q.get(timeout=2)` | Espera máx. 2 s (`queue.Empty`) |
| `q.get_nowait()` | Saca sin esperar |
| `q.qsize()` | Tamaño aproximado |
| `q.empty()` | `True` si está vacía |
| `q.full()` | `True` si está llena |
| `q.close()` | Ya no se va a meter nada más |
| `q.join_thread()` | Espera a que se vacíe el buffer interno (tras `close`) |

### JoinableQueue
| Comando | Qué hace |
|---|---|
| `q = mp.JoinableQueue()` | Queue con control de tareas |
| `q.task_done()` | El consumidor marca un elemento como terminado |
| `q.join()` | Espera a que todos estén terminados |

### SimpleQueue
| Comando | Qué hace |
|---|---|
| `q = mp.SimpleQueue()` | Cola básica |
| `q.put(x)` / `q.get()` / `q.empty()` / `q.close()` | Meter / sacar / ¿vacía? / cerrar |

### Pipe
| Comando | Qué hace |
|---|---|
| `a, b = mp.Pipe()` | Tubería bidireccional (dos extremos) |
| `a, b = mp.Pipe(False)` | Unidireccional: `a` solo recibe, `b` solo envía |
| `c.send(x)` | Envía un objeto |
| `c.recv()` | Recibe (espera si no hay nada) |
| `c.poll()` | `True` si hay algo para leer |
| `c.poll(2)` | Espera máx. 2 s a que haya algo |
| `c.send_bytes(b)` / `c.recv_bytes()` | Enviar / recibir bytes |
| `c.close()` | Cierra el extremo |

### Value / Array (memoria compartida)
| Comando | Qué hace |
|---|---|
| `v = mp.Value('i', 0)` | Entero compartido (`'i'` int, `'d'` float, `'c'` char, `'b'` bool/byte) |
| `v.value` | Leer / escribir el valor |
| `v.get_lock()` | Lock propio (`with v.get_lock():`) |
| `a = mp.Array('i', 5)` | Array de 5 enteros a 0 |
| `a = mp.Array('i', [1, 2, 3])` | Array con valores |
| `a[0]`, `a[:]`, `len(a)` | Acceso como una lista |
| `a.get_lock()` | Lock propio |

### Manager
| Comando | Qué hace |
|---|---|
| `m = mp.Manager()` / `with mp.Manager() as m:` | Crea un gestor de objetos compartidos |
| `m.list()` | Lista compartida |
| `m.dict()` | Diccionario compartido |
| `m.Queue()` | Cola compartida |
| `m.Value('i', 0)` / `m.Array('i', [..])` | Valor / array compartido |
| `m.Lock()` / `m.RLock()` / `m.Semaphore(n)` / `m.Event()` / `m.Condition()` / `m.Barrier(n)` | Sincronización compartida |
| `m.Namespace()` | Objeto con atributos compartidos (`ns.x = 1`) |
| `m.shutdown()` | Cierra el gestor |

### Pool
| Comando | Qué hace |
|---|---|
| `pool = mp.Pool(4)` / `with mp.Pool(4) as pool:` | Grupo de 4 procesos trabajadores |
| `pool.map(f, lista)` | Aplica `f` a cada elemento, devuelve lista |
| `pool.starmap(f, [(a, b), ...])` | Igual con varios argumentos |
| `pool.apply(f, (x,))` | Ejecuta `f(x)` y espera el resultado |
| `pool.apply_async(f, (x,))` | Igual sin esperar; `r.get()` da el resultado |
| `pool.map_async(f, lista)` | `map` sin esperar; `r.get()` |
| `pool.imap(f, lista)` | Devuelve resultados poco a poco, en orden |
| `pool.imap_unordered(f, lista)` | Poco a poco, según van acabando |
| `r.get()` / `r.ready()` / `r.wait()` | Resultado / ¿terminado? / esperar |
| `pool.close()` | No admite más tareas |
| `pool.join()` | Espera a los trabajadores (tras `close`) |
| `pool.terminate()` | Para todo inmediatamente |

### Sincronización (igual que en threading)
| Comando | Qué hace |
|---|---|
| `mp.Lock()` / `mp.RLock()` | Cerrojo: `acquire()`, `release()`, `with` |
| `mp.Semaphore(n)` / `mp.BoundedSemaphore(n)` | `acquire()`, `release()`, `with` |
| `mp.Event()` | `set()`, `clear()`, `is_set()`, `wait()` |
| `mp.Condition()` | `wait()`, `wait_for()`, `notify()`, `notify_all()` |
| `mp.Barrier(n)` | `wait()`, `reset()`, `abort()` |

---

## queue
```python
import queue
```
| Comando | Qué hace |
|---|---|
| `queue.Queue()` / `queue.Queue(50)` | Cola FIFO para hilos (con tamaño máximo) |
| `queue.LifoQueue()` | Pila (último en entrar, primero en salir) |
| `queue.PriorityQueue()` | Por prioridad: `put((prioridad, dato))`, menor sale antes |
| `queue.SimpleQueue()` | Cola básica sin tamaño máximo |
| `q.put(x)` / `q.put(x, timeout=2)` / `q.put_nowait(x)` | Meter |
| `q.get()` / `q.get(timeout=2)` / `q.get_nowait()` | Sacar |
| `q.qsize()` / `q.empty()` / `q.full()` | Tamaño / ¿vacía? / ¿llena? |
| `q.task_done()` | Marca un elemento como procesado |
| `q.join()` | Espera a que todo esté procesado |
| `queue.Empty` | Excepción: cola vacía |
| `queue.Full` | Excepción: cola llena |

---

## subprocess
```python
import subprocess as sb
```
### Funciones
| Comando | Qué hace |
|---|---|
| `sb.run(["python", "x.py"])` | Ejecuta y espera; devuelve `CompletedProcess` |
| `sb.run(..., capture_output=True)` | Guarda la salida y los errores |
| `sb.run(..., text=True)` | Trabaja con `str` en lugar de bytes |
| `sb.run(..., input="5\n")` | Le pasa datos por la entrada |
| `sb.run(..., timeout=5)` | Tiempo máximo |
| `sb.run(..., check=True)` | Lanza error si el código de salida no es 0 |
| `sb.run("dir", shell=True)` | Ejecuta a través de la consola |
| `sb.run(..., cwd="carpeta")` | Carpeta donde se ejecuta |
| `sb.check_output([...])` | Ejecuta y devuelve solo la salida |
| `sb.check_call([...])` | Ejecuta; error si falla |
| `sb.call([...])` | Ejecuta y devuelve el código de salida |
| `sb.getoutput("cmd")` | Salida de un comando de consola como `str` |

### Resultado de `run` (`r`)
| Comando | Qué hace |
|---|---|
| `r.stdout` | Salida del programa |
| `r.stderr` | Errores del programa |
| `r.returncode` | Código de salida (0 = OK) |
| `r.args` | Comando ejecutado |

### Popen
| Comando | Qué hace |
|---|---|
| `proc = sb.Popen(["python", "x.py"])` | Lanza sin esperar |
| `sb.Popen(..., stdout=sb.PIPE)` | Captura la salida |
| `sb.Popen(..., stdin=sb.PIPE)` | Permite enviarle datos |
| `sb.Popen(..., stderr=sb.PIPE)` / `stderr=sb.STDOUT` | Captura errores / los junta con la salida |
| `sb.DEVNULL` | Descarta la salida |
| `proc.communicate()` | Espera y devuelve `(salida, errores)` |
| `proc.communicate(input="5\n")` | Envía datos, espera y devuelve |
| `proc.stdout.read()` | Lee toda la salida (bytes → `.decode("cp850")`) |
| `proc.stdout.readline()` | Lee una línea |
| `proc.stdin.write(...)` | Escribe en la entrada del hijo |
| `proc.wait()` / `proc.wait(5)` | Espera a que termine |
| `proc.poll()` | `None` si sigue vivo, si no el código de salida |
| `proc.terminate()` / `proc.kill()` | Termina / mata |
| `proc.pid` | PID |
| `proc.returncode` | Código de salida |

### Excepciones
| Comando | Qué hace |
|---|---|
| `sb.CalledProcessError` | El programa acabó con error (`check=True`) |
| `sb.TimeoutExpired` | Superó el `timeout` |

---

## os
```python
import os
```
| Comando | Qué hace |
|---|---|
| `os.getpid()` | PID del proceso actual |
| `os.getppid()` | PID del proceso padre |
| `os.cpu_count()` | Número de núcleos |
| `os.kill(pid, signal.SIGTERM)` | Envía una señal a un proceso |
| `os.system("cls")` | Ejecuta un comando de consola |
| `os.getcwd()` | Carpeta actual |
| `os.chdir("ruta")` | Cambia de carpeta |
| `os.listdir(".")` | Lista ficheros |
| `os.mkdir("x")` / `os.makedirs("a/b")` | Crea carpeta(s) |
| `os.remove("f.txt")` | Borra un fichero |
| `os.rename("a", "b")` | Renombra |
| `os.path.exists("f.txt")` | ¿Existe? |
| `os.path.isfile(p)` / `os.path.isdir(p)` | ¿Es fichero? / ¿Es carpeta? |
| `os.path.join("a", "b.txt")` | Une rutas |
| `os.path.getsize(p)` | Tamaño en bytes |
| `os.path.basename(p)` / `os.path.dirname(p)` | Nombre / carpeta de una ruta |
| `os.environ["VAR"]` / `os.getenv("VAR")` | Variables de entorno |

---

## sys
```python
import sys
```
| Comando | Qué hace |
|---|---|
| `sys.argv` | Argumentos del programa (`sys.argv[1]`...) |
| `sys.executable` | Ruta del Python actual (para `subprocess`) |
| `sys.stdout.write("txt")` | Escribe en la salida (sin salto de línea) |
| `sys.stdout.flush()` | Fuerza a mostrar la salida |
| `sys.stdin.readline()` | Lee una línea de la entrada |
| `sys.stdin.read()` | Lee toda la entrada |
| `sys.stderr.write("err")` | Escribe en la salida de errores |
| `sys.exit()` / `sys.exit(1)` | Termina el programa (con código de salida) |
| `sys.platform` | Sistema operativo (`win32`, `linux`...) |
| `sys.path` | Rutas donde busca los módulos |

---

## time
```python
import time
```
| Comando | Qué hace |
|---|---|
| `time.sleep(0.5)` | Pausa 0,5 segundos |
| `time.time()` | Segundos desde 1970 (para medir: fin − inicio) |
| `time.perf_counter()` | Contador preciso para medir tiempos |
| `time.monotonic()` | Reloj que nunca va hacia atrás |
| `time.process_time()` | Tiempo de CPU del proceso |
| `time.ctime()` | Fecha y hora como texto |
| `time.localtime()` | Fecha y hora como estructura |
| `time.strftime("%H:%M:%S")` | Fecha/hora con formato |

---

## random
```python
import random
```
| Comando | Qué hace |
|---|---|
| `random.randint(1, 10)` | Entero entre 1 y 10 (ambos incluidos) |
| `random.randrange(0, 10, 2)` | Entero del rango (10 no incluido, de 2 en 2) |
| `random.random()` | Decimal entre 0 y 1 |
| `random.uniform(1.5, 3.5)` | Decimal entre dos valores |
| `random.choice(lista)` | Un elemento al azar |
| `random.choices(lista, k=3)` | 3 elementos (pueden repetirse) |
| `random.choices(lista, weights=[7, 3])` | Al azar con pesos/probabilidades |
| `random.sample(lista, 3)` | 3 elementos sin repetir |
| `random.shuffle(lista)` | Desordena la lista |
| `random.seed(1)` | Fija la semilla (mismos resultados siempre) |

---

## collections (deque)
```python
from collections import deque
```
| Comando | Qué hace |
|---|---|
| `d = deque()` / `deque([1, 2])` | Crea una cola doble |
| `deque(maxlen=50)` | Con tamaño máximo (descarta los más antiguos) |
| `d.append(x)` | Añade por la derecha |
| `d.appendleft(x)` | Añade por la izquierda |
| `d.pop()` | Saca por la derecha |
| `d.popleft()` | Saca por la izquierda (FIFO con `append`) |
| `d.extend(l)` / `d.extendleft(l)` | Añade varios |
| `d.rotate(1)` | Rota los elementos |
| `d.clear()` | Vacía |
| `d.count(x)` / `d.remove(x)` | Cuenta / borra un valor |
| `len(d)` | Tamaño |

---

## unicodedata
```python
import unicodedata
```
| Comando | Qué hace |
|---|---|
| `unicodedata.normalize('NFD', txt)` | Separa letras y tildes (`é` → `e` + `´`) |
| `unicodedata.normalize('NFC', txt)` | Las vuelve a juntar |
| `unicodedata.category(c)` | Categoría del carácter (`'Mn'` = tilde/diacrítico) |
| `unicodedata.name(c)` | Nombre del carácter |

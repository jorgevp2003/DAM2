package com.ejemplo.util;

import com.ejemplo.util.ValidadorDni;

import es.ciudadescolar.componentes.CampoTexto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

// Importamos las assertions de JUnit 5.
import static org.junit.jupiter.api.Assertions.*;
// Importamos las clases necesarias para coordinar hilos.
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
// Importamos las anotaciones de JUnit 5.
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
// Importamos la plataforma JavaFX.
import javafx.application.Platform;

class ValidadorDniTest {
@Test
void dniCorrectoDebeSerValido() {
assertTrue(ValidadorDni.esValido("12345678Z"));
}
@Test
void letraIncorrectaDebeSerInvalida() {
assertFalse(ValidadorDni.esValido("12345678A"));
}
@Test
void formatoIncorrectoDebeSerInvalido() {
assertFalse(ValidadorDni.esValido("1234"));
}
@Test
void dniVacioDebeSerInvalido() {
assertFalse(ValidadorDni.esValido(""));
}
@Test
void dniNuloDebeSerInvalido() {
assertFalse(ValidadorDni.esValido(null));
}


@BeforeAll
static void iniciarJavaFX() throws Exception {
// Creamos un contador que permitirá esperar a que JavaFX arranque.
CountDownLatch latch = new CountDownLatch(1);
// Guardamos posibles errores producidos durante la inicialización.
AtomicReference<Throwable> error = new AtomicReference<>();
// Creamos un hilo independiente para iniciar JavaFX.
Thread hilo = new Thread(() -> {
try {
// Iniciamos JavaFX y avisamos cuando esté preparado.
Platform.startup(latch::countDown);
} catch (Throwable t) {
// Guardamos el error si JavaFX no consigue iniciarse.
error.set(t);
// Liberamos la espera para que el programa pueda continuar.
latch.countDown();
}
});
// Indicamos que el hilo no impedirá finalizar el programa.
hilo.setDaemon(true);
// Ponemos en marcha el hilo.
hilo.start();
// Esperamos un máximo de 10 segundos a que JavaFX se inicie.
assertTrue(
latch.await(10, TimeUnit.SECONDS),
"JavaFX no se ha iniciado"
);
// Si se ha producido un error, hacemos que JUnit lo detecte.
if (error.get() != null) {
throw new AssertionError(
"No se pudo inicializar JavaFX",
error.get()
);
}
}

/*
* MÉTODO AUXILIAR:
* Ejecuta una prueba en el hilo de JavaFX.
*
* Los componentes gráficos de JavaFX deben crearse y utilizarse
* desde su hilo de aplicación, no desde el hilo de JUnit.
*/
private void ejecutarEnJavaFX(Runnable prueba)
throws Exception {
// Creamos un contador para esperar a que termine la prueba.
CountDownLatch latch = new CountDownLatch(1);
// Reservamos una variable para guardar posibles errores.
AtomicReference<Throwable> error = new AtomicReference<>();
// Enviamos la prueba al hilo de aplicación de JavaFX.
Platform.runLater(() -> {
try {
// Ejecutamos el código de la prueba.
prueba.run();
} catch (Throwable t) {
// Guardamos cualquier error o fallo de una aserción.
error.set(t);
} finally {
// Avisamos de que la ejecución ha terminado.
latch.countDown();
}
});
// Esperamos un máximo de 5 segundos a que termine la prueba.
assertTrue(
latch.await(5, TimeUnit.SECONDS),
"La prueba ha excedido el tiempo de espera"
);
// Si se ha producido un error, lo comunicamos a JUnit.
if (error.get() != null) {
throw new AssertionError(
"La prueba ha fallado",
error.get()
);
}
}
/*
* PRUEBA 1:
* Comprueba que podemos introducir un texto en el componente
* y recuperarlo posteriormente.
*/
@Test
void debeGuardarYRecuperarElTexto() throws Exception {
// Ejecutamos la prueba en el hilo de JavaFX.
ejecutarEnJavaFX(() -> {
// Creamos un componente CampoTexto.
CampoTexto campo = new CampoTexto("Nombre");
// Introducimos un texto.
campo.setTexto("Ana");
// Comprobamos que el texto recuperado sea el esperado.
assertEquals("Ana", campo.getTexto());
});
}

/*
* PRUEBA 2:
* Comprueba que un campo obligatorio sin contenido
* no supera la validación.
*/
@Test
void campoObligatorioVacioDebeSerInvalido() throws Exception {
ejecutarEnJavaFX(() -> {
// Creamos el componente.
CampoTexto campo = new CampoTexto("Nombre");
// Indicamos que el campo es obligatorio.
campo.setObligatorio(true);
// Dejamos el campo vacío.
campo.setTexto("");
// Comprobamos que la validación devuelve false.
assertFalse(campo.validar());
});
}
/*
* PRUEBA 3:
* Comprueba que un campo obligatorio con contenido
* supera la validación.
*/
@Test
void campoObligatorioConTextoDebeSerValido() throws Exception {
ejecutarEnJavaFX(() -> {
// Creamos el componente.
CampoTexto campo = new CampoTexto("Nombre");
// Indicamos que el campo es obligatorio.
campo.setObligatorio(true);
// Introducimos un texto.
campo.setTexto("Ana");
// Comprobamos que la validación devuelve true.
assertTrue(campo.validar());
});
}
/*
* PRUEBA 4:
* Comprueba que un campo opcional puede permanecer vacío
* sin que la validación lo considere incorrecto.
*/
@Test
void campoOpcionalVacioDebeSerValido() throws Exception {
ejecutarEnJavaFX(() -> {
// Creamos el componente.
CampoTexto campo = new CampoTexto("Nombre");
// Indicamos que el campo no es obligatorio.
campo.setObligatorio(false);
// Dejamos el campo vacío.
campo.setTexto("");
// Comprobamos que la validación devuelve true.
assertTrue(campo.validar());
});
}

private boolean campoEditable(CampoTexto campo) {
// Buscamos el TextField entre los elementos internos.
return campo.getChildren().stream()
// Seleccionamos el elemento que sea un TextField.
.filter(javafx.scene.control.TextField.class::isInstance)
// Convertimos el elemento al tipo TextField.
.map(javafx.scene.control.TextField.class::cast)
// Obtenemos el primer TextField encontrado.
.findFirst()
// Si no existe, lanzamos una excepción.
.orElseThrow()
// Consultamos si permite la edición.
.isEditable();
}

/*
* PRUEBA 5:
* Comprueba que el campo permite la edición
* cuando establecemos editable a true.
*/
@Test
void debePermitirEditarElCampo() throws Exception {
ejecutarEnJavaFX(() -> {
// Creamos el componente.
CampoTexto campo = new CampoTexto("Nombre");
// Activamos la edición.
campo.setEditable(true);
// Comprobamos que el TextField interno permite editar.
assertTrue(campoEditable(campo));
});
}
/*
* PRUEBA 6:
* Comprueba que podemos impedir la edición del campo
* estableciendo editable a false.
*/
@Test
void debePermitirDesactivarLaEdicion() throws Exception {
ejecutarEnJavaFX(() -> {
// Creamos el componente.
CampoTexto campo = new CampoTexto("Nombre");
// Desactivamos la edición.
campo.setEditable(false);
// Comprobamos que el TextField interno no permite editar.
assertFalse(campoEditable(campo));
});
}





/*
* PRUEBA 7:
* Comprueba que el texto de ayuda se establece
* y se recupera correctamente.
*/
@Test
void debeEstablecerElTextoDeAyuda() throws Exception {
ejecutarEnJavaFX(() -> {
// Creamos el componente.
CampoTexto campo = new CampoTexto("Nombre");
// Establecemos el texto de ayuda.
campo.setTextoAyuda("Escribe tu nombre");
// Comprobamos que devuelve el texto esperado.
assertEquals(
"Escribe tu nombre",
campo.getTextoAyuda()
);
});
}


}

package es.ciudadescolar.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;

/**
 * Pruebas unitarias del componente {@link BotonAccion}.
 *
 * <p>¿Por qué el hilo de JavaFX? Los controles (Button, Tooltip...) solo se
 * pueden crear y modificar en el "JavaFX Application Thread". JUnit ejecuta
 * los tests en otro hilo, así que:</p>
 * <ol>
 *   <li>{@code @BeforeAll}: arranca JavaFX una vez con {@code Platform.startup}
 *       (sin abrir ninguna ventana).</li>
 *   <li>{@code ejecutarEnFX(...)}: envía el código al hilo de JavaFX con
 *       {@code Platform.runLater} y espera con un {@code CountDownLatch}
 *       a que termine. Si un assert falla dentro, el error se guarda y se
 *       relanza en el hilo de JUnit para que el test salga en rojo.</li>
 * </ol>
 * Ejecutar: {@code mvn clean test}
 *
 * @author Jorge Vazquez Poto
 * @version 1.0
 */
public class BotonAccionTest {

    /**
     * Arranca JavaFX una sola vez antes de todas las pruebas.
     *
     * @throws Exception si JavaFX no se inicia
     */
    @BeforeAll
    static void iniciarJavaFX() throws Exception {
        // Contador que permite esperar a que JavaFX arranque.
        CountDownLatch latch = new CountDownLatch(1);
        // Posibles errores producidos durante la inicialización.
        AtomicReference<Throwable> error = new AtomicReference<>();

        Thread hilo = new Thread(() -> {
            try {
                Platform.startup(latch::countDown);
            } catch (IllegalStateException yaIniciado) {
                // JavaFX ya estaba arrancado (otra clase de test): no es un error.
                latch.countDown();
            } catch (Throwable t) {
                error.set(t);
                latch.countDown();
            }
        });
        hilo.setDaemon(true);
        hilo.start();

        assertTrue(latch.await(10, TimeUnit.SECONDS), "JavaFX no se ha iniciado");
        if (error.get() != null) {
            throw new AssertionError("No se pudo inicializar JavaFX", error.get());
        }
    }

    /**
     * Ejecuta el código en el hilo de JavaFX y espera a que termine.
     * Si dentro falla un assert, el fallo se traslada a JUnit.
     */
    private void ejecutarEnFX(Runnable accion) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        AtomicReference<Throwable> error = new AtomicReference<>();

        Platform.runLater(() -> {
            try {
                accion.run();
            } catch (Throwable t) {
                error.set(t);
            } finally {
                latch.countDown();
            }
        });

        assertTrue(latch.await(5, TimeUnit.SECONDS), "El hilo de JavaFX no ha respondido");
        if (error.get() != null) {
            throw new AssertionError("Fallo en el hilo de JavaFX", error.get());
        }
    }

    // 1. Creación del botón con un texto
    @Test
    void crearBotonConTexto() throws InterruptedException {
        ejecutarEnFX(() -> {
            BotonAccion bt = new BotonAccion("Pulsame");
            assertEquals("Pulsame", bt.getTexto());
            assertEquals("Pulsame", bt.getText());
        });
    }

    // Constructor completo: texto, colores y tamaño
    @Test
    void crearBotonCompleto() throws InterruptedException {
        ejecutarEnFX(() -> {
            BotonAccion bt = new BotonAccion("Guardar", "#2e7d32", "white", 14);
            assertEquals("Guardar", bt.getTexto());
            assertEquals("#2e7d32", bt.getColorFondo());
            assertEquals("white", bt.getColorTexto());
            assertEquals(14, bt.getTamañoTexto());
        });
    }

    // 2. Modificación y recuperación del texto
    @Test
    void cambiarTexto() throws InterruptedException {
        ejecutarEnFX(() -> {
            BotonAccion bt = new BotonAccion("Pulsame");
            bt.setTexto("Pulsame por favor");
            assertEquals("Pulsame por favor", bt.getTexto());
        });
    }

    // 3. Cambio del color de fondo
    @Test
    void cambiarColorFondo() throws InterruptedException {
        ejecutarEnFX(() -> {
            BotonAccion bt = new BotonAccion("Pulsame");
            bt.setColorFondo("#5678aa");
            bt.setColorFondo("#236700");
            assertEquals("#236700", bt.getColorFondo());
            assertTrue(bt.getStyle().contains("-fx-background-color: #236700"));
        });
    }

    // 4. Cambio del color del texto
    @Test
    void cambiarColorTexto() throws InterruptedException {
        ejecutarEnFX(() -> {
            BotonAccion bt = new BotonAccion("Pulsame");
            bt.setColorTexto("rgb(44, 162, 5)");
            assertEquals("rgb(44, 162, 5)", bt.getColorTexto());
            assertTrue(bt.getStyle().contains("-fx-text-fill: rgb(44, 162, 5)"));
        });
    }

    // 5. Establecimiento del tamaño de fuente
    @Test
    void cambiarTamanioTexto() throws InterruptedException {
        ejecutarEnFX(() -> {
            BotonAccion bt = new BotonAccion("Pulsame");
            bt.setTamañoTexto(10);
            bt.setTamañoTexto(12);
            assertEquals(12, bt.getTamañoTexto());
            assertTrue(bt.getStyle().contains("-fx-font-size: 12px"));
        });
    }

    // 6. Texto de ayuda (Tooltip)
    @Test
    void cambiarTextoAyuda() throws InterruptedException {
        ejecutarEnFX(() -> {
            BotonAccion bt = new BotonAccion("Pulsame");
            bt.setTextoAyuda("Pulsa para guardar");
            assertEquals("Pulsa para guardar", bt.getTextoAyuda());
            assertNotNull(bt.getTooltip());
            assertEquals("Pulsa para guardar", bt.getTooltip().getText());

            // Texto vacío -> se quita el Tooltip
            bt.setTextoAyuda("");
            assertNull(bt.getTooltip());
        });
    }

    // 7. Activación y desactivación
    @Test
    void activarDesactivarBoton() throws InterruptedException {
        ejecutarEnFX(() -> {
            BotonAccion bt = new BotonAccion("Pulsame");
            assertTrue(bt.isActivo());

            bt.setActivo(false);
            assertFalse(bt.isActivo());
            assertTrue(bt.isDisable());

            bt.setActivo(true);
            assertTrue(bt.isActivo());
            assertFalse(bt.isDisable());
        });
    }

    // 8. Ejecución de una acción al pulsar
    @Test
    void ejecutarAccionAlPulsar() throws InterruptedException {
        ejecutarEnFX(() -> {
            BotonAccion bt = new BotonAccion("Pulsame");
            AtomicBoolean pulsado = new AtomicBoolean(false);
            bt.setOnAction(e -> pulsado.set(true));
            bt.fire();
            assertTrue(pulsado.get());
        });
    }

    // 9a. Rechazo de tamaño negativo
    @Test
    void rechazarTamanioNegativo() throws InterruptedException {
        ejecutarEnFX(() -> {
            BotonAccion bt = new BotonAccion("Pulsame");
            bt.setTamañoTexto(10);
            assertThrows(IllegalArgumentException.class, () -> bt.setTamañoTexto(-1));
            assertEquals(10, bt.getTamañoTexto()); // conserva el valor anterior
        });
    }

    // 9b. Rechazo de tamaño cero
    @Test
    void rechazarTamanioCero() throws InterruptedException {
        ejecutarEnFX(() -> {
            BotonAccion bt = new BotonAccion("Pulsame");
            assertThrows(IllegalArgumentException.class, () -> bt.setTamañoTexto(0));
            assertThrows(IllegalArgumentException.class,
                    () -> new BotonAccion("X", "red", "white", 0));
        });
    }
}

package es.ciudadescolar;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import javafx.application.Platform;

/**
 * Utilidades para probar componentes JavaFX con JUnit 5.
 *
 * Uso en un test:
 *   @BeforeAll static void init() throws Exception { FxTestUtil.iniciarJavaFX(); }
 *   @Test void prueba() throws Exception { FxTestUtil.ejecutarEnFX(() -> { ... asserts ... }); }
 */
public final class FxTestUtil {

    private static boolean iniciado = false;

    private FxTestUtil() { }

    /** Arranca JavaFX una sola vez (aunque lo llamen varias clases de test). */
    public static synchronized void iniciarJavaFX() throws InterruptedException {
        if (iniciado) {
            return;
        }
        CountDownLatch latch = new CountDownLatch(1);
        try {
            Platform.startup(latch::countDown);
        } catch (IllegalStateException yaIniciado) {
            // JavaFX ya estaba arrancado en esta JVM: no pasa nada
            latch.countDown();
        }
        assertTrue(latch.await(10, TimeUnit.SECONDS), "JavaFX no se ha iniciado");
        Platform.setImplicitExit(false);
        iniciado = true;
    }

    /** Ejecuta el código en el hilo de JavaFX y espera a que termine (propaga los fallos a JUnit). */
    public static void ejecutarEnFX(Runnable accion) throws InterruptedException {
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
}

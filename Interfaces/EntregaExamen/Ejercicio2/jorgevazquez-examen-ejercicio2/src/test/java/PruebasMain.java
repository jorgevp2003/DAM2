package test.java;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import javafx.application.Platform;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author Jorge Vazquez
 * @version 1.0
 * NO SE PORQUE NO VA , LO ESTOY INTENDANDO ARREGLAR PERO COMPILA LA **** PRUEBA. 30 MIN LLEVO INTENTADO ARREGLARLO
 */
public class PruebasMain {
    public PruebasMain() {
    }
    ComboEstado comboEstado = new ComboEstado();

    @BeforeAll
    static void iniciarJavaFX() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        AtomicReference<Throwable> error = new AtomicReference();
        Thread hilo = new Thread(() -> {
            try {
                Platform.startup(latch::countDown);
            } catch (IllegalStateException var3) {
                latch.countDown();
            } catch (Throwable t) {
                error.set(t);
                latch.countDown();
            }

        });
        hilo.setDaemon(true);
        hilo.start();
        Assertions.assertTrue(latch.await(10L, TimeUnit.SECONDS), "JavaFX no se ha iniciado");
        if (error.get() != null) {
            throw new AssertionError("No se pudo inicializar JavaFX", (Throwable)error.get());
        }
    }

    private void ejecutarEnFX(Runnable accion) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        AtomicReference<Throwable> error = new AtomicReference();
        Platform.runLater(() -> {
            try {
                accion.run();
            } catch (Throwable t) {
                error.set(t);
            } finally {
                latch.countDown();
            }

        });
        Assertions.assertTrue(latch.await(5L, TimeUnit.SECONDS), "El hilo de JavaFX no ha respondido");
        if (error.get() != null) {
            throw new AssertionError("Fallo en el hilo de JavaFX", (Throwable)error.get());
        }
    }

    @Test
    public void testColorAcorde() {
        
        boolean funciona = false;
        if(comboEstado.getEstado().equals("RESUELTA") && comboEstado.getColor().equals("green")){
            funciona = true;
        }
        assertTrue(funciona);
    }
    @Test
    public void testActualizarColor() {
        comboEstado.actualizarColor("Blue");
        assertEquals("Blue", comboEstado.getColor());
    }

}
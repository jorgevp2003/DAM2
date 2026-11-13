import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import es.ciudadescolar.modelo.BotonAccion;
import javafx.application.Platform;

public class BotonAccionTest {
    /**
     * @author Jorge Vazquez Poto
     * @version 1.0
     * 
     * 
     * @throws Exception
     */
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

    private void ejecutarEnFX(Runnable accion) throws InterruptedException {

    // Contador para esperar a que termine el código enviado a JavaFX.
    CountDownLatch latch = new CountDownLatch(1);

    // Guardamos el posible fallo producido dentro del hilo de JavaFX.
    AtomicReference<Throwable> error = new AtomicReference<>();

    // Enviamos el código al hilo de aplicación de JavaFX.
    Platform.runLater(() -> {
        try {
            accion.run();
        } catch (Throwable t) {
            error.set(t);
        } finally {
            latch.countDown();
        }
    });

    // Esperamos a que el hilo de JavaFX termine.
    assertTrue(
        latch.await(5, TimeUnit.SECONDS),
        "El hilo de JavaFX no ha respondido"
    );

    // Si hubo un fallo en el hilo de JavaFX, lo trasladamos a JUnit.
    if (error.get() != null) {
        throw new AssertionError(
            "Fallo en el hilo de JavaFX",
            error.get()
        );
    }
}

    @Test
    void crearBotonConTexto() throws InterruptedException {
        ejecutarEnFX(() -> {
            BotonAccion bt = new BotonAccion("Pulsame");
            assertEquals("Pulsame", bt.getText());
        });
    }

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

    @Test 
    void activarButton() throws InterruptedException {
        ejecutarEnFX(() -> {
                BotonAccion bt = new BotonAccion("Pulsame");
                //Desactivo el boton
                bt.setActivo(false);
                //Compruebp que esta desabilitado 
                assertTrue(bt.isDisable());
                //Lo volvemos a activar
                bt.setActivo(true);
                //Compruebo que se ha vuelto a activar
                assertTrue(bt.isActivo());
                assertFalse(bt.isDisable());
        });
    }

    @Test 
    void cambiarTexto() throws InterruptedException{
        ejecutarEnFX(() -> {
                String textoNuevo = "Pulsame porfavor";

                BotonAccion bt = new BotonAccion("Pulsame");

                Boolean res = false;
                if(bt.getText().equals(textoNuevo))
                    res = true;

                assertFalse(res);

                bt.setText(textoNuevo);

                if(bt.getText().equals(textoNuevo))
                    res = true;

                assertTrue(res);
        });

    }

    @Test 
    void cambiarColorFondo() throws InterruptedException{
        ejecutarEnFX(() -> {
            String nuevoColor = "#2367";

            BotonAccion bt = new BotonAccion("Pulsame");
            bt.setColorFondo("#5678");

            Boolean res = false;
            if(bt.getColorFondo().equals(nuevoColor))
                res = true;

            assertFalse(res);

            bt.setColorFondo(nuevoColor);

            if(bt.getColorFondo().equals(nuevoColor))
                res = true;

            assertTrue(res);
        });
    }

    @Test 
    void cambiarColorTexto() throws InterruptedException{
        ejecutarEnFX(() -> {
            String nuevoColor = "rgb(44, 162, 5)";

            BotonAccion bt = new BotonAccion("Pulsame");
            bt.setColorTexto("#5678");

            Boolean res = false;
            if(bt.getColorTexto().equals(nuevoColor))
                res = true;

            assertFalse(res);

            bt.setColorTexto(nuevoColor);

            if(bt.getColorTexto().equals(nuevoColor))
                res = true;

            assertTrue(res);
        });
    }

    @Test 
    void cambiarTamanioTesto() throws InterruptedException{
        ejecutarEnFX(() -> {
            int nuevoTamanio = 12;

            BotonAccion bt = new BotonAccion("Pulsame");
            bt.setTamañoTexto(10);


            Boolean res = false;

            if(bt.getTamañoTexto()== nuevoTamanio){
                res = true;
            }
            assertFalse(res);

            bt.setTamañoTexto(nuevoTamanio);

            if(bt.getTamañoTexto()== nuevoTamanio){
                res = true;
            }
            assertTrue(res);
        });
    }

     @Test 
    void cambiarTestoAyuda() throws InterruptedException{
        ejecutarEnFX(() -> {
        String textoAyuda = "Pulsa para guardar";

        BotonAccion bt = new BotonAccion("Pulsame");
        bt.setTextoAyuda("Hola que tal");


        Boolean res = false;
        
        if(bt.getTextoAyuda().equals(textoAyuda)){
            res = true;
        }
        assertFalse(res);

        bt.setTextoAyuda(textoAyuda);

         if(bt.getTextoAyuda().equals(textoAyuda)){
            res = true;
        }
        assertTrue(res);
        });
    }

    @Test 
    void tamañoTextoNegativo() throws InterruptedException{
        ejecutarEnFX(() -> {
            int nuevoTamanio = -1;
            BotonAccion bt = new BotonAccion("Pulsame");
            bt.setTamañoTexto(10);

            Boolean res = false;

            if(bt.getTamañoTexto()== nuevoTamanio){
                res = true;
            }
            assertFalse(res);

            bt.setTamañoTexto(nuevoTamanio);

            if(bt.getTamañoTexto()== nuevoTamanio){
                res = true;
            }
            assertFalse(res);
        });
        
    }
    
}

package es.ciudadescolar.componentes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import es.ciudadescolar.FxTestUtil;

class BotonAccionTest {

    @BeforeAll
    static void iniciar() throws Exception {
        FxTestUtil.iniciarJavaFX();
    }

    @Test
    void creaConTexto() throws Exception {
        FxTestUtil.ejecutarEnFX(() -> assertEquals("Púlsame", new BotonAccion("Púlsame").getText()));
    }

    @Test
    void ejecutaAccionAlPulsar() throws Exception {
        FxTestUtil.ejecutarEnFX(() -> {
            BotonAccion bt = new BotonAccion("Púlsame");
            AtomicBoolean pulsado = new AtomicBoolean(false);
            bt.setOnAction(e -> pulsado.set(true));
            bt.fire();
            assertTrue(pulsado.get());
        });
    }

    @Test
    void activarYDesactivar() throws Exception {
        FxTestUtil.ejecutarEnFX(() -> {
            BotonAccion bt = new BotonAccion("Púlsame");
            bt.setActivo(false);
            assertTrue(bt.isDisable());
            bt.setActivo(true);
            assertTrue(bt.isActivo());
        });
    }

    @Test
    void coloresYTamañoSeAplicanAlEstilo() throws Exception {
        FxTestUtil.ejecutarEnFX(() -> {
            BotonAccion bt = new BotonAccion("Púlsame");
            bt.setColorFondo("#2e7d32");
            bt.setColorTexto("white");
            bt.setTamañoTexto(14);
            assertEquals("#2e7d32", bt.getColorFondo());
            assertEquals("white", bt.getColorTexto());
            assertEquals(14, bt.getTamañoTexto());
            assertTrue(bt.getStyle().contains("-fx-background-color: #2e7d32"));
            assertTrue(bt.getStyle().contains("-fx-font-size: 14px"));
        });
    }

    @Test
    void tamañoNegativoSeIgnora() throws Exception {
        FxTestUtil.ejecutarEnFX(() -> {
            BotonAccion bt = new BotonAccion("Púlsame");
            bt.setTamañoTexto(10);
            bt.setTamañoTexto(-1);
            assertEquals(10, bt.getTamañoTexto());
        });
    }

    @Test
    void textoAyudaCreaTooltip() throws Exception {
        FxTestUtil.ejecutarEnFX(() -> {
            BotonAccion bt = new BotonAccion("Púlsame");
            bt.setTextoAyuda("Pulsa para guardar");
            assertEquals("Pulsa para guardar", bt.getTextoAyuda());
            assertNotNull(bt.getTooltip());
            bt.setTextoAyuda("");
            assertNull(bt.getTooltip());
            assertFalse(bt.getTextoAyuda() == null);
        });
    }
}

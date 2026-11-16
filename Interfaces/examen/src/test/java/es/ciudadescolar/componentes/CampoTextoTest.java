package es.ciudadescolar.componentes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import es.ciudadescolar.FxTestUtil;
import es.ciudadescolar.util.Validador;

/** Tests de un componente JavaFX: se ejecutan en el hilo de JavaFX. */
class CampoTextoTest {

    @BeforeAll
    static void iniciar() throws Exception {
        FxTestUtil.iniciarJavaFX();
    }

    @Test
    void guardaYRecuperaTexto() throws Exception {
        FxTestUtil.ejecutarEnFX(() -> {
            CampoTexto campo = new CampoTexto("Nombre");
            campo.setTexto("Ana");
            assertEquals("Ana", campo.getTexto());
            assertEquals("Nombre", campo.getEtiqueta());
        });
    }

    @Test
    void obligatorioVacioEsInvalido() throws Exception {
        FxTestUtil.ejecutarEnFX(() -> {
            CampoTexto campo = new CampoTexto("Nombre");
            campo.setObligatorio(true);
            campo.setTexto("");
            assertFalse(campo.validar());
            assertTrue(campo.getCampo().getStyle().contains("red"));
        });
    }

    @Test
    void obligatorioConTextoEsValido() throws Exception {
        FxTestUtil.ejecutarEnFX(() -> {
            CampoTexto campo = new CampoTexto("Nombre");
            campo.setObligatorio(true);
            campo.setTexto("Ana");
            assertTrue(campo.validar());
        });
    }

    @Test
    void opcionalVacioEsValido() throws Exception {
        FxTestUtil.ejecutarEnFX(() -> {
            CampoTexto campo = new CampoTexto("Nombre");
            campo.setObligatorio(false);
            assertTrue(campo.validar());
        });
    }

    @Test
    void reglaExtraDni() throws Exception {
        FxTestUtil.ejecutarEnFX(() -> {
            CampoTexto campo = new CampoTexto("DNI");
            campo.setRegla(Validador::esDniValido);
            campo.setTexto("12345678A");
            assertFalse(campo.validar());
            campo.setTexto("12345678Z");
            assertTrue(campo.validar());
        });
    }

    @Test
    void editableYTextoAyuda() throws Exception {
        FxTestUtil.ejecutarEnFX(() -> {
            CampoTexto campo = new CampoTexto("Nombre");
            campo.setEditable(false);
            assertFalse(campo.isEditable());
            campo.setEditable(true);
            assertTrue(campo.isEditable());
            campo.setTextoAyuda("Escribe tu nombre");
            assertEquals("Escribe tu nombre", campo.getTextoAyuda());
        });
    }
}

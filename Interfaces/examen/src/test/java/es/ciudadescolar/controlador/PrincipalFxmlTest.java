package es.ciudadescolar.controlador;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import es.ciudadescolar.FxTestUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/** Comprueba que el FXML carga sin errores y enlaza su controlador (fx:id / onAction correctos). */
class PrincipalFxmlTest {

    @BeforeAll
    static void iniciar() throws Exception {
        FxTestUtil.iniciarJavaFX();
    }

    @Test
    void fxmlCargaYTieneControlador() throws Exception {
        FxTestUtil.ejecutarEnFX(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/principal.fxml"));
                Parent root = loader.load();
                assertNotNull(root);
                assertTrue(loader.getController() instanceof PrincipalController);
                assertNotNull(getClass().getResource("/css/estilos.css"));
            } catch (Exception e) {
                throw new AssertionError("No se pudo cargar principal.fxml", e);
            }
        });
    }
}

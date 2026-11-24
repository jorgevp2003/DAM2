package es.ciudadescolar;

import es.ciudadescolar.modelo.BotonAccion;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Ventana de PRUEBA del componente {@link BotonAccion} (no forma parte del JAR:
 * el pom.xml excluye esta clase al empaquetar).
 *
 * <p>Ejecutar: {@code mvn clean javafx:run}</p>
 *
 * @author Jorge Vazquez Poto
 * @version 1.0
 */
public class Main extends Application {

    /**
     * Crea varios BotonAccion con distintos textos, colores y tamaños.
     *
     * @param stage ventana principal
     */
    @Override
    public void start(Stage stage) {
        Label estado = new Label("Pulsa un botón");

        // Constructor completo: (texto, colorFondo, colorTexto, tamañoFuente)
        BotonAccion guardar = new BotonAccion("Guardar", "#2e7d32", "white", 16);
        guardar.setTextoAyuda("Tooltip: guarda los datos");
        // setOnAction lo hereda de Button
        guardar.setOnAction(e -> estado.setText("Has pulsado Guardar"));

        // Constructor simple + setters
        BotonAccion borrar = new BotonAccion("Borrar");
        borrar.setColorFondo("#c62828");
        borrar.setColorTexto("white");
        borrar.setTamañoTexto(12);
        borrar.setOnAction(e -> estado.setText("Has pulsado Borrar"));

        // Botón desactivado con setActivo(false)
        BotonAccion desactivado = new BotonAccion("Desactivado", "#9e9e9e", "black", 12);
        desactivado.setActivo(false);

        VBox root = new VBox(12, guardar, borrar, desactivado, estado);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        stage.setTitle("Práctica 3 - Prueba de BotonAccion");
        stage.setScene(new Scene(root, 320, 240));
        stage.show();
    }

    /**
     * Arranca JavaFX.
     *
     * @param args argumentos (no se usan)
     */
    public static void main(String[] args) {
        launch(args);
    }
}

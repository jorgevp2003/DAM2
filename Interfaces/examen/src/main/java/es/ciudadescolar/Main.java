package es.ciudadescolar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Punto de entrada JavaFX.
 * Carga la vista principal hecha con FXML (principal.fxml + PrincipalController).
 *
 * Ejecutar: mvn clean javafx:run
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/principal.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 900, 600);
        scene.getStylesheets().add(getClass().getResource("/css/estilos.css").toExternalForm());

        stage.setTitle("Proyecto base - Examen");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

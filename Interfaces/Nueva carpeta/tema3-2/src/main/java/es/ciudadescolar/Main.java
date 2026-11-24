
package es.ciudadescolar;


import es.ciudadescolar.controlador.FormularioController;
import es.ciudadescolar.vista.FormularioView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Main extends Application {
@Override
public void start(Stage stage) {
FormularioView vista = new FormularioView();
new FormularioController(vista);
Scene scene = new Scene(vista, 500, 350);
stage.setTitle("Registro de personas");
stage.setScene(scene);
stage.show();
}
public static void main(String[] args) {
launch();
}
}

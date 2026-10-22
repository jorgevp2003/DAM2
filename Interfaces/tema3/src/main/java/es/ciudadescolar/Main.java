
package es.ciudadescolar;

import es.ciudadescolar.componentes.CampoTexto;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        Button btnValidar = new Button("Validar formulario");

        CampoTexto campoNombre = new CampoTexto("Nombre");
        CampoTexto campoApellidos = new CampoTexto("Apellidos");
        CampoTexto campoDni = new CampoTexto("DNI");

        campoNombre.setEditable(true);
        campoApellidos.setEditable(true);
        campoDni.setEditable(false);

        campoNombre.setTextoAyuda("Introduce tu nombre");
        campoApellidos.setTextoAyuda("Introduce tus apellidos");
        campoDni.setTextoAyuda("Introduce 8 números y una letra");

        campoNombre.setObligatorio(true);
        campoApellidos.setObligatorio(true);
        campoDni.setObligatorio(false);

        VBox root = new VBox(
            campoNombre,
            campoApellidos,
            campoDni,
            btnValidar
        );

        root.setSpacing(15);
        root.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(root, 400, 250);

        stage.setTitle("Prueba del componente CampoTexto");
        stage.setScene(scene);
        stage.show();

        btnValidar.setOnAction(event -> {

            boolean nombreValido = campoNombre.validar();
            boolean apellidosValidos = campoApellidos.validar();
            boolean dniValido = campoDni.validar();

            if (nombreValido && apellidosValidos && dniValido) {

                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Validación");
                alerta.setHeaderText("Formulario correcto");
                alerta.setContentText("Todos los campos son válidos.");
                alerta.showAndWait();

            } else {

                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Validación");
                alerta.setHeaderText("Errores en el formulario");
                alerta.setContentText(
                    "Debes completar todos los campos obligatorios."
                );
                alerta.showAndWait();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}

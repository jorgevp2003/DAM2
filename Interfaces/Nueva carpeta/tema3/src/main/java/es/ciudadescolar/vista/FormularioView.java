package es.ciudadescolar.vista;

import es.ciudadescolar.componentes.CampoTexto;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class FormularioView extends VBox {
    private CampoTexto txtNombre;
    private CampoTexto txtApellidos;
    private CampoTexto txtDni;
    private Button btnGuardar;

    public FormularioView() {
        Label titulo = new Label("Registro de personas");
        titulo.setStyle(
            "-fx-font-size: 20px; -fx-font-weight: bold;"
        );

        txtNombre = new CampoTexto("Nombre:");
        txtNombre.setTextoAyuda("Ej.: Ana");
        txtNombre.setObligatorio(true);

        txtApellidos = new CampoTexto("Apellidos:");
        txtApellidos.setTextoAyuda("Introduce los apellidos");
        txtApellidos.setObligatorio(true);

        txtDni = new CampoTexto("DNI:");
        txtDni.setTextoAyuda("Ej.: 12345678Z");
        txtDni.setObligatorio(true);

        btnGuardar = new Button("Guardar");

        GridPane formulario = new GridPane();
        formulario.setHgap(12);
        formulario.setVgap(15);
        formulario.add(txtNombre, 0, 0);
        formulario.add(txtApellidos, 0, 1);
        formulario.add(txtDni, 0, 2);
        formulario.add(btnGuardar, 0, 3);
        formulario.setAlignment(Pos.CENTER_LEFT);

        setSpacing(20);
        setPadding(new Insets(25));
        setAlignment(Pos.TOP_CENTER);
        getChildren().addAll(titulo, formulario);
    }

    public CampoTexto getTxtNombre() {
        return txtNombre;
    }

    public CampoTexto getTxtApellidos() {
        return txtApellidos;
    }

    public CampoTexto getTxtDni() {
        return txtDni;
    }

    public Button getBtnGuardar() {
        return btnGuardar;
    }
}

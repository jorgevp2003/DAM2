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

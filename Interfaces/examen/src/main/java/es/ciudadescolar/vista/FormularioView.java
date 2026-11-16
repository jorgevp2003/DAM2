package es.ciudadescolar.vista;

import es.ciudadescolar.componentes.BotonAccion;
import es.ciudadescolar.componentes.CampoTexto;
import es.ciudadescolar.util.Validador;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Vista (la V de MVC) construida por CÓDIGO, sin FXML.
 * Solo crea y coloca componentes; la lógica está en FormularioController.
 */
public class FormularioView extends VBox {

    private final CampoTexto txtNombre;
    private final CampoTexto txtApellidos;
    private final CampoTexto txtDni;
    private final BotonAccion btnGuardar;
    private final Button btnLimpiar;
    private final Label lblEstado;
    private final Label lblInfo;

    public FormularioView() {
        Label titulo = new Label("Registro rápido de personas");
        titulo.getStyleClass().add("titulo");

        txtNombre = new CampoTexto("Nombre:");
        txtNombre.setTextoAyuda("Ej.: Ana");
        txtNombre.setObligatorio(true);

        txtApellidos = new CampoTexto("Apellidos:");
        txtApellidos.setTextoAyuda("Introduce los apellidos");
        txtApellidos.setObligatorio(true);

        txtDni = new CampoTexto("DNI:");
        txtDni.setTextoAyuda("Ej.: 12345678Z");
        txtDni.setObligatorio(true);
        txtDni.setRegla(Validador::esDniValido);   // regla extra del componente

        btnGuardar = new BotonAccion("Guardar", "#1565c0", "white");
        btnGuardar.setTextoAyuda("Guarda la persona");
        btnLimpiar = new Button("Limpiar");

        lblEstado = new Label();
        lblEstado.getStyleClass().add("estado");
        lblEstado.setWrapText(true);
        lblInfo = new Label();

        GridPane formulario = new GridPane();
        formulario.setHgap(12);
        formulario.setVgap(12);
        formulario.add(txtNombre, 0, 0);
        formulario.add(txtApellidos, 0, 1);
        formulario.add(txtDni, 0, 2);
        formulario.setAlignment(Pos.CENTER_LEFT);

        HBox botones = new HBox(10, btnGuardar, btnLimpiar);

        setSpacing(15);
        setPadding(new Insets(25));
        setAlignment(Pos.TOP_LEFT);
        getChildren().addAll(titulo, formulario, botones, lblEstado, lblInfo);
    }

    public CampoTexto getTxtNombre() { return txtNombre; }
    public CampoTexto getTxtApellidos() { return txtApellidos; }
    public CampoTexto getTxtDni() { return txtDni; }
    public BotonAccion getBtnGuardar() { return btnGuardar; }
    public Button getBtnLimpiar() { return btnLimpiar; }
    public Label getLblEstado() { return lblEstado; }
    public Label getLblInfo() { return lblInfo; }
}

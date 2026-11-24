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
    private Button btnLimpiar;

    private Label lblEstado;
    private Label lblInfo;

    public FormularioView() {

        // Título del formulario
        Label titulo = new Label("Registro de personas");
        titulo.setStyle(
            "-fx-font-size: 20px; -fx-font-weight: bold;"
        );

        // Configuración de los campos de texto personalizados
        txtNombre = new CampoTexto("Nombre:");
        txtNombre.setTextoAyuda("Ej.: Ana");
        txtNombre.setObligatorio(true);

        txtApellidos = new CampoTexto("Apellidos:");
        txtApellidos.setTextoAyuda("Introduce los apellidos");
        txtApellidos.setObligatorio(true);

        txtDni = new CampoTexto("DNI:");
        txtDni.setTextoAyuda("Ej.: 12345678Z");
        txtDni.setObligatorio(true);
        lblEstado = new Label("Escribe tu nombre para continuar.");
       


        // Botón para guardar
        btnGuardar = new Button("Guardar");
        btnLimpiar = new Button("Limpiar");

        // Etiqueta para mostrar mensajes de estado
        lblEstado = new Label();
        lblInfo = new Label();

        // Distribución de los componentes
        GridPane formulario = new GridPane();
        formulario.setHgap(12);
        formulario.setVgap(15);

        formulario.add(txtNombre, 0, 0);
        formulario.add(txtApellidos, 0, 1);
        formulario.add(txtDni, 0, 2);
        formulario.add(btnGuardar, 0, 3);
        formulario.add(btnLimpiar,2,3);
        formulario.add(lblEstado, 0, 4);
        formulario.add(lblInfo,0,5);
        

        formulario.setAlignment(Pos.CENTER_LEFT);

        // Configuración general del VBox
        setSpacing(20);
        setPadding(new Insets(25));
        setAlignment(Pos.TOP_CENTER);

        // Añadir los elementos a la vista
        getChildren().addAll(titulo, formulario);
    }

   
    public CampoTexto getTxtNombre() {
        return txtNombre;
    }


    public void setTxtNombre(CampoTexto txtNombre) {
        this.txtNombre = txtNombre;
    }


    public CampoTexto getTxtApellidos() {
        return txtApellidos;
    }


    public void setTxtApellidos(CampoTexto txtApellidos) {
        this.txtApellidos = txtApellidos;
    }


    public CampoTexto getTxtDni() {
        return txtDni;
    }


    public void setTxtDni(CampoTexto txtDni) {
        this.txtDni = txtDni;
    }


    public Button getBtnGuardar() {
        return btnGuardar;
    }
    public Button getBtnLimpiar(){
        return btnLimpiar;
    }


    public void setBtnGuardar(Button btnGuardar) {
        this.btnGuardar = btnGuardar;
    }


    public Label getLblEstado() {
        return lblEstado;
    }
    public Label getLblInfo() {
        return lblInfo;
    }


    public void setLblEstado(Label lblEstado) {
        this.lblEstado = lblEstado;
    }
     public void setLblInfo(Label lblInfo) {
        this.lblInfo = lblInfo;
    }
   

}
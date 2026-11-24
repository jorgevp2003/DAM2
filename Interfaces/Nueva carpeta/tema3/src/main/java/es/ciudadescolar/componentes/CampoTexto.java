package es.ciudadescolar.componentes;


import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CampoTexto extends VBox {
    private Label etiqueta;
    private TextField campo;
    private boolean obligatorio = false;
    public CampoTexto(String textoEtiqueta) {
        etiqueta = new Label(textoEtiqueta);
        campo = new TextField();
        getChildren().addAll(etiqueta, campo);
        setSpacing(5);
        campo.textProperty().addListener((observable, anterior, nuevo) -> {validar();});
    }

    public void setEditable(boolean editable) {
        campo.setEditable(editable);
    }

    public void setTextoAyuda(String textoAyuda) {
        campo.setPromptText(textoAyuda);
    }
    public void setObligatorio(boolean obligatorio) {
        this.obligatorio = obligatorio;
    }
    public boolean isObligatorio() {
        return obligatorio;
    }
    public boolean validar() {
    if (obligatorio && campo.getText().trim().isEmpty()) {
        campo.setStyle("-fx-border-color: red;");//marca visualmente el campo con un borde rojo cuando falla la validación
        return false;
    }
        campo.setStyle("");
        return true;
    }
}
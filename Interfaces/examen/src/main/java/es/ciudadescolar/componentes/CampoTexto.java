package es.ciudadescolar.componentes;

import java.util.function.Predicate;

import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Componente personalizado: Label + TextField con validación.
 * - obligatorio: no puede quedar vacío
 * - regla extra opcional (Predicate), p. ej. Validador::esDniValido
 * Si falla, el campo se marca con borde rojo.
 */
public class CampoTexto extends VBox {

    private final Label etiqueta;
    private final TextField campo;
    private boolean obligatorio = false;
    private Predicate<String> regla = null;

    public CampoTexto(String textoEtiqueta) {
        etiqueta = new Label(textoEtiqueta);
        campo = new TextField();
        getChildren().addAll(etiqueta, campo);
        setSpacing(5);
        // Listener: valida cada vez que cambia el texto
        campo.textProperty().addListener((observable, anterior, nuevo) -> validar());
    }

    public boolean validar() {
        String texto = campo.getText() == null ? "" : campo.getText().trim();
        boolean ok = true;
        if (obligatorio && texto.isEmpty()) {
            ok = false;
        } else if (!texto.isEmpty() && regla != null && !regla.test(texto)) {
            ok = false;
        }
        campo.setStyle(ok ? "" : "-fx-border-color: red;");
        return ok;
    }

    public void setRegla(Predicate<String> regla) { this.regla = regla; }

    public String getTexto() { return campo.getText(); }
    public void setTexto(String texto) { campo.setText(texto); }

    public String getEtiqueta() { return etiqueta.getText(); }
    public void setEtiqueta(String texto) { etiqueta.setText(texto); }

    public String getTextoAyuda() { return campo.getPromptText(); }
    public void setTextoAyuda(String textoAyuda) { campo.setPromptText(textoAyuda); }

    public boolean isEditable() { return campo.isEditable(); }
    public void setEditable(boolean editable) { campo.setEditable(editable); }

    public boolean isObligatorio() { return obligatorio; }
    public void setObligatorio(boolean obligatorio) { this.obligatorio = obligatorio; }

    /** Para añadir listeners desde el controlador. */
    public StringProperty textProperty() { return campo.textProperty(); }

    public TextField getCampo() { return campo; }
}

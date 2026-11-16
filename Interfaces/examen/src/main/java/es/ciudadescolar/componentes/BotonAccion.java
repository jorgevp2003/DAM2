package es.ciudadescolar.componentes;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

/**
 * Componente personalizado: botón con color de fondo, color de texto,
 * tamaño de letra, texto de ayuda (Tooltip) y activar/desactivar.
 *
 * @author Jorge Vazquez
 * @version 2.0
 */
public class BotonAccion extends Button {

    private String colorFondo;
    private String colorTexto;
    private int tamañoTexto;
    private String textoAyuda;

    /** Constructor vacío: necesario para poder usarlo dentro de un FXML. */
    public BotonAccion() {
        super();
    }

    public BotonAccion(String texto) {
        super(texto);
    }

    public BotonAccion(String texto, String colorFondo, String colorTexto) {
        super(texto);
        this.colorFondo = colorFondo;
        this.colorTexto = colorTexto;
        aplicarEstilo();
    }

    /** Construye el style CSS con los valores actuales. */
    private void aplicarEstilo() {
        StringBuilder css = new StringBuilder();
        if (colorFondo != null && !colorFondo.isBlank()) {
            css.append("-fx-background-color: ").append(colorFondo).append(";");
        }
        if (colorTexto != null && !colorTexto.isBlank()) {
            css.append("-fx-text-fill: ").append(colorTexto).append(";");
        }
        if (tamañoTexto > 0) {
            css.append("-fx-font-size: ").append(tamañoTexto).append("px;");
        }
        setStyle(css.toString());
    }

    public String getTexto() { return getText(); }
    public void setTexto(String texto) { setText(texto); }

    public String getColorFondo() { return colorFondo; }
    public void setColorFondo(String colorFondo) {
        this.colorFondo = colorFondo;
        aplicarEstilo();
    }

    public String getColorTexto() { return colorTexto; }
    public void setColorTexto(String colorTexto) {
        this.colorTexto = colorTexto;
        aplicarEstilo();
    }

    public int getTamañoTexto() { return tamañoTexto; }
    /** Solo acepta valores positivos; si no, se ignora. */
    public void setTamañoTexto(int tamañoTexto) {
        if (tamañoTexto > 0) {
            this.tamañoTexto = tamañoTexto;
            aplicarEstilo();
        }
    }

    public String getTextoAyuda() { return textoAyuda; }
    public void setTextoAyuda(String texto) {
        this.textoAyuda = texto;
        setTooltip(texto == null || texto.isBlank() ? null : new Tooltip(texto));
    }

    public boolean isActivo() { return !isDisable(); }
    public void setActivo(boolean activo) { setDisable(!activo); }
}

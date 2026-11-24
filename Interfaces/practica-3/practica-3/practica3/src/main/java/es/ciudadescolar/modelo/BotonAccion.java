package es.ciudadescolar.modelo;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

/**
 * Botón JavaFX personalizado que hereda de {@link Button}.
 * <p>
 * Además del comportamiento normal de un botón (texto, eventos con
 * {@link #setOnAction(javafx.event.EventHandler)}, deshabilitado...),
 * permite configurar de forma sencilla:
 * </p>
 * <ul>
 *   <li>El color de fondo.</li>
 *   <li>El color del texto.</li>
 *   <li>El tamaño de la fuente.</li>
 *   <li>Un texto de ayuda que se muestra en un {@link Tooltip}.</li>
 *   <li>Su estado activo / inactivo.</li>
 * </ul>
 * Los colores y el tamaño se aplican mediante estilos CSS de JavaFX
 * ({@code -fx-background-color}, {@code -fx-text-fill} y {@code -fx-font-size}).
 *
 * <pre>
 * BotonAccion b = new BotonAccion("Guardar", "#2e7d32", "white", 14);
 * b.setTextoAyuda("Guarda los datos");
 * b.setOnAction(e -&gt; System.out.println("Pulsado"));
 * </pre>
 *
 * @author Jorge Vazquez Poto
 * @version 1.0
 */
public class BotonAccion extends Button {

    /** Color de fondo en formato CSS (por ejemplo "#ff0000" o "red"). */
    private String colorFondo;
    /** Color del texto en formato CSS. */
    private String colorTexto;
    /** Tamaño de la fuente en píxeles (0 = tamaño por defecto). */
    private int tamañoTexto;
    /** Texto que se muestra en el Tooltip. */
    private String textoAyuda;

    /**
     * Crea un botón sin texto y con el aspecto por defecto.
     */
    public BotonAccion() {
        super();
    }

    /**
     * Crea un botón con el texto indicado y el aspecto por defecto.
     *
     * @param texto texto que mostrará el botón
     */
    public BotonAccion(String texto) {
        super(texto);
    }

    /**
     * Crea un botón con texto, colores y tamaño de fuente.
     *
     * @param texto       texto que mostrará el botón
     * @param colorFondo  color de fondo en formato CSS (por ejemplo "#1976d2")
     * @param colorTexto  color del texto en formato CSS (por ejemplo "white")
     * @param tamañoTexto tamaño de la fuente en píxeles; debe ser mayor que 0
     * @throws IllegalArgumentException si {@code tamañoTexto} es menor o igual que 0
     */
    public BotonAccion(String texto, String colorFondo, String colorTexto, int tamañoTexto) {
        super(texto);
        this.colorFondo = colorFondo;
        this.colorTexto = colorTexto;
        setTamañoTexto(tamañoTexto); // valida y aplica el estilo
    }

    /**
     * Devuelve el texto que muestra el botón.
     *
     * @return texto del botón
     */
    public String getTexto() {
        return getText();
    }

    /**
     * Cambia el texto que muestra el botón.
     *
     * @param texto nuevo texto del botón
     */
    public void setTexto(String texto) {
        setText(texto);
    }

    /**
     * Devuelve el color de fondo establecido.
     *
     * @return color de fondo en formato CSS, o {@code null} si no se ha establecido
     */
    public String getColorFondo() {
        return colorFondo;
    }

    /**
     * Cambia el color de fondo del botón.
     *
     * @param colorFondo color en formato CSS (por ejemplo "#ff9800" o "orange");
     *                   {@code null} o vacío vuelve al color por defecto
     */
    public void setColorFondo(String colorFondo) {
        this.colorFondo = colorFondo;
        actualizarEstilo();
    }

    /**
     * Devuelve el color del texto establecido.
     *
     * @return color del texto en formato CSS, o {@code null} si no se ha establecido
     */
    public String getColorTexto() {
        return colorTexto;
    }

    /**
     * Cambia el color del texto del botón.
     *
     * @param colorTexto color en formato CSS (por ejemplo "white");
     *                   {@code null} o vacío vuelve al color por defecto
     */
    public void setColorTexto(String colorTexto) {
        this.colorTexto = colorTexto;
        actualizarEstilo();
    }

    /**
     * Devuelve el tamaño de la fuente establecido.
     *
     * @return tamaño en píxeles, o 0 si se usa el tamaño por defecto
     */
    public int getTamañoTexto() {
        return tamañoTexto;
    }

    /**
     * Establece el tamaño de la fuente del botón.
     *
     * @param tamañoTexto tamaño en píxeles; debe ser mayor que 0
     * @throws IllegalArgumentException si {@code tamañoTexto} es menor o igual que 0
     */
    public void setTamañoTexto(int tamañoTexto) {
        if (tamañoTexto <= 0) {
            throw new IllegalArgumentException(
                    "El tamaño de la fuente debe ser mayor que 0: " + tamañoTexto);
        }
        this.tamañoTexto = tamañoTexto;
        actualizarEstilo();
    }

    /**
     * Devuelve el texto de ayuda del botón.
     *
     * @return texto del Tooltip, o {@code null} si no tiene
     */
    public String getTextoAyuda() {
        return textoAyuda;
    }

    /**
     * Establece el texto de ayuda que se muestra al pasar el ratón por encima
     * del botón (Tooltip).
     *
     * @param textoAyuda texto de ayuda; {@code null} o vacío elimina el Tooltip
     */
    public void setTextoAyuda(String textoAyuda) {
        this.textoAyuda = textoAyuda;
        if (textoAyuda == null || textoAyuda.isBlank()) {
            setTooltip(null);
        } else {
            setTooltip(new Tooltip(textoAyuda));
        }
    }

    /**
     * Indica si el botón está activo (se puede pulsar).
     *
     * @return {@code true} si está activo, {@code false} si está deshabilitado
     */
    public boolean isActivo() {
        return !isDisable();
    }

    /**
     * Activa o desactiva el botón.
     *
     * @param activo {@code true} para activarlo, {@code false} para deshabilitarlo
     */
    public void setActivo(boolean activo) {
        setDisable(!activo);
    }

    /**
     * Construye el estilo CSS a partir de los colores y el tamaño guardados
     * y lo aplica al botón.
     */
    private void actualizarEstilo() {
        StringBuilder css = new StringBuilder();
        if (colorFondo != null && !colorFondo.isBlank()) {
            css.append("-fx-background-color: ").append(colorFondo).append("; ");
        }
        if (colorTexto != null && !colorTexto.isBlank()) {
            css.append("-fx-text-fill: ").append(colorTexto).append("; ");
        }
        if (tamañoTexto > 0) {
            css.append("-fx-font-size: ").append(tamañoTexto).append("px; ");
        }
        setStyle(css.toString().trim());
    }
}

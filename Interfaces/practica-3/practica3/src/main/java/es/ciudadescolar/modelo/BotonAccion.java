package es.ciudadescolar.modelo;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
/**
 * @author Jorge Vazquez
 * @version id
 * 
 * BotonAccion
 */
public class BotonAccion extends Button {
    private String texto;
    private String colorFondo;
    private String colorTexto;
    private int tamañoTexto;
    private String textoAyuda;

    /**
     * 
     * @param texto
     */
    public BotonAccion(String texto ){
        super(texto);
    }

    /**
     * 
     * @param texto
     */
    public void setTextoAyuda(String texto){
        this.textoAyuda = texto;
        if(texto == null || texto.isBlank()){
            setTooltip(null);
        } else {
            setTooltip(new Tooltip(texto));
        }
    }
    /**
     * 
     * @return String
     */
    public String getTextoAyuda(){
        return textoAyuda;
    }
    /**
     * 
     * @return String
     */
    public String getTexto() {
        return texto;
    }
    /**
     * 
     * @param texto
     */
    public void setTexto(String texto) {
        setText(texto);
    }
    /**
     * 
     * @return String
     */
    public String getColorFondo() {
        return colorFondo;
    }
    /**
     * 
     * @param colorFondo
     */
    public void setColorFondo(String colorFondo) {
        this.colorFondo = colorFondo;
        
    }
    /**
     * 
     * @return String
     */
    public String getColorTexto() {
        return colorTexto;
    }
    /**
     * 
     * @param colorTexto
     */
    public void setColorTexto(String colorTexto) {
        this.colorTexto = colorTexto;
    }
    /**
     * 
     * @return int
     */
    public int getTamañoTexto() {
        return tamañoTexto;
    }
    /**
     * 
     * @param tamañoTexto
     */
    public void setTamañoTexto(int tamañoTexto) {
        if (tamañoTexto>0)
            this.tamañoTexto = tamañoTexto;
    }
    /**
     * 
     * @return Boolean
     */
    public boolean isActivo() {
        return !isDisable();
    }
    /**
     * 
     * @param activo
     */
    public void setActivo(boolean activo) {
        setDisable(!activo);
    }

    
}

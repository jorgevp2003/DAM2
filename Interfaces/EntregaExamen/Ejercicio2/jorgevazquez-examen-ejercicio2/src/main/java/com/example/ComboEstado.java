package com.example;

import javafx.scene.control.ComboBox;

/**
 * @author Jorge Vazquez
 * @version 1.0
 * ComboBox para el estado de las incidencias
 * Guardo el estado y doy color acorde
 */
public class ComboEstado extends ComboBox<String> {
    //Estados
    public static final String PENDIENTE = "Pendiente";
    public static final String EN_PROCESO = "En Proceso";
    public static final String RESUELTA = "Resuelta";

    private String estado;
    private String color;

    // Constructor
    public ComboEstado() {
        super();
        getItems().addAll(PENDIENTE, EN_PROCESO, RESUELTA);
        setPromptText("Selecciona estado");
        setMaxWidth(Double.MAX_VALUE);

        // Cada vez que cambia la selección actualizamos estado y color
        valueProperty().addListener((obs, anterior, nuevo) -> {
            setEstado(nuevo);
            actualizarColor();
        });
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean esEstadoFinalizado() {
        return RESUELTA.equals(estado);
    }

    /** 
     * Reinicia el combo dejando sin selección y sin color. 
     * */
    public void reiniciar() {
        getSelectionModel().clearSelection();
        setValue(null);
        estado = null;
        color = null;
        setStyle("");
    }

    /** 
     * Pongo el color de fondo que corresponde al estado actual. 
     * */
    public void actualizarColor() {
        
        if (RESUELTA.equals(estado)) {
            color = "green";
        } else if (PENDIENTE.equals(estado)) {
            color = "orange";
        } else if (EN_PROCESO.equals(estado)) {
            color = "blue";
        } else {
            color = null;
        }
        setStyle(color == null ? "" : "-fx-background-color: " + color + ";");
    }

    
}

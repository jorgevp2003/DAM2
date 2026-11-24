package com.ejemplo.controlador;

import com.ejemplo.modelo.Incidencia;
import com.ejemplo.vista.ResumenView;

import javafx.stage.Stage;

/**
 * CONTROLADOR de la ventana secundaria (resumen).
 *
 * <p>Comunicación entre ventanas:</p>
 * <ul>
 *   <li><b>Entrada:</b> recibe la {@link Incidencia} en el constructor
 *       (se la pasa {@code FormularioController}) y la muestra en la vista.</li>
 *   <li><b>Salida:</b> guarda en {@code aceptada} qué botón se pulsó.
 *       La ventana principal lo consulta con {@link #isAceptada()} después
 *       de que {@code showAndWait()} termine.</li>
 * </ul>
 *
 * @author Jorge Vazquez Poto
 * @version 1.0
 */
public class ResumenController {

    /** Ventana del resumen; la necesitamos para poder cerrarla. */
    private final Stage ventana;

    /**
     * Resultado: true si se pulsó Aceptar.
     * Empieza en false, así cerrar con la X cuenta como "cancelar".
     */
    private boolean aceptada = false;

    /**
     * Rellena la vista con los datos recibidos y asocia los eventos.
     *
     * @param vista      vista del resumen
     * @param ventana    ventana en la que se muestra (para cerrarla)
     * @param incidencia datos recibidos de la ventana principal
     */
    public ResumenController(ResumenView vista, Stage ventana, Incidencia incidencia) {
        this.ventana = ventana;

        // ENTRADA: mostrar los datos que llegan de la ventana principal
        vista.getLblEquipo().setText(incidencia.getEquipo());
        vista.getLblDescripcion().setText(incidencia.getDescripcion());

        // Eventos de los botones (en el controlador, no en la vista)
        vista.getBtnAceptar().setOnAction(e -> aceptar());
        vista.getBtnCancelar().setOnAction(e -> cancelar());
    }

    /**
     * Aceptar: marca la incidencia como aceptada y cierra la ventana.
     * Al cerrarse, termina el showAndWait() de la ventana principal.
     */
    private void aceptar() {
        aceptada = true;
        ventana.close();
    }

    /**
     * Cancelar: cierra la ventana SIN registrar la incidencia.
     */
    private void cancelar() {
        aceptada = false;
        ventana.close();
    }

    /**
     * SALIDA hacia la ventana principal.
     *
     * @return {@code true} si se pulsó Aceptar; {@code false} si se canceló
     *         o se cerró la ventana con la X
     */
    public boolean isAceptada() {
        return aceptada;
    }
}

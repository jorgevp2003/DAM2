package com.ejemplo.vista;

import es.ciudadescolar.modelo.BotonAccion;   // viene de lib/BotonAccion.jar
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * VISTA secundaria: resumen de la incidencia antes de registrarla.
 *
 * <p>Las etiquetas se crean VACÍAS. Es {@code ResumenController} quien
 * recibe la {@code Incidencia} y escribe los datos en ellas, y quien pone
 * los eventos de Aceptar y Cancelar (la vista no gestiona eventos).</p>
 *
 * <pre>
 *  VBox (this)
 *   ├── Label titulo
 *   ├── GridPane  [Equipo:     | lblEquipo     ]
 *   │             [Incidencia: | lblDescripcion]
 *   └── HBox      [btnAceptar] [btnCancelar]
 * </pre>
 *
 * @author Jorge Vazquez Poto
 * @version 1.0
 */
public class ResumenView extends VBox {

    private Label lblEquipo;
    private Label lblDescripcion;
    private BotonAccion btnAceptar;
    private BotonAccion btnCancelar;

    /**
     * Crea y coloca los controles del resumen.
     */
    public ResumenView() {
        Label titulo = new Label("Resumen de la incidencia");
        titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        // Etiquetas vacías: los datos los pone el controlador
        lblEquipo = new Label();
        lblDescripcion = new Label();
        lblDescripcion.setWrapText(true);  // si el texto es largo, salta de línea
        lblDescripcion.setMaxWidth(260);   // ancho máximo antes de saltar

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.add(new Label("Equipo:"), 0, 0);
        grid.add(lblEquipo, 1, 0);
        grid.add(new Label("Incidencia:"), 0, 1);
        grid.add(lblDescripcion, 1, 1);

        // Botones personalizados: azul para Aceptar, rojo para Cancelar
        btnAceptar = new BotonAccion("Aceptar", "#1976d2", "white", 13);
        btnCancelar = new BotonAccion("Cancelar", "#c62828", "white", 13);

        HBox botones = new HBox(10, btnAceptar, btnCancelar);
        botones.setAlignment(Pos.CENTER);

        setSpacing(18);
        setPadding(new Insets(20));
        setAlignment(Pos.TOP_CENTER);
        getChildren().addAll(titulo, grid, botones);
    }

    // ===================== GETTERS =====================

    /** @return etiqueta donde se muestra el nombre del equipo */
    public Label getLblEquipo() {
        return lblEquipo;
    }

    /** @return etiqueta donde se muestra la descripción */
    public Label getLblDescripcion() {
        return lblDescripcion;
    }

    /** @return botón "Aceptar" */
    public BotonAccion getBtnAceptar() {
        return btnAceptar;
    }

    /** @return botón "Cancelar" */
    public BotonAccion getBtnCancelar() {
        return btnCancelar;
    }
}

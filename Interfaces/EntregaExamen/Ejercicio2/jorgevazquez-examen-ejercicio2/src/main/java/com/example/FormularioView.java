package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * @author Jorge Vazquez
 * @version 1.0
 * Clase para la vista del formulario de consulta de incidencias.
 * Contiene los elementos de la interfaz y sus getters.
 */
public class FormularioView extends VBox {

    private final Label lblTitulo = new Label("Consulta de incidencias");

    private final Label lblAula = new Label("Aula");
    private final ComboBox<String> cbAula = new ComboBox<>();

    private final Label lblEquipamiento = new Label("Equipamiento");
    private final ComboBox<String> cbEquipamiento = new ComboBox<>();

    private final Label lblEstado = new Label("Estado incidencia");
    private final ComboEstado cbEstado = new ComboEstado();
    //Texto de muestra para la seleccion
    private final Label lblEstadoSeleccionado = new Label("Estado seleccionado: ninguno");

    private final Button btnConsultar = new Button("Consultar incidencia");

    /**
     * Constructor de la vista del formulario.
     * Configura el layout y añade los elementos a la vista.
     */
    public FormularioView() {
        // Configuración del layout
        super(8);
        setPadding(new Insets(20));
        setAlignment(Pos.TOP_LEFT);

        lblTitulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        //Opciones de aula
        cbAula.getItems().addAll("Aula 101", "Aula 102", "Aula 201", "Aula 202", "Taller");
        cbAula.setPromptText("Selecciona aula");
        cbAula.setMaxWidth(Double.MAX_VALUE);
        //opciones de equipamiento
        cbEquipamiento.getItems().addAll("Ordenador", "Monitor", "Proyector", "Impresora", "Red / Wifi");
        cbEquipamiento.setPromptText("Selecciona equipamiento");
        cbEquipamiento.setMaxWidth(Double.MAX_VALUE);
        //mi combo personalizado 
        btnConsultar.setMaxWidth(Double.MAX_VALUE);
        btnConsultar.setDefaultButton(true);
        VBox.setMargin(btnConsultar, new Insets(10, 0, 0, 0));

        getChildren().addAll(
                lblTitulo,
                lblAula, cbAula,
                lblEquipamiento, cbEquipamiento,
                lblEstado, cbEstado,
                lblEstadoSeleccionado,
                btnConsultar);
    }

    public ComboBox<String> getCbAula() {
        return cbAula;
    }

    public ComboBox<String> getCbEquipamiento() {
        return cbEquipamiento;
    }

    public ComboEstado getCbEstado() {
        return cbEstado;
    }

    public Label getLblEstadoSeleccionado() {
        return lblEstadoSeleccionado;
    }

    public Button getBtnConsultar() {
        return btnConsultar;
    }
}

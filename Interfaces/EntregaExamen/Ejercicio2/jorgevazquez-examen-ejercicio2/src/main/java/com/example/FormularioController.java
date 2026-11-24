package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 
 * FormularioController
 * @author Jorge Vazquez
 */
public class FormularioController {

    private final FormularioView vista;
    private final Stage stagePrincipal;

    public FormularioController(FormularioView vista, Stage stagePrincipal) {
        this.vista = vista;
        this.stagePrincipal = stagePrincipal;
        inicializarEventos();
    }

    /**
     * Inicializa los eventos de la vista.
     */
    private void inicializarEventos() {
        /**
         * Listener para actualizar la etiqueta de estado seleccionado y su color.
         * 
         * Esto no se como funciona, lo tenia asi en el ejemplo de clase, solo lo he cambiado un poco
         */
        vista.getCbEstado().valueProperty().addListener((obs, anterior, nuevo) -> {
            Label lbl = vista.getLblEstadoSeleccionado();
            if (nuevo == null) {
                lbl.setText("Estado seleccionado: ninguno");
                lbl.setStyle("");
            } else {
                lbl.setText("Estado seleccionado: " + nuevo);
                lbl.setStyle("-fx-text-fill: " + vista.getCbEstado().getColor() + "; -fx-font-weight: bold;");
            }
        });

        vista.getBtnConsultar().setOnAction(e -> consultarIncidencia());
    }

    /**
     * Consulta la incidencia seleccionada y abre una ventana modal con el resumen.
     */
    private void consultarIncidencia() {
        String aula = vista.getCbAula().getValue();
        String equipamiento = vista.getCbEquipamiento().getValue();
        String estado = vista.getCbEstado().getEstado();

        // Compruebo que no haya campos vacios y aviso si hay con alert
        if (aula == null || equipamiento == null || estado == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(stagePrincipal);
            alert.setTitle("Faltan datos");
            alert.setHeaderText(null);
            alert.setContentText("Selecciona aula, equipamiento y estado de la incidencia.");
            alert.showAndWait();
            return;
        }

        abrirResumen(aula, equipamiento, estado);
    }

    /**
     * Abre una ventana modal con el resumen de la incidencia seleccionada.
     * @param aula
     * @param equipamiento
     * @param estado
     */
    private void abrirResumen(String aula, String equipamiento, String estado) {
        // Crear una nueva ventana modal para mostrar el resumen
        Stage resumen = new Stage();
        resumen.initOwner(stagePrincipal);
        resumen.initModality(Modality.WINDOW_MODAL);
        resumen.setTitle("Resumen incidencias");
        // Crear los elementos de la interfaz para mostrar enl resumen
        Label lblTitulo = new Label("Resumen incidencias");
        lblTitulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        // Crear etiquetas para mostrar los detalles de la incidencia
        Label lblAula = new Label("Aula: " + aula);
        Label lblEquipamiento = new Label("Equipamiento: " + equipamiento);
        Label lblEstado = new Label("Estado: " + estado);
        lblEstado.setStyle("-fx-text-fill: " + vista.getCbEstado().getColor() + "; -fx-font-weight: bold;");
        //Creou un boton para cerrar la ventana
        Button btnCerrar = new Button("Cerrar");
        btnCerrar.setCancelButton(true);
        btnCerrar.setOnAction(e -> resumen.close());
        // Creo un Vbox
        VBox raiz = new VBox(10, lblTitulo, lblAula, lblEquipamiento, lblEstado, btnCerrar);
        raiz.setPadding(new Insets(20));
        raiz.setAlignment(Pos.CENTER_LEFT);

        resumen.setScene(new Scene(raiz, 320, 220));
        resumen.showAndWait();
    }
}

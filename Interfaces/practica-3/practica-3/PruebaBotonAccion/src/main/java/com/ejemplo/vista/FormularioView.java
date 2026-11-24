package com.ejemplo.vista;

// BotonAccion NO está en este proyecto: viene de lib/BotonAccion.jar
// (dependencia con systemPath en el pom.xml). Se importa igual que cualquier clase.
import es.ciudadescolar.modelo.BotonAccion;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * VISTA principal: formulario para introducir una incidencia.
 *
 * <p>Hereda de {@link VBox}, así la propia vista es el contenedor raíz y se
 * puede pasar directamente a {@code new Scene(vista, ...)}.</p>
 *
 * <p>Regla MVC: la vista SOLO construye y organiza los controles.
 * Los eventos (setOnAction) los pone {@code FormularioController},
 * que accede a los controles mediante los getters de esta clase.</p>
 *
 * <pre>
 *  VBox (this)
 *   ├── Label titulo
 *   ├── GridPane  [Equipo:     | txtEquipo     ]
 *   │             [Incidencia: | txtDescripcion]
 *   ├── HBox      [btnRegistrar] [btnLimpiar]
 *   └── Label lblEstado
 * </pre>
 *
 * @author Jorge Vazquez Poto
 * @version 1.0
 */
public class FormularioView extends VBox {

    // Los controles son atributos (no variables locales) para poder
    // devolverlos con getters y que el controlador trabaje con ellos.
    private TextField txtEquipo;
    private TextField txtDescripcion;
    private BotonAccion btnRegistrar;
    private BotonAccion btnLimpiar;
    private Label lblEstado;

    /**
     * Crea y coloca todos los controles del formulario.
     */
    public FormularioView() {
        // --- Título ---
        Label titulo = new Label("Registro de incidencias");
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // --- Campos de texto ---
        txtEquipo = new TextField();
        txtEquipo.setPromptText("Ej: PC-AULA3-07");   // texto gris de ejemplo
        txtEquipo.setPrefWidth(280);

        txtDescripcion = new TextField();
        txtDescripcion.setPromptText("Ej: No enciende la pantalla");

        // --- GridPane: etiquetas en la columna 0, campos en la columna 1 ---
        // grid.add(nodo, columna, fila)
        GridPane grid = new GridPane();
        grid.setHgap(10);                 // separación horizontal entre columnas
        grid.setVgap(12);                 // separación vertical entre filas
        grid.setAlignment(Pos.CENTER);
        grid.add(new Label("Equipo:"), 0, 0);
        grid.add(txtEquipo, 1, 0);
        grid.add(new Label("Incidencia:"), 0, 1);
        grid.add(txtDescripcion, 1, 1);

        // --- Botones personalizados (componente del JAR) ---
        // Constructor completo: (texto, colorFondo, colorTexto, tamañoFuente).
        // Usamos colores y tamaños distintos para comprobar que el componente funciona.
        btnRegistrar = new BotonAccion("Registrar incidencia", "#2e7d32", "white", 14);
        btnRegistrar.setTextoAyuda("Comprueba los datos y muestra el resumen"); // Tooltip

        btnLimpiar = new BotonAccion("Limpiar", "#bdbdbd", "black", 12);
        btnLimpiar.setTextoAyuda("Vacía los campos del formulario");

        // HBox = fila horizontal; 10 = separación entre los botones
        HBox botones = new HBox(10, btnRegistrar, btnLimpiar);
        botones.setAlignment(Pos.CENTER);

        // --- Etiqueta de estado (el controlador escribe aquí los mensajes) ---
        lblEstado = new Label();

        // --- Configuración del propio VBox (this) ---
        setSpacing(18);                   // separación vertical entre hijos
        setPadding(new Insets(25));       // margen interior
        setAlignment(Pos.TOP_CENTER);
        getChildren().addAll(titulo, grid, botones, lblEstado);
    }

    // ===================== GETTERS =====================
    // El controlador los usa para leer los campos y poner los eventos.

    /** @return campo de texto del nombre del equipo */
    public TextField getTxtEquipo() {
        return txtEquipo;
    }

    /** @return campo de texto de la descripción */
    public TextField getTxtDescripcion() {
        return txtDescripcion;
    }

    /** @return botón "Registrar incidencia" */
    public BotonAccion getBtnRegistrar() {
        return btnRegistrar;
    }

    /** @return botón "Limpiar" */
    public BotonAccion getBtnLimpiar() {
        return btnLimpiar;
    }

    /** @return etiqueta donde se muestra el estado de la operación */
    public Label getLblEstado() {
        return lblEstado;
    }
}

package com.ejemplo;

import com.ejemplo.controlador.FormularioController;
import com.ejemplo.vista.FormularioView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Punto de entrada de la aplicación "Registro de incidencias".
 *
 * <p>Patrón MVC (Modelo - Vista - Controlador) hecho por código, sin FXML:</p>
 * <ul>
 *   <li><b>Modelo</b> ({@code modelo.Incidencia}): solo guarda datos.</li>
 *   <li><b>Vista</b> ({@code vista.*View}): solo crea y coloca controles.
 *       NO tiene ningún setOnAction.</li>
 *   <li><b>Controlador</b> ({@code controlador.*Controller}): recibe la vista,
 *       le pone los eventos y hace la lógica (validar, abrir ventanas...).</li>
 * </ul>
 *
 * <p>Flujo: Main crea la vista -&gt; se la pasa al controlador -&gt; el controlador
 * engancha los eventos -&gt; Main mete la vista en la Scene y muestra la ventana.</p>
 *
 * <p>Ejecutar: {@code mvn clean javafx:run}</p>
 *
 * @author Jorge Vazquez Poto
 * @version 1.0
 */
public class Main extends Application {

    /**
     * JavaFX llama a este método automáticamente después de {@code launch()}.
     * Se ejecuta en el hilo de aplicación de JavaFX.
     *
     * @param stage ventana principal que crea JavaFX por nosotros
     */
    @Override
    public void start(Stage stage) {
        // 1. Crear la VISTA (solo controles, sin eventos)
        FormularioView vista = new FormularioView();

        // 2. Crear el CONTROLADOR pasándole la vista.
        //    También le pasamos el Stage principal porque lo necesitará como
        //    "propietario" (owner) de la ventana modal secundaria.
        new FormularioController(vista, stage);

        // 3. La vista es un VBox (un Parent), así que puede ser la raíz de la Scene
        Scene scene = new Scene(vista, 460, 300);

        // 4. Configurar y mostrar la ventana
        stage.setTitle("Registro de incidencias - Taller informático");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Método main: solo arranca JavaFX. {@code launch()} crea el hilo de
     * JavaFX y acaba llamando a {@link #start(Stage)}.
     *
     * @param args argumentos de la línea de comandos (no se usan)
     */
    public static void main(String[] args) {
        launch(args);
    }
}

package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Ventana mínima JavaFX (proyecto de prueba, no forma parte de la entrega).
 *
 * <p>Ejecutar: {@code mvn clean javafx:run}</p>
 *
 * @author Jorge Vazquez Poto
 * @version 1.0
 */
public class Main extends Application {

    /**
     * JavaFX llama a este método después de {@code launch()}.
     * Muestra una ventana con un único Label centrado.
     *
     * @param stage ventana principal que crea JavaFX
     */
    @Override
    public void start(Stage stage) {
        // StackPane centra a su hijo (el Label) dentro de la ventana
        stage.setTitle("Práctica 3.1");
        stage.setScene(new Scene(new StackPane(new Label("Hola JavaFX")), 300, 200));
        stage.show();
    }

    /**
     * Método main: solo arranca JavaFX, que acaba llamando a {@link #start(Stage)}.
     *
     * @param args argumentos de la línea de comandos (no se usan)
     */
    public static void main(String[] args) {
        launch(args);
    }
}

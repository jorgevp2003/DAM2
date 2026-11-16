package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Ventana mínima JavaFX. Ejecutar con: mvn clean javafx:run
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Práctica 3.1");
        stage.setScene(new Scene(new StackPane(new Label("Hola JavaFX")), 300, 200));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

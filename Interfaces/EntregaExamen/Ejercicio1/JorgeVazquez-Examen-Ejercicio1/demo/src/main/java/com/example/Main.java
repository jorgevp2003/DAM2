package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Main extends Application {
    
    //Lanzamos la ventana principal con el fxml 
    @Override 
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
        getClass().getResource("/fxml.fxml")
        
    );
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stage.setTitle("Inicio Sesion");
    stage.setScene(scene);
    stage.show();
    }

    public static void main(String[] args) {
        launch();
    } 
}
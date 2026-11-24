package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * @Author Jorge Vazquez
     * @version 1.0
     * Clase principal de la aplicación. Inicia la vista del formulario y el controlador.
     */
    @Override
    public void start(Stage stage) {
        
        FormularioView vista = new FormularioView();
        new FormularioController(vista, stage);
        Scene scene = new Scene(vista, 460, 420);

        stage.setTitle("Consulta de incidencias");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

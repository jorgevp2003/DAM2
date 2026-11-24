package es.ciudadescolar.vista;

import es.ciudadescolar.componentes.CampoTexto;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class SegundaVista extends VBox {
    
    private CampoTexto altura;
    public SegundaVista(){
        Label titulo = new Label("Segundo Formulario");

       
        altura = new CampoTexto("Introduce tu altura");
        
        GridPane grid = new GridPane();
        
        grid.setHgap(12);
        grid.setVgap(15);

        grid.add(altura,0,0);

        grid.setAlignment(getAlignment());

        // Configuración general del VBox
        setSpacing(20);
        setPadding(new Insets(25));
        setAlignment(Pos.TOP_CENTER);

        // Añadir los elementos a la vista
        getChildren().addAll(titulo, grid);
    }

}

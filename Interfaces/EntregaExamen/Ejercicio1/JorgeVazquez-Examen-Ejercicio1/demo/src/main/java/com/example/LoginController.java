package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * @author Jorge Vazquez
 * @version 1.0
 * LoginController
 */
public class LoginController {
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private MenuBar menuLogin;
    @FXML
    private Button btnLogin;

    /**
     * Inicializo el controlador
     */
    @FXML
    public void initialize() {
        // Menú de opciones
        Menu menu = new Menu("Opciones");
        MenuItem item1 = new MenuItem("Ayuda");
        MenuItem item2 = new MenuItem("Salir");
        item1.setOnAction(event -> mostrarAyuda());
        item2.setOnAction(event -> salir());
        menu.getItems().addAll(item1, item2);
        menuLogin.getMenus().add(menu);

        //Boton de inicio
        btnLogin.setOnAction(event -> iniciarSesion());
        txtPassword.setOnAction(event -> iniciarSesion());
    }


    /**
     * Alerta de ayuda con contraseña
     */
    private void mostrarAyuda() {
        mostrarAlerta(Alert.AlertType.INFORMATION, "Ayuda","Introduce tu usuario y una contraseña que tenga:\n"+ " - Mínimo 8 caracteres\n"+ " - Al menos una letra\n"+ " - Al menos un número");
    }
    /**
     * Cierra la aplicacion
     */
    private void salir() {
        System.exit(0);
    }

    /**
     * Compruebo que la contraseña tiene 8 digitos
     * @param contrasena
     * @return
     */
    private boolean validarContrasena(String contrasena) {
        // Validar que la contraseña tenga al menos 8 caracteres
        if (contrasena == null || contrasena.length() < 8) {
            return false;
        }

        /**
         * Compruebo que la ontraseña tenga una letra y un numero minimo
         */
        boolean tieneLetra = false;
        boolean tieneNumero = false;

        for (char c : contrasena.toCharArray()) {
            if (Character.isLetter(c)) {
                tieneLetra = true;
            } else if (Character.isDigit(c)) {
                tieneNumero = true;
            }
        }

        return tieneLetra && tieneNumero;
    }

    /**
     * Comprueba que todos los campos esten rellenos y que la contraseña sea valid
     * Enseña un mensaje si asi es.
     */
    @FXML
    private void iniciarSesion() {
        String usuario = txtUsuario.getText().trim();
        String contrasena = txtPassword.getText();

        if (usuario.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Error de inicio de sesión",
                    "Debes introducir un usuario.");
            txtUsuario.requestFocus();
            return;
        }

        if (validarContrasena(contrasena)) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Inicio de sesión","¡Bienvenido, " + usuario + "! La contraseña es válida.");
            limpiarCampos();
        } else {
            
            mostrarAlerta(Alert.AlertType.ERROR, "Error de inicio de sesión","La contraseña debe tener al menos 8 caracteres, "+ "contener al menos una letra y un número.");
            txtPassword.clear();
            txtPassword.requestFocus();
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void limpiarCampos() {
        txtUsuario.clear();
        txtPassword.clear();
    }
}

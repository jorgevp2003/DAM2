package es.ciudadescolar.util;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/** Atajos para mostrar ventanas emergentes (Alert). */
public final class Alertas {

    private Alertas() { }

    public static void info(String titulo, String cabecera, String mensaje) {
        mostrar(Alert.AlertType.INFORMATION, titulo, cabecera, mensaje);
    }

    public static void error(String titulo, String cabecera, String mensaje) {
        mostrar(Alert.AlertType.ERROR, titulo, cabecera, mensaje);
    }

    public static void aviso(String titulo, String cabecera, String mensaje) {
        mostrar(Alert.AlertType.WARNING, titulo, cabecera, mensaje);
    }

    /** Pregunta Aceptar/Cancelar. Devuelve true si el usuario pulsa Aceptar. */
    public static boolean confirmar(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        Optional<ButtonType> respuesta = alerta.showAndWait();
        return respuesta.isPresent() && respuesta.get() == ButtonType.OK;
    }

    private static void mostrar(Alert.AlertType tipo, String titulo, String cabecera, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecera);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}

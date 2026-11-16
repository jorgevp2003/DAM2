package es.ciudadescolar.util;

import java.time.LocalDate;

/**
 * Validaciones reutilizables. Métodos estáticos y SIN JavaFX,
 * así se pueden probar con JUnit directamente (ver ValidadorTest).
 */
public final class Validador {

    private static final String LETRAS_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";

    private Validador() { }

    /** true si el texto no es null y tiene algo distinto de espacios. */
    public static boolean noVacio(String texto) {
        return texto != null && !texto.isBlank();
    }

    /** true si tiene al menos 'min' caracteres (sin contar espacios de los extremos). */
    public static boolean longitudMinima(String texto, int min) {
        return texto != null && texto.trim().length() >= min;
    }

    /** DNI: 8 números + letra correcta (número % 23). Acepta minúscula. */
    public static boolean esDniValido(String dni) {
        if (dni == null) {
            return false;
        }
        dni = dni.trim().toUpperCase();
        if (!dni.matches("\\d{8}[A-Z]")) {
            return false;
        }
        int numero = Integer.parseInt(dni.substring(0, 8));
        return dni.charAt(8) == LETRAS_DNI.charAt(numero % 23);
    }

    /** Email: algo@algo.dominio (sin espacios, una sola @). */
    public static boolean esEmailValido(String email) {
        return email != null && email.trim().matches("^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)*\\.[A-Za-z]{2,}$");
    }

    /** Teléfono español: 9 dígitos empezando por 6, 7, 8 o 9. */
    public static boolean esTelefonoValido(String telefono) {
        return telefono != null && telefono.trim().matches("[6789]\\d{8}");
    }

    /** true si la fecha existe y la persona tiene al menos 'edadMinima' años hoy. */
    public static boolean esMayorDe(LocalDate fechaNacimiento, int edadMinima) {
        return fechaNacimiento != null
            && !fechaNacimiento.plusYears(edadMinima).isAfter(LocalDate.now());
    }

    /** true si el texto es un número entero dentro del rango [min, max]. */
    public static boolean esEnteroEnRango(String texto, int min, int max) {
        try {
            int n = Integer.parseInt(texto.trim());
            return n >= min && n <= max;
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }
}

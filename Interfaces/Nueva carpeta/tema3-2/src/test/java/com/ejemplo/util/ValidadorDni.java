package com.ejemplo.util;

public class ValidadorDni {
    public static boolean esValido(String dni) {
    if (dni == null) {
        return false;
    }
    dni = dni.trim().toUpperCase();
    if (!dni.matches("\\d{8}[A-Z]")) {
        return false;
    }
    int numero = Integer.parseInt(dni.substring(0, 8));
    char letraIntroducida = dni.charAt(8);
    String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
    char letraCorrecta = letras.charAt(numero % 23);
    return letraIntroducida == letraCorrecta;
}
}

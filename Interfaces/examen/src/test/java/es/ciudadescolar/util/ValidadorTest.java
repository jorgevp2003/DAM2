package es.ciudadescolar.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

/** Tests de lógica pura (no necesitan JavaFX). */
class ValidadorTest {

    // ---------- DNI ----------
    @Test
    void dniCorrectoEsValido() {
        assertTrue(Validador.esDniValido("12345678Z"));
        assertTrue(Validador.esDniValido("12345678z"));   // minúscula también vale
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = { "12345678A", "1234", "ABCDEFGHZ", "123456789" })
    void dniIncorrectoEsInvalido(String dni) {
        assertFalse(Validador.esDniValido(dni));
    }

    // ---------- Email ----------
    @ParameterizedTest
    @ValueSource(strings = { "ana@gmail.com", "ana.lopez@correo.es", "a+b@sub.dominio.org" })
    void emailCorrecto(String email) {
        assertTrue(Validador.esEmailValido(email));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = { "anagmail.com", "ana@@gmail.com", "ana@gmail", "ana @gmail.com" })
    void emailIncorrecto(String email) {
        assertFalse(Validador.esEmailValido(email));
    }

    // ---------- Teléfono ----------
    @Test
    void telefono() {
        assertTrue(Validador.esTelefonoValido("612345678"));
        assertFalse(Validador.esTelefonoValido("12345678"));
        assertFalse(Validador.esTelefonoValido("512345678"));
        assertFalse(Validador.esTelefonoValido("6123a5678"));
        assertFalse(Validador.esTelefonoValido(null));
    }

    // ---------- Edad ----------
    @Test
    void edadMinima() {
        assertTrue(Validador.esMayorDe(LocalDate.now().minusYears(16), 16));   // justo 16 hoy
        assertFalse(Validador.esMayorDe(LocalDate.now().minusYears(16).plusDays(1), 16));
        assertFalse(Validador.esMayorDe(null, 16));
    }

    // ---------- Otros ----------
    @Test
    void textosYNumeros() {
        assertTrue(Validador.noVacio("hola"));
        assertFalse(Validador.noVacio("   "));
        assertTrue(Validador.longitudMinima("  Ana ", 3));
        assertFalse(Validador.longitudMinima("Al", 3));
        assertTrue(Validador.esEnteroEnRango("10", 8, 22));
        assertFalse(Validador.esEnteroEnRango("23", 8, 22));
        assertFalse(Validador.esEnteroEnRango("diez", 8, 22));
    }
}

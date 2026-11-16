package com.example.ejemplotodo

import org.junit.Assert.assertEquals
import org.junit.Test

/** Test unitario (JUnit 4) de la lógica de la calculadora. Se ejecuta en el PC, sin emulador. */
class CalculadoraTest {
    @Test fun suma() = assertEquals("%.2f".format(5.0), calcular("2+3"))
    @Test fun resta() = assertEquals("%.2f".format(-1.0), calcular("2-3"))
    @Test fun multiplica() = assertEquals("%.2f".format(6.0), calcular("2*3"))
    @Test fun divideEntreCero() = assertEquals("Error: división por 0", calcular("2/0"))
    @Test fun sinOperador() = assertEquals("42", calcular("42"))
}

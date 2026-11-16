package com.example.ejemplotodo

/**
 * MODELO DE DATOS compartido por ListView y GridView personalizados.
 * data class -> Kotlin genera solo: toString, equals, hashCode, copy, componentN
 * Valores por defecto -> se puede crear con parámetros con nombre: Ejemplo(titulo = "x", numeroEjemplo = 3)
 */
data class Ejemplo(
    var titulo: String = "",
    var subtitulo: String = "",
    var urlFoto: String = "",
    var numeroEjemplo: Int = 0
)

/** Datos de prueba reutilizables */
fun ejemplosDePrueba(): MutableList<Ejemplo> = mutableListOf(
    Ejemplo("Título Ejemplo 1", "Subtítulo Ejemplo 1", "", 1),
    Ejemplo("Título Ejemplo 2", "Subtítulo Ejemplo 2", "", 2),
    Ejemplo(titulo = "Título Ejemplo 3", subtitulo = "Subtítulo Ejemplo 3", numeroEjemplo = 3),
    Ejemplo(titulo = "Título Ejemplo 4", subtitulo = "Subtítulo Ejemplo 4", numeroEjemplo = 4),
    Ejemplo(titulo = "Título Ejemplo 5", subtitulo = "Subtítulo Ejemplo 5", numeroEjemplo = 5),
    Ejemplo(titulo = "Título Ejemplo 6", subtitulo = "Subtítulo Ejemplo 6", numeroEjemplo = 6),
)

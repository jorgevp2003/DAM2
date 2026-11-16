package com.example.examen

/** TODO: adaptar el modelo de datos al enunciado */
data class Elemento(
    val id: Int = 0,
    var titulo: String = "",
    var subtitulo: String = "",
    var imagen: Int = R.mipmap.ic_launcher,   // id de recurso (R.drawable.xxx)
)

fun elementosDePrueba(): MutableList<Elemento> = mutableListOf(
    Elemento(1, "Elemento 1", "Descripción 1"),
    Elemento(2, "Elemento 2", "Descripción 2"),
    Elemento(3, "Elemento 3", "Descripción 3"),
)

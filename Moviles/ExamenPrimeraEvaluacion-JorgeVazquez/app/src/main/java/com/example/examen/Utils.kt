package com.example.examen

import android.content.Context
import android.widget.Toast

/** Etiqueta para Logcat: Log.d(TAG, "mensaje") */
const val TAG = "EXAMEN"

/** Uso: toast("Hola")  desde cualquier Activity (o context.toast("Hola")) */
fun Context.toast(mensaje: String, largo: Boolean = false) =
    Toast.makeText(this, mensaje, if (largo) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()

/** dp -> píxeles (para LayoutParams / setPadding en vistas creadas por código) */
fun Context.dp(valor: Int): Int = (valor * resources.displayMetrics.density).toInt()

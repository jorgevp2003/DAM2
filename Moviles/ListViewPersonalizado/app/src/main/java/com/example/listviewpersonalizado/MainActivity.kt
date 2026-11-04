package com.example.listviewpersonalizado

import android.R
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import java.util.ArrayList


class MainActivity : Activity() {
    private lateinit var lista: ListView
    private lateinit var ejemploList: MutableList<Ejemplo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lista = ListView(this)
        setContentView(lista)

        ejemploList = ArrayList()

        ejemploList.add(Ejemplo("Título Ejemplo 1", "Subtítulo Ejemplo 1", "", 1))
        ejemploList.add(Ejemplo("Título Ejemplo 2", "Subtítulo Ejemplo 2", "", 2))
        ejemploList.add(Ejemplo(titulo = "Título Ejemplo 3", subtitulo = "Subtítulo Ejemplo 3", numeroEjemplo = 3))
        ejemploList.add(Ejemplo(titulo = "Título Ejemplo 4", subtitulo = "Subtítulo Ejemplo 4", numeroEjemplo = 4))

        val adaptadorEjemplo = MiAdaptadorEjemplo(this, ejemploList)
        lista.adapter = adaptadorEjemplo
    }

    class MiAdaptadorEjemplo(
        private val ctx: Context,
        private val ejemploList: List<Ejemplo>
    ) : ArrayAdapter<Ejemplo>(ctx, 0, ejemploList) {

        private fun dp(valor: Int): Int =
            (valor * ctx.resources.displayMetrics.density).toInt()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup):

                View {
            // Obtener la información del elemento que estamos recorriendo
            val elementoActual = ejemploList[position]

            // Fila horizontal: imagen + columna de textos
            val fila = LinearLayout(ctx)
            fila.orientation = LinearLayout.HORIZONTAL
            fila.setPadding(ctx.dp(16), ctx.dp(8), ctx.dp(16), ctx.dp(8))

            // Imagen (79dp x 76dp, como en los apuntes)
            val imageViewFoto = ImageView(ctx)
            imageViewFoto.setImageResource(R.mipmap.sym_def_app_icon)
            fila.addView(imageViewFoto, LinearLayout.LayoutParams(ctx.dp(79),
                ctx.dp(76)))

            // Columna vertical para los tres textos
            val columna = LinearLayout(ctx)
            columna.orientation = LinearLayout.VERTICAL
            columna.setPadding(ctx.dp(8), 0, 0, 0)
            fila.addView(columna)
            val textViewTitulo = TextView(ctx)
            textViewTitulo.setTypeface(null, Typeface.BOLD)
            textViewTitulo.setTextColor(Color.BLACK)
            columna.addView(textViewTitulo)
            val textViewSubtitulo = TextView(ctx)
            textViewSubtitulo.setPadding(0, ctx.dp(8), 0, 0)
            columna.addView(textViewSubtitulo)
            val textViewNumero = TextView(ctx)
            textViewNumero.setPadding(0, ctx.dp(8), 0, 0)
            columna.addView(textViewNumero)

            // Poner la información del elemento actual en la interfaz
            textViewTitulo.text = elementoActual.titulo
            textViewSubtitulo.text = elementoActual.subtitulo
            textViewNumero.text = elementoActual.numeroEjemplo.toString()
            return fila
        }
    }
    data class Ejemplo(
        var titulo: String = "",
        var subtitulo: String = "",
        var urlFoto: String = "",
        var numeroEjemplo: Int = 0
    )


}
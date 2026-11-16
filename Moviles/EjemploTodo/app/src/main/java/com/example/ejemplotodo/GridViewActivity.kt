package com.example.ejemplotodo

import android.app.Activity
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

/**
 * GRIDVIEW PERSONALIZADO (GridViewPersonalizado)
 *
 *  Igual que el ListView personalizado pero en cuadrícula:
 *   view.numColumns = GridView.AUTO_FIT (o 2, 3...)
 *   view.columnWidth / verticalSpacing / horizontalSpacing
 *  El adaptador construye una CELDA vertical: imagen arriba, textos debajo.
 */
class GridViewActivity : Activity() {

    private lateinit var view: GridView
    private lateinit var ejemploList: MutableList<Ejemplo>

    private fun dp(valor: Int): Int = (valor * resources.displayMetrics.density).toInt()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = GridView(this)
        view.numColumns = GridView.AUTO_FIT
        view.columnWidth = dp(150)
        view.verticalSpacing = dp(16)
        view.horizontalSpacing = dp(8)
        view.stretchMode = GridView.STRETCH_COLUMN_WIDTH
        view.setPadding(dp(8), dp(8), dp(8), dp(8))
        setContentView(view)

        ejemploList = ejemplosDePrueba()
        view.adapter = MiAdaptadorGrid(this, ejemploList)
        view.setOnItemClickListener { _, _, pos, _ ->
            Toast.makeText(this, "Celda ${ejemploList[pos].numeroEjemplo}", Toast.LENGTH_SHORT).show()
        }
    }

    class MiAdaptadorGrid(
        private val ctx: Context,
        private val ejemploList: List<Ejemplo>
    ) : ArrayAdapter<Ejemplo>(ctx, 0, ejemploList) {

        private fun dp(valor: Int): Int = (valor * ctx.resources.displayMetrics.density).toInt()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val elementoActual = ejemploList[position]

            val celda = LinearLayout(ctx)
            celda.orientation = LinearLayout.VERTICAL
            celda.gravity = Gravity.CENTER_HORIZONTAL
            celda.setPadding(dp(8), dp(8), dp(8), dp(8))
            celda.setBackgroundColor(0xFFEDE7F6.toInt())

            val imagen = ImageView(ctx)
            imagen.setImageResource(R.drawable.logociudadescolar)
            celda.addView(imagen, LinearLayout.LayoutParams(dp(80), dp(80)))

            val titulo = TextView(ctx)
            titulo.text = elementoActual.titulo
            titulo.setTypeface(null, Typeface.BOLD)
            titulo.gravity = Gravity.CENTER
            celda.addView(titulo)

            val subtitulo = TextView(ctx)
            subtitulo.text = elementoActual.subtitulo
            subtitulo.gravity = Gravity.CENTER
            celda.addView(subtitulo)

            return celda
        }
    }
}

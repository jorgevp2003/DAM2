package com.example.gridviewpersonalizado

import android.content.Context
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class MiAdaptadorEjemplo(
    private val ctx: Context,
    private val ejemploList: List<Ejemplo>
) : BaseAdapter() {

    override fun getCount(): Int = ejemploList.size

    override fun getItem(position: Int): Any = ejemploList[position]

    override fun getItemId(position: Int): Long = ejemploList[position].id.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val elementoActual = ejemploList[position]

        val celda = LinearLayout(ctx)
        celda.orientation = LinearLayout.VERTICAL
        celda.gravity = Gravity.CENTER_HORIZONTAL
        celda.setPadding(ctx.dp(8), ctx.dp(8), ctx.dp(8), ctx.dp(8))

        val imagen = ImageView(ctx)
        imagen.layoutParams = LinearLayout.LayoutParams(ctx.dp(120), ctx.dp(120))
        imagen.scaleType = ImageView.ScaleType.FIT_CENTER
        imagen.setImageResource(elementoActual.imagen)

        val titulo = TextView(ctx)
        titulo.text = elementoActual.titulo
        titulo.textSize = 18f
        titulo.setTypeface(null, Typeface.BOLD)
        titulo.gravity = Gravity.CENTER

        val subtitulo = TextView(ctx)
        subtitulo.text = elementoActual.subtitulo
        subtitulo.textSize = 14f
        subtitulo.gravity = Gravity.CENTER

        celda.addView(imagen)
        celda.addView(titulo)
        celda.addView(subtitulo)

        return celda
    }
}

// Convierte dp a píxeles
fun Context.dp(valor: Int): Int =
    (valor * resources.displayMetrics.density).toInt()
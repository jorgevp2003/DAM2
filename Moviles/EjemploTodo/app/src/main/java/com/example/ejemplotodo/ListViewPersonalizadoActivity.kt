package com.example.ejemplotodo

import android.app.Activity
import android.app.AlertDialog
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
import android.widget.Toast

/**
 * LISTVIEW PERSONALIZADO (ListViewPersonalizado)
 *
 *  1. data class con los datos (Ejemplo)
 *  2. Adaptador propio que hereda de ArrayAdapter<Ejemplo>
 *  3. Sobrescribir getView(position, convertView, parent): construye la FILA
 *     y rellena sus vistas con el elemento [position]
 *  4. lista.adapter = MiAdaptadorEjemplo(this, lista)
 */
class ListViewPersonalizadoActivity : Activity() {

    private lateinit var lista: ListView
    private lateinit var ejemploList: MutableList<Ejemplo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lista = ListView(this)
        setContentView(lista)

        ejemploList = ejemplosDePrueba()
        lista.adapter = MiAdaptadorEjemplo(this, ejemploList)

        // CLICK EN UN ELEMENTO: saca un MENSAJE (AlertDialog) con los datos de la fila pulsada
        lista.setOnItemClickListener { _, _, pos, _ ->
            val elemento = ejemploList[pos]
            AlertDialog.Builder(this)
                .setTitle(elemento.titulo)
                .setMessage("Has pulsado el elemento ${pos + 1}\n\n" +
                        "Subtítulo: ${elemento.subtitulo}\n" +
                        "Número: ${elemento.numeroEjemplo}")
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton("Aceptar") { dialog, _ -> dialog.dismiss() }
                .show()
        }

        // CLICK LARGO: mensaje corto (Toast). Devolver true = evento consumido
        lista.setOnItemLongClickListener { _, _, pos, _ ->
            Toast.makeText(this, "Pulsación larga: ${ejemploList[pos].titulo}", Toast.LENGTH_SHORT).show()
            true
        }
    }

    class MiAdaptadorEjemplo(
        private val ctx: Context,
        private val ejemploList: List<Ejemplo>
    ) : ArrayAdapter<Ejemplo>(ctx, 0, ejemploList) {

        // Convierte dp a píxeles (las LayoutParams van en píxeles)
        private fun dp(valor: Int): Int = (valor * ctx.resources.displayMetrics.density).toInt()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            // Información del elemento que estamos pintando
            val elementoActual = ejemploList[position]

            // Fila horizontal: imagen + columna de textos
            val fila = LinearLayout(ctx)
            fila.orientation = LinearLayout.HORIZONTAL
            fila.setPadding(dp(16), dp(8), dp(16), dp(8))

            val imageViewFoto = ImageView(ctx)
            imageViewFoto.setImageResource(R.mipmap.ic_launcher)
            fila.addView(imageViewFoto, LinearLayout.LayoutParams(dp(79), dp(76)))

            // Columna vertical para los tres textos
            val columna = LinearLayout(ctx)
            columna.orientation = LinearLayout.VERTICAL
            columna.setPadding(dp(8), 0, 0, 0)
            fila.addView(columna)

            val textViewTitulo = TextView(ctx)
            textViewTitulo.setTypeface(null, Typeface.BOLD)
            textViewTitulo.setTextColor(Color.BLACK)
            columna.addView(textViewTitulo)

            val textViewSubtitulo = TextView(ctx)
            textViewSubtitulo.setPadding(0, dp(8), 0, 0)
            columna.addView(textViewSubtitulo)

            val textViewNumero = TextView(ctx)
            textViewNumero.setPadding(0, dp(8), 0, 0)
            columna.addView(textViewNumero)

            // Poner la información en la interfaz
            textViewTitulo.text = elementoActual.titulo
            textViewSubtitulo.text = elementoActual.subtitulo
            textViewNumero.text = elementoActual.numeroEjemplo.toString()

            return fila
        }
    }
}

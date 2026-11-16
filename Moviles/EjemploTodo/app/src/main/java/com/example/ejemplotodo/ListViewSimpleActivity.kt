package com.example.ejemplotodo

import android.app.Activity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar

/**
 * LISTVIEW SIMPLE (ListViewSimple)
 *
 *  ListView + ArrayAdapter(contexto, layoutDeFila, lista)
 *  android.R.layout.simple_list_item_1 = fila del sistema con un solo TextView
 *  setOnItemClickListener { parent, view, position, id -> ... }
 *
 *  Aquí la interfaz se crea POR CÓDIGO (sin XML), igual que en clase.
 */
class ListViewSimpleActivity : Activity() {

    private val ejemplosList = mutableListOf("Ejemplo 1", "Ejemplo 2", "Ejemplo 3", "Ejemplo 4")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Contenedor vertical: de arriba a abajo
        val raiz = LinearLayout(this)
        raiz.orientation = LinearLayout.VERTICAL

        val toolbar = Toolbar(this)
        toolbar.title = "ListView simple"
        raiz.addView(toolbar, LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ))

        val texto = TextView(this)
        texto.text = "Pulsa = Toast · Mantén pulsado = borrar"
        texto.setPadding(32, 16, 32, 16)
        raiz.addView(texto)

        val lista = ListView(this)
        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, ejemplosList)
        lista.adapter = adaptador
        raiz.addView(lista)

        // Click corto
        lista.setOnItemClickListener { _, _, posicion, _ ->
            Toast.makeText(this, "El ejemplo seleccionado es: ${ejemplosList[posicion]}", Toast.LENGTH_SHORT).show()
        }
        // Click largo: borrar y avisar al adaptador
        lista.setOnItemLongClickListener { _, _, posicion, _ ->
            ejemplosList.removeAt(posicion)
            adaptador.notifyDataSetChanged()   // ¡IMPORTANTE para refrescar la lista!
            true
        }

        setContentView(raiz)
        setActionBar(toolbar)
    }
}

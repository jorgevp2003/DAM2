package com.example.listviewpersonalizado

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar

class MainActivity : Activity() {
    private val ejemploslist = listOf("Ejemplo 1", "Ejemplo 2", "Ejemplo 3", "Ejemplo 4")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val raiz = LinearLayout(this)
        raiz.orientation = LinearLayout.VERTICAL

        // Si el tema del manifiesto no trae barra, creamos una nosotros
        var toolbar: Toolbar? = null
        if (actionBar == null) {
            toolbar = Toolbar(this)
            toolbar.title = "ListViewSimple"
            raiz.addView(toolbar, LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ))
        }

        val texto = TextView(this)
        texto.text = "Pulsa los tres puntos de la barra superior"
        raiz.addView(texto)

        setContentView(raiz)
        if (toolbar != null) setActionBar(toolbar)
    }

    // Crear el menú: una opción por cada elemento de la lista
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        ejemploslist.forEachIndexed { posicion, ejemplo ->
            menu.add(Menu.NONE, posicion, Menu.NONE, ejemplo)
        }
        return true
    }

    // Al pulsar una opción, mostrar el mensaje
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId in ejemploslist.indices) {
            Toast.makeText(
                this,
                "El ejemplo seleccionado es: ${ejemploslist[item.itemId]}",
                Toast.LENGTH_SHORT
            ).show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
package com.example.menus

import android.app.Activity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.Toast
import android.widget.Toolbar

class MainActivity : Activity() {
    /*===============================================================
                            ASIGNAMOS ID A CADA OPCION
     ================================================================*/
    companion object {
        // Cataluña
        const val OPCION_BARCELONA = 1
        const val OPCION_GIRONA = 2
        const val OPCION_LLEIDA = 3
        const val OPCION_TARRAGONA = 4
        // Aragón
        const val OPCION_HUESCA = 5
        const val OPCION_TERUEL = 6
        const val OPCION_ZARAGOZA = 7

        const val OPCION_ALCOBENDAS = 8
        const val OPCION_LASTABLAS = 9
        const val OPCION_TRESOLIVOS = 10
    }

    // Fuera de onCreate para poder usarla en onCreateContextMenu
    private val comunidades = listOf("Madrid", "Cataluña", "Aragón")

    /*===============================================================
                            PANTALLA: BARRA + LISTA
     ================================================================*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Contenedor vertical: de arriba a abajo
        val raiz = LinearLayout(this)
        raiz.orientation = LinearLayout.VERTICAL

        val toolbar = Toolbar(this)
        toolbar.title = "Menu contextual"
        raiz.addView(toolbar, LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ))

        // Lista de comunidades
        val lista = ListView(this)
        lista.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, comunidades)
        raiz.addView(lista)

        // La lista tendrá menú contextual al mantener pulsado
        registerForContextMenu(lista)

        setContentView(raiz)
        setActionBar(toolbar)
    }

    /*===============================================================
                CREAMOS EL MENU SEGUN LA COMUNIDAD PULSADA
     ================================================================*/
    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        // ¿Qué fila se ha mantenido pulsada?
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val comunidad = comunidades[info.position]

        when (comunidad) {

            "Cataluña" -> {
                menu.setHeaderTitle("Cataluña")
                menu.add(Menu.NONE, OPCION_BARCELONA, Menu.NONE, "Barcelona")
                menu.add(Menu.NONE, OPCION_GIRONA, Menu.NONE, "Girona")
                menu.add(Menu.NONE, OPCION_LLEIDA, Menu.NONE, "Lleida")
                menu.add(Menu.NONE, OPCION_TARRAGONA, Menu.NONE, "Tarragona")
            }
            "Aragón" -> {
                menu.setHeaderTitle("Aragón")
                menu.add(Menu.NONE, OPCION_HUESCA, Menu.NONE, "Huesca")
                menu.add(Menu.NONE, OPCION_TERUEL, Menu.NONE, "Teruel")
                menu.add(Menu.NONE, OPCION_ZARAGOZA, Menu.NONE, "Zaragoza")
            }
        }
    }

    /*===============================================================
                    CUANDO SE PULSE UNA PROVINCIA PASA:
     ================================================================*/
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val poblaciones = when (item.itemId) {
            OPCION_BARCELONA -> "Sabadell, Terrassa y Badalona"
            OPCION_GIRONA -> "Figueres, Blanes y Olot"
            OPCION_LLEIDA -> "Tàrrega, Balaguer y Mollerussa"
            OPCION_TARRAGONA -> "Reus, Tortosa y Cambrils"
            OPCION_HUESCA -> "Jaca, Barbastro y Monzón"
            OPCION_TERUEL -> "Alcañiz, Andorra y Calamocha"
            OPCION_ZARAGOZA -> "Calatayud, Ejea de los Caballeros y Tarazona"
            else -> return super.onContextItemSelected(item)
        }
        Toast.makeText(this, "${item.title}: $poblaciones", Toast.LENGTH_LONG).show()
        return true
    }
}
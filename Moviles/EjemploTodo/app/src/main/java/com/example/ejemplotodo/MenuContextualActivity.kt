package com.example.ejemplotodo

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

/**
 * MENÚ CONTEXTUAL (Menu2) -> aparece al MANTENER PULSADO una vista
 *
 *  1. registerForContextMenu(vista)
 *  2. onCreateContextMenu(menu, v, menuInfo) -> crear opciones
 *       en un ListView: (menuInfo as AdapterView.AdapterContextMenuInfo).position = fila pulsada
 *  3. onContextItemSelected(item) -> al pulsar una opción
 */
class MenuContextualActivity : Activity() {

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
        // Madrid
        const val OPCION_ALCOBENDAS = 8
        const val OPCION_LASTABLAS = 9
        const val OPCION_TRESOLIVOS = 10
        // Menú de opciones
        const val OPCION_MADRID = 11
        const val OPCION_CATALUNA = 12
        const val OPCION_ARAGON = 13
    }

    // Fuera de onCreate para poder usarla en onCreateContextMenu
    private val comunidades = listOf("Madrid", "Cataluña", "Aragón")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val raiz = LinearLayout(this)
        raiz.orientation = LinearLayout.VERTICAL

        val toolbar = Toolbar(this)
        toolbar.title = "Menú contextual (mantén pulsado)"
        raiz.addView(toolbar, LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ))

        val lista = ListView(this)
        lista.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, comunidades)
        raiz.addView(lista)

        // La lista tendrá menú contextual al mantener pulsado
        registerForContextMenu(lista)

        setContentView(raiz)
        setActionBar(toolbar)
    }

    // ---- Menú de opciones normal (3 puntos) ----
    override fun onCreateOptionsMenu(miMenu: Menu): Boolean {
        miMenu.add(0, OPCION_MADRID, 0, "Madrid")
        miMenu.add(0, OPCION_CATALUNA, 0, "Cataluña")
        miMenu.add(0, OPCION_ARAGON, 0, "Aragón")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val capital = when (item.itemId) {
            OPCION_MADRID -> "Madrid"
            OPCION_CATALUNA -> "Barcelona"
            OPCION_ARAGON -> "Zaragoza"
            else -> return super.onOptionsItemSelected(item)
        }
        Toast.makeText(this, "Capital de ${item.title}: $capital", Toast.LENGTH_SHORT).show()
        return true
    }

    // ---- Crear el menú contextual según la fila pulsada ----
    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val comunidad = comunidades[info.position]

        menu.setHeaderTitle(comunidad)
        when (comunidad) {
            "Madrid" -> {
                menu.add(Menu.NONE, OPCION_ALCOBENDAS, Menu.NONE, "Alcobendas")
                menu.add(Menu.NONE, OPCION_LASTABLAS, Menu.NONE, "Las Tablas")
                menu.add(Menu.NONE, OPCION_TRESOLIVOS, Menu.NONE, "Tres Olivos")
            }
            "Cataluña" -> {
                menu.add(Menu.NONE, OPCION_BARCELONA, Menu.NONE, "Barcelona")
                menu.add(Menu.NONE, OPCION_GIRONA, Menu.NONE, "Girona")
                menu.add(Menu.NONE, OPCION_LLEIDA, Menu.NONE, "Lleida")
                menu.add(Menu.NONE, OPCION_TARRAGONA, Menu.NONE, "Tarragona")
            }
            "Aragón" -> {
                menu.add(Menu.NONE, OPCION_HUESCA, Menu.NONE, "Huesca")
                menu.add(Menu.NONE, OPCION_TERUEL, Menu.NONE, "Teruel")
                menu.add(Menu.NONE, OPCION_ZARAGOZA, Menu.NONE, "Zaragoza")
            }
        }
    }

    // ---- Al pulsar una opción del menú contextual ----
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val poblaciones = when (item.itemId) {
            OPCION_BARCELONA -> "Sabadell, Terrassa y Badalona"
            OPCION_GIRONA -> "Figueres, Blanes y Olot"
            OPCION_LLEIDA -> "Tàrrega, Balaguer y Mollerussa"
            OPCION_TARRAGONA -> "Reus, Tortosa y Cambrils"
            OPCION_HUESCA -> "Jaca, Barbastro y Monzón"
            OPCION_TERUEL -> "Alcañiz, Andorra y Calamocha"
            OPCION_ZARAGOZA -> "Calatayud, Ejea de los Caballeros y Tarazona"
            OPCION_ALCOBENDAS -> "Barrio de La Moraleja, El Soto, Centro"
            OPCION_LASTABLAS -> "Barrio del distrito de Fuencarral-El Pardo"
            OPCION_TRESOLIVOS -> "Barrio del distrito de Fuencarral-El Pardo"
            else -> return super.onContextItemSelected(item)
        }
        Toast.makeText(this, "${item.title}: $poblaciones", Toast.LENGTH_LONG).show()
        return true
    }
}

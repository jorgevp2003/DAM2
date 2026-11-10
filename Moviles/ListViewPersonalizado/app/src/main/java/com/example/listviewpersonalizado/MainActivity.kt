package com.example.menus

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
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

        // Madrid
        const val OPCION_ALCOBENDAS = 8
        const val OPCION_LASTABLAS = 9
        const val OPCION_TRESOLIVOS = 10
    }

    /*===============================================================
                            PANTALLA: SOLO LA BARRA
     ================================================================*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val raiz = LinearLayout(this)
        raiz.orientation = LinearLayout.VERTICAL

        val toolbar = Toolbar(this)
        toolbar.title = "Menu"
        raiz.addView(
            toolbar, LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        )

        setContentView(raiz)
        setActionBar(toolbar)
    }

    /*===============================================================
                CREAMOS EL MENU CON UN SUBMENU POR COMUNIDAD
     ================================================================*/
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val madrid = menu.addSubMenu("Madrid")
        madrid.add(Menu.NONE, OPCION_ALCOBENDAS, Menu.NONE, "Alcobendas")
        madrid.add(Menu.NONE, OPCION_LASTABLAS, Menu.NONE, "Las Tablas")
        madrid.add(Menu.NONE, OPCION_TRESOLIVOS, Menu.NONE, "Tres Olivos")

        val cataluna = menu.addSubMenu("Cataluña")
        cataluna.add(Menu.NONE, OPCION_BARCELONA, Menu.NONE, "Barcelona")
        cataluna.add(Menu.NONE, OPCION_GIRONA, Menu.NONE, "Girona")
        cataluna.add(Menu.NONE, OPCION_LLEIDA, Menu.NONE, "Lleida")
        cataluna.add(Menu.NONE, OPCION_TARRAGONA, Menu.NONE, "Tarragona")

        val aragon = menu.addSubMenu("Aragón")
        aragon.add(Menu.NONE, OPCION_HUESCA, Menu.NONE, "Huesca")
        aragon.add(Menu.NONE, OPCION_TERUEL, Menu.NONE, "Teruel")
        aragon.add(Menu.NONE, OPCION_ZARAGOZA, Menu.NONE, "Zaragoza")

        return true
    }

    /*===============================================================
                    CUANDO SE PULSE UNA OPCION PASA:
     ================================================================*/
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val mensaje = when (item.itemId) {
            OPCION_ALCOBENDAS, OPCION_LASTABLAS, OPCION_TRESOLIVOS -> "${item.title}"
            OPCION_BARCELONA -> "${item.title}: Sabadell, Terrassa y Badalona"
            OPCION_GIRONA -> "${item.title}: Figueres, Blanes y Olot"
            OPCION_LLEIDA -> "${item.title}: Tàrrega, Balaguer y Mollerussa"
            OPCION_TARRAGONA -> "${item.title}: Reus, Tortosa y Cambrils"
            OPCION_HUESCA -> "${item.title}: Jaca, Barbastro y Monzón"
            OPCION_TERUEL -> "${item.title}: Alcañiz, Andorra y Calamocha"
            OPCION_ZARAGOZA -> "${item.title}: Calatayud, Ejea de los Caballeros y Tarazona"
            else -> return super.onOptionsItemSelected(item)
        }
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
        return true
    }
}
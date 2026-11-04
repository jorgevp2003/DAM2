package com.example.menus

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.widget.TextView
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import android.widget.Toolbar

class MainActivity : Activity() {
    /*===============================================================
                            ASIGNAMOS ID A CADA OPCION
     ================================================================*/
    companion object {
        const val OPCION_MENU_1 = 1
        const val OPCION_MENU_2 = 2
        const val OPCION_MENU_3 = 3

    }

    /*===============================================================
                            CREAMOS EL MENU
     ================================================================*/
    override fun onCreateOptionsMenu(mimenu: Menu):
            Boolean {
        mimenu.add(
            0,
            OPCION_MENU_1, 0, "Opcion de menu 1"
        )
        mimenu.add(
            0,
            OPCION_MENU_2, 0, "Opcion de menu 2"
        )
        mimenu.add(
            0,
            OPCION_MENU_3, 0, "Opcion de menu 3"
        )

        return true
        }

    /*===============================================================
                    CUANDO SE PULSE UNA DE LAS OPCIONES PASA:
     ================================================================*/
    override fun onOptionsItemSelected(opcion_menu: MenuItem): Boolean {
        when(opcion_menu.itemId){
            OPCION_MENU_1 -> {
                Toast.makeText(this, "Opcion menu 1", Toast.LENGTH_LONG).show()
                return true
            }
            OPCION_MENU_2 -> {
                Toast.makeText(this, "Opcion menu 2", Toast.LENGTH_LONG).show()
                return true
            }
            OPCION_MENU_3 -> {
                Toast.makeText(this, "Opcion menu 3", Toast.LENGTH_LONG).show()
                return true
            }
        }

        return super.onOptionsItemSelected(opcion_menu)
    }
    /*===============================================================
                            BARRA DEL MENU
     ================================================================*/

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        //Contenedor vertical: De arriba a abajo
        val raiz = LinearLayout(this)
        raiz.orientation = LinearLayout.VERTICAL

        val toolbar = Toolbar(this)
        toolbar.title = "CreacionMenu"

        raiz.addView(toolbar,LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ))

        val texto = TextView(this)
        texto.text = "Pulsa los tres puntos en la barra"
        raiz.addView(texto)
        setContentView(raiz)
        setActionBar(toolbar)
    }
}



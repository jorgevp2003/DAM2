package com.example.ejemplotodo

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar

/**
 * MENÚ DE OPCIONES (Menus) -> los 3 puntitos de la barra superior
 *
 *  onCreateOptionsMenu(menu)      -> se CREA el menú (1 vez). return true para mostrarlo.
 *      menu.add(grupo, ID, orden, "Texto")
 *      menu.addSubMenu("Texto")   -> submenú
 *      menuInflater.inflate(R.menu.menu_opciones, menu)  -> desde XML (res/menu)
 *  onOptionsItemSelected(item)    -> al PULSAR. when(item.itemId) { ... }  return true si lo gestionas.
 *
 *  Si el tema no tiene ActionBar, creamos un Toolbar y lo ponemos con setActionBar(toolbar).
 */
class MenuOpcionesActivity : Activity() {

    companion object {
        const val OPCION_MENU_1 = 1
        const val OPCION_MENU_2 = 2
        const val OPCION_MENU_3 = 3
        const val SUB_ROJO = 10
        const val SUB_VERDE = 11
    }

    private lateinit var texto: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val raiz = LinearLayout(this)
        raiz.orientation = LinearLayout.VERTICAL

        val toolbar = Toolbar(this)
        toolbar.title = "Menú de opciones"
        raiz.addView(toolbar, LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ))

        texto = TextView(this)
        texto.text = "Pulsa los tres puntos en la barra"
        texto.textSize = 20f
        texto.setPadding(32, 32, 32, 32)
        raiz.addView(texto)

        setContentView(raiz)
        setActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(mimenu: Menu): Boolean {
        // 1) Opciones desde XML (res/menu/menu_opciones.xml)
        menuInflater.inflate(R.menu.menu_opciones, mimenu)

        // 2) Opciones por código
        mimenu.add(0, OPCION_MENU_1, 0, "Opción de menú 1")
        mimenu.add(0, OPCION_MENU_2, 0, "Opción de menú 2")
        mimenu.add(0, OPCION_MENU_3, 0, "Opción de menú 3")

        // 3) Submenú
        val sub = mimenu.addSubMenu("Colores")
        sub.add(0, SUB_ROJO, 0, "Rojo")
        sub.add(0, SUB_VERDE, 0, "Verde")
        return true
    }

    override fun onOptionsItemSelected(opcionMenu: MenuItem): Boolean {
        when (opcionMenu.itemId) {
            R.id.accion_buscar -> mostrar("Buscar (desde XML)")
            R.id.accion_ajustes -> mostrar("Ajustes (desde XML)")
            OPCION_MENU_1 -> mostrar("Opción menú 1")
            OPCION_MENU_2 -> mostrar("Opción menú 2")
            OPCION_MENU_3 -> mostrar("Opción menú 3")
            SUB_ROJO -> { texto.setTextColor(0xFFD32F2F.toInt()); mostrar("Rojo") }
            SUB_VERDE -> { texto.setTextColor(0xFF388E3C.toInt()); mostrar("Verde") }
            else -> return super.onOptionsItemSelected(opcionMenu)
        }
        return true
    }

    private fun mostrar(msg: String) {
        texto.text = msg
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}

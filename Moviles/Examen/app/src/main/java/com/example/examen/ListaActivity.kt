package com.example.examen

import android.app.Activity
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import android.widget.Toolbar

/**
 * Plantilla "clásica" (android.app.Activity, interfaz por código):
 *  Toolbar + ListView con adaptador personalizado + menú de opciones + menú contextual.
 *  Para un GridView: cambiar ListView por GridView y poner numColumns.
 */
class ListaActivity : Activity() {

    companion object {
        // TODO: IDs de las opciones
        const val OPCION_1 = 1
        const val OPCION_2 = 2
        const val CTX_BORRAR = 100
        const val CTX_VER = 101
    }

    private val elementos = elementosDePrueba()
    private lateinit var adaptador: MiAdaptador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val raiz = LinearLayout(this)
        raiz.orientation = LinearLayout.VERTICAL

        val toolbar = Toolbar(this)
        toolbar.title = "Lista"   // TODO
        raiz.addView(toolbar, LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ))

        val lista = ListView(this)
        adaptador = MiAdaptador(this, elementos)
        lista.adapter = adaptador
        lista.setOnItemClickListener { _, _, pos, _ ->
            toast("Pulsado: ${elementos[pos].titulo}")   // TODO
        }
        registerForContextMenu(lista)
        raiz.addView(lista)

        setContentView(raiz)
        setActionBar(toolbar)
    }

    // ---------------- MENÚ DE OPCIONES ----------------
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_principal, menu)   // desde XML
        menu.add(0, OPCION_1, 0, "Opción 1")                 // por código
        menu.add(0, OPCION_2, 0, "Opción 2")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.accion_ajustes -> toast("Ajustes")
            OPCION_1 -> toast("Opción 1")   // TODO
            OPCION_2 -> toast("Opción 2")   // TODO
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    // ---------------- MENÚ CONTEXTUAL ----------------
    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val pos = (menuInfo as AdapterView.AdapterContextMenuInfo).position
        menu.setHeaderTitle(elementos[pos].titulo)
        menu.add(Menu.NONE, CTX_VER, Menu.NONE, "Ver")
        menu.add(Menu.NONE, CTX_BORRAR, Menu.NONE, "Borrar")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val pos = (item.menuInfo as AdapterView.AdapterContextMenuInfo).position
        when (item.itemId) {
            CTX_VER -> toast(elementos[pos].toString())
            CTX_BORRAR -> {
                elementos.removeAt(pos)
                adaptador.notifyDataSetChanged()
            }
            else -> return super.onContextItemSelected(item)
        }
        return true
    }

    // ---------------- ADAPTADOR PERSONALIZADO ----------------
    class MiAdaptador(private val ctx: Context, private val datos: List<Elemento>) :
        ArrayAdapter<Elemento>(ctx, 0, datos) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val e = datos[position]

            val fila = LinearLayout(ctx)
            fila.orientation = LinearLayout.HORIZONTAL
            fila.setPadding(ctx.dp(16), ctx.dp(8), ctx.dp(16), ctx.dp(8))

            val imagen = ImageView(ctx)
            imagen.setImageResource(e.imagen)
            fila.addView(imagen, LinearLayout.LayoutParams(ctx.dp(64), ctx.dp(64)))

            val columna = LinearLayout(ctx)
            columna.orientation = LinearLayout.VERTICAL
            columna.setPadding(ctx.dp(8), 0, 0, 0)
            fila.addView(columna)

            val titulo = TextView(ctx)
            titulo.setTypeface(null, Typeface.BOLD)
            titulo.text = e.titulo
            columna.addView(titulo)

            val subtitulo = TextView(ctx)
            subtitulo.text = e.subtitulo
            columna.addView(subtitulo)

            // TODO: más campos

            return fila
        }
    }
}

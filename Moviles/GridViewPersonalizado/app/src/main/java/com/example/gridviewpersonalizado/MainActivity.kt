package com.example.gridviewpersonalizado


import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.LinearLayout
import java.util.ArrayList

class MainActivity : Activity() {

    private lateinit var view: GridView
    private lateinit var ejemploList: MutableList<Ejemplo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        view = GridView(this)
        view.numColumns = GridView.AUTO_FIT
        view.columnWidth = dp(150)
        view.verticalSpacing = dp(16)
        setContentView(view)

        ejemploList = ArrayList()
        ejemploList.add(Ejemplo("Titulo Ejemplo 1", "Subtitulo Ejemplo 1", "", 1))
        ejemploList.add(Ejemplo("Titulo Ejemplo 2", "Subtitulo Ejemplo 2", "", 2))
        ejemploList.add(Ejemplo("Titulo Ejemplo 3", "Subtitulo Ejemplo 3", "", 3))
        ejemploList.add(Ejemplo("Titulo Ejemplo 4", "Subtitulo Ejemplo 4", "", 4))

        val adaptadorEjemplo = MiAdaptadorEjemplo(this, ejemploList)
        view.adapter = adaptadorEjemplo


    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View{
        val elementActual = ejemploList[position]

        val celda = LinearLayout(ctx)
        celda.orientation = LinearLayout.VERTICAL
        celda.gravity = Gravity.CENTER_HORIZONTAL
        celda.setPadding(dp(8),dp(8),dp(8),dp(8))
        

    }

}


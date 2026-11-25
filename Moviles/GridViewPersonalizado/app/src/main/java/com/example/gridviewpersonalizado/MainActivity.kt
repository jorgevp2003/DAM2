package com.example.gridviewpersonalizado

import android.app.Activity
import android.os.Bundle
import android.widget.GridView

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
        ejemploList.add(Ejemplo("Titulo Ejemplo 1", "Subtitulo Ejemplo 1", R.drawable.logo, 1))
        ejemploList.add(Ejemplo("Titulo Ejemplo 2", "Subtitulo Ejemplo 2", R.drawable.logo, 2))
        ejemploList.add(Ejemplo("Titulo Ejemplo 3", "Subtitulo Ejemplo 3", R.drawable.logo, 3))
        ejemploList.add(Ejemplo("Titulo Ejemplo 4", "Subtitulo Ejemplo 4", R.drawable.logo, 4))

        view.adapter = MiAdaptadorEjemplo(this, ejemploList)
    }
}
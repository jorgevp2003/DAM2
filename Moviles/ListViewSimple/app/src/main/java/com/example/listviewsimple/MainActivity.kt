package com.example.listviewsimple

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import java.util.ArrayList


class MainActivity : Activity(), AdapterView.OnItemClickListener {
    private lateinit var lista : ListView
    private lateinit var ejemploslist :MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        lista = ListView(this)
        setContentView(lista)

        ejemploslist = ArrayList()
        ejemploslist.add("Ejemplo1")
        ejemploslist.add("Ejemplo2")
        ejemploslist.add("Ejemplo3")
        ejemploslist.add("Ejemplo4")

        val adaptadorEjemplo = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, ejemploslist)
        lista.adapter = adaptadorEjemplo
        lista.onItemClickListener = this
    }


    override fun onItemClick(parent: AdapterView<*>?, view: View?, position:Int, id:Long){
        Toast.makeText(this,
            "El ejemplo seleccionado es: "+ejemploslist[position],
            Toast.LENGTH_SHORT).show()
    }

}


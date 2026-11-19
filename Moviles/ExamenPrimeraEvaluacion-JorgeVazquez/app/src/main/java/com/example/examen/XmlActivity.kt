package com.example.examen

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/** Plantilla de pantalla con layout XML (res/layout/activity_xml.xml). */
class XmlActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xml)

        val etEntrada = findViewById<EditText>(R.id.etEntrada)
        val tvSalida = findViewById<TextView>(R.id.tvSalida)
        val btnAccion = findViewById<Button>(R.id.btnAccion)

        btnAccion.setOnClickListener {
            // TODO: lógica del botón
            tvSalida.text = etEntrada.text.toString()
        }
    }
}

package com.example.ejemplotodo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * VISTAS CLÁSICAS CON XML (Kotlin_Prueba1 / CicloDeVida-Activity2)
 *
 *  - La interfaz está en  res/layout/activity_vistas_xml.xml  (ConstraintLayout)
 *  - setContentView(R.layout.xxx)  carga el XML
 *  - findViewById<Tipo>(R.id.xxx)  obtiene cada vista por su id (android:id="@+id/xxx")
 *  - setOnClickListener { ... }    para los clicks
 *
 *  Alternativa: ViewBinding (activado en build.gradle.kts):
 *      val b = ActivityVistasXmlBinding.inflate(layoutInflater)
 *      setContentView(b.root);  b.btnSaludar.setOnClickListener { ... }
 */
class VistasXmlActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vistas_xml)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val tvSaludo = findViewById<TextView>(R.id.tvSaludo)
        val btnSaludar = findViewById<Button>(R.id.btnSaludar)

        btnSaludar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            if (nombre.isEmpty()) {
                etNombre.error = "Escribe algo"
            } else {
                tvSaludo.text = getString(R.string.saludo, nombre)  // string con parámetro %1$s
                Toast.makeText(this, "Saludado $nombre", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

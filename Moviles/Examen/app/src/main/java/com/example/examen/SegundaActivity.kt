package com.example.examen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.examen.ui.theme.ExamenTheme

/** Plantilla de segunda pantalla en Compose: recibe extras y devuelve resultado. */
class SegundaActivity : ComponentActivity() {

    companion object {
        const val EXTRA_DATO = "dato"
        const val EXTRA_RESPUESTA = "respuesta"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dato = intent.getStringExtra(EXTRA_DATO) ?: ""

        setContent {
            ExamenTheme {
                var texto by remember { mutableStateOf("") }
                Column(
                    Modifier.fillMaxSize().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically)
                ) {
                    Text("Recibido: $dato")
                    // TODO: contenido de la segunda pantalla
                    OutlinedTextField(texto, { texto = it }, label = { Text("Respuesta") })
                    Button(onClick = {
                        setResult(Activity.RESULT_OK, Intent().putExtra(EXTRA_RESPUESTA, texto))
                        finish()
                    }) { Text("Volver") }
                }
            }
        }
    }
}

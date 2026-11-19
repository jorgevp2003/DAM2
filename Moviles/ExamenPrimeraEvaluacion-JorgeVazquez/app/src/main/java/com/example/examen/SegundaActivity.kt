package com.example.examen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
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
import java.security.KeyStore.TrustedCertificateEntry

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
                var nota1 by remember { mutableStateOf("") }
                var nota2 by remember { mutableStateOf("") }
                var nota3 by remember { mutableStateOf("") }
                var media by remember { mutableStateOf(0.0)}
                var mediaTotal by remember { mutableStateOf(false)}
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically)
                ) {
                    OutlinedTextField(value = nota1,
                                      onValueChange = {nota1 = it},
                                     label = {Text(text = "Introduce la nota del trabajo 1")})
                    OutlinedTextField(value = nota2,
                        onValueChange = {nota2 = it},
                        label = {Text(text = "Introduce la nota del trabajo 2")})
                    OutlinedTextField(value = nota3,
                        onValueChange = {nota3 = it},
                        label = {Text(text = "Introduce la nota del exmamen")})

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(checked = mediaTotal, onCheckedChange = { mediaTotal = it })
                        Text("Hacer la media contando tambien el examen")
                    }
                    Row(){

                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Button(onClick = {
                            if (mediaTotal == true) {
                                var notas = nota1.toDouble() + nota2.toDouble() + nota3.toDouble()
                                media = notas.toDouble() / 3
                            } else {
                                var notas = nota1.toDouble() + nota2.toDouble()
                                media = notas / 2
                            }
                        }) {
                            Text(text = "Pulsa para calcular")
                        }

                        Button(onClick = {
                            nota1 = ""
                            nota2 = ""
                            nota3 = ""
                            media = 0.0
                        }) { Text("Borrar") }
                    }
                    
                    Text(text = "La nota media es : "+media)
                }
            }
        }
    }
}

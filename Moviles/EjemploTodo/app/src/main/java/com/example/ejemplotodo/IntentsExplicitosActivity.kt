package com.example.ejemplotodo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ejemplotodo.ui.theme.EjemploTodoTheme

/**
 * INTENTS EXPLÍCITOS (sabemos a qué clase vamos)
 *
 *  IDA:     val intent = Intent(this, DestinoActivity::class.java)
 *           intent.putExtra("nombre", "Jorge")
 *           startActivity(intent)
 *
 *  LLEGADA: intent.getStringExtra("nombre")   intent.getIntExtra("numero", 0)
 *
 *  VUELTA CON RESULTADO:
 *           val lanzador = registerForActivityResult(StartActivityForResult()) { r -> ... }
 *           lanzador.launch(intent)
 *           (en el destino) setResult(Activity.RESULT_OK, Intent().putExtra(...)); finish()
 *
 *  ¡¡Toda Activity nueva hay que declararla en el AndroidManifest.xml!!
 */
class IntentsExplicitosActivity : ComponentActivity() {

    private var respuesta by mutableStateOf("(sin respuesta todavía)")

    // Debe declararse como propiedad (antes de onCreate / onStart)
    private val lanzadorConResultado =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resultado ->
            if (resultado.resultCode == Activity.RESULT_OK) {
                respuesta = resultado.data?.getStringExtra(DestinoActivity.EXTRA_RESPUESTA) ?: "vacío"
            } else {
                respuesta = "Cancelado (volvió con atrás)"
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EjemploTodoTheme {
                var nombre by remember { mutableStateOf("Jorge") }
                Column(
                    Modifier.fillMaxSize().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically)
                ) {
                    Text("Intents explícitos", style = MaterialTheme.typography.headlineSmall)
                    OutlinedTextField(nombre, { nombre = it }, label = { Text("Nombre a enviar") })

                    Button(onClick = {
                        val intent = Intent(this@IntentsExplicitosActivity, DestinoActivity::class.java)
                        intent.putExtra(DestinoActivity.EXTRA_NOMBRE, nombre)
                        intent.putExtra(DestinoActivity.EXTRA_NUMERO, 1)
                        startActivity(intent)
                    }) { Text("Ir (solo ida)") }

                    Button(onClick = {
                        val intent = Intent(this@IntentsExplicitosActivity, DestinoActivity::class.java).apply {
                            putExtra(DestinoActivity.EXTRA_NOMBRE, nombre)
                            putExtra(DestinoActivity.EXTRA_NUMERO, 2)
                        }
                        lanzadorConResultado.launch(intent)
                    }) { Text("Ir y esperar respuesta") }

                    Text("Respuesta: $respuesta")
                }
            }
        }
    }
}

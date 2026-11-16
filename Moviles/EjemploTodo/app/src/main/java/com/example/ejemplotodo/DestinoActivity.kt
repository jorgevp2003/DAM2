package com.example.ejemplotodo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ejemplotodo.ui.theme.EjemploTodoTheme

/** Segunda pantalla: recibe los extras y puede devolver un resultado. */
class DestinoActivity : ComponentActivity() {

    // Claves de los extras en un sitio -> evita errores al escribirlas
    companion object {
        const val EXTRA_NOMBRE = "nombre"
        const val EXTRA_NUMERO = "numero"
        const val EXTRA_RESPUESTA = "respuesta"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // RECOGER LOS DATOS que nos han mandado
        val nombre = intent.getStringExtra(EXTRA_NOMBRE) ?: "desconocido"
        val numero = intent.getIntExtra(EXTRA_NUMERO, 0)

        setContent {
            EjemploTodoTheme {
                var respuesta by remember { mutableStateOf("¡Hola desde la segunda pantalla!") }
                Column(
                    Modifier.fillMaxSize().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically)
                ) {
                    Image(
                        painter = painterResource(R.drawable.logociudadescolar2),
                        contentDescription = "Logo 2",
                        modifier = Modifier.size(150.dp)
                    )
                    Text("Recibido -> nombre: $nombre, numero: $numero")
                    OutlinedTextField(respuesta, { respuesta = it }, label = { Text("Respuesta") })
                    Button(onClick = {
                        // DEVOLVER UN RESULTADO a quien nos abrió (si usó registerForActivityResult)
                        setResult(Activity.RESULT_OK, Intent().putExtra(EXTRA_RESPUESTA, respuesta))
                        finish() // cierra esta Activity y vuelve a la anterior
                    }) { Text("Devolver y volver") }
                }
            }
        }
    }
}

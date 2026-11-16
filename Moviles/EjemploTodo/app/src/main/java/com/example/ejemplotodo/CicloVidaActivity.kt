package com.example.ejemplotodo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ejemplotodo.ui.theme.EjemploTodoTheme

/**
 * CICLO DE VIDA DE UNA ACTIVITY
 *
 *  onCreate  -> se crea (1 vez). Aquí setContent / setContentView.
 *  onStart   -> se hace visible.
 *  onResume  -> está en primer plano, el usuario interactúa.
 *  onPause   -> otra cosa la tapa parcialmente / va a salir.
 *  onStop    -> ya no se ve (se ha ido a otra Activity o al Home).
 *  onRestart -> vuelve desde onStop (después llama a onStart).
 *  onDestroy -> se destruye (finish(), botón atrás, o GIRAR LA PANTALLA).
 *
 *  Ver en Logcat filtrando por la etiqueta "CICLO".
 *  Prueba: girar el móvil -> onPause, onStop, onDestroy, onCreate, onStart, onResume
 */
class CicloVidaActivity : ComponentActivity() {

    private var i = 0
    // Lista observable por Compose: cada vez que se añade algo, la pantalla se repinta
    private val eventos = mutableStateListOf<String>()

    private fun registrar(metodo: String) {
        val texto = "$metodo: ${++i}"
        Log.d("CICLO", texto)
        eventos.add(texto)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registrar("onCreate")
        setContent {
            EjemploTodoTheme {
                Column(Modifier.fillMaxSize().padding(16.dp)) {
                    Text("Ciclo de vida", style = MaterialTheme.typography.headlineSmall)
                    Text("Sal al Home y vuelve, o gira la pantalla. También en Logcat (tag CICLO).")
                    LazyColumn {
                        items(eventos) { Text("• $it") }
                    }
                }
            }
        }
    }

    override fun onStart() { super.onStart(); registrar("onStart") }
    override fun onResume() { super.onResume(); registrar("onResume") }
    override fun onPause() { super.onPause(); registrar("onPause") }
    override fun onStop() { super.onStop(); registrar("onStop") }
    override fun onRestart() { super.onRestart(); registrar("onRestart") }
    override fun onDestroy() { super.onDestroy(); Log.d("CICLO", "onDestroy: ${++i}") }
}

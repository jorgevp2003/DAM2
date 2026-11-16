package com.example.ejemplotodo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ejemplotodo.ui.theme.EjemploTodoTheme

/**
 * PANTALLA PRINCIPAL (índice).
 * Cada tarjeta abre una Activity con un tema distinto de la asignatura.
 * Así se repasa a la vez: Compose (Scaffold, LazyColumn, Card) + Intents explícitos.
 */
data class Tema(val titulo: String, val descripcion: String, val destino: Class<out Activity>)

val temas = listOf(
    Tema("Ciclo de vida", "onCreate, onStart, onResume, onPause, onStop, onDestroy (Logcat)", CicloVidaActivity::class.java),
    Tema("Layouts Compose", "Column, Row, Box, weight, Arrangement, Alignment, Modifier", LayoutsActivity::class.java),
    Tema("Estado y Calculadora", "remember, mutableStateOf, TextField, Button, Image", EstadoActivity::class.java),
    Tema("Intents explícitos", "Ir a otra Activity, putExtra / getExtra, devolver resultado", IntentsExplicitosActivity::class.java),
    Tema("Intents implícitos y permisos", "Alarma, llamada (permiso), marcar, web, compartir, mapa", IntentsImplicitosActivity::class.java),
    Tema("Vistas XML (clásico)", "AppCompatActivity + layout XML + ConstraintLayout + findViewById", VistasXmlActivity::class.java),
    Tema("ListView simple", "ArrayAdapter + simple_list_item_1 + click en elemento", ListViewSimpleActivity::class.java),
    Tema("ListView personalizado", "ArrayAdapter propio + getView + data class", ListViewPersonalizadoActivity::class.java),
    Tema("GridView personalizado", "GridView + adaptador propio (celdas)", GridViewActivity::class.java),
    Tema("Menú de opciones", "Toolbar + onCreateOptionsMenu / onOptionsItemSelected + submenú + XML", MenuOpcionesActivity::class.java),
    Tema("Menú contextual", "registerForContextMenu + onCreateContextMenu / onContextItemSelected", MenuContextualActivity::class.java),
    Tema("Listas, menús y diálogos en Compose", "LazyColumn, LazyVerticalGrid, TopAppBar, DropdownMenu, AlertDialog, Toast, Snackbar", ComposeAvanzadoActivity::class.java),
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EjemploTodoTheme {
                PantallaIndice()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaIndice() {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ejemplo TODO - Móviles") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 12.dp)
        ) {
            itemsIndexed(temas) { i, tema ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable {
                            // INTENT EXPLÍCITO: de esta pantalla a la clase destino
                            context.startActivity(Intent(context, tema.destino))
                        }
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text("${i + 1}. ${tema.titulo}", fontWeight = FontWeight.Bold)
                        Text(tema.descripcion, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaIndicePreview() {
    EjemploTodoTheme { PantallaIndice() }
}

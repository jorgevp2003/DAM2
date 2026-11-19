package com.example.ejemplotodo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ejemplotodo.ui.theme.EjemploTodoTheme

/**
 * PANTALLA PRINCIPAL hecha con BOX (sin Scaffold, sin lista y sin funciones auxiliares).
 * - 12 Box escritos a mano, cada uno con su .clickable que abre su Activity (INTENT EXPLÍCITO).
 * - Los dos primeros van arriba en FILA (Row) con weight(1f) -> mitad y mitad.
 * - El resto, uno debajo de otro en un Column con verticalScroll.
 */
class MainBoxActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EjemploTodoTheme {
                PantallaIndiceBox()
            }
        }
    }
}

@Composable
fun PantallaIndiceBox() {
    val context = LocalContext.current

    // Box raíz: ocupa toda la pantalla y respeta barra de estado / navegación
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .safeDrawingPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // ---- BARRA SUPERIOR (Box sin click) ----
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    "Ejemplo TODO - Móviles (Box)",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            // ---- FILA con los dos primeros Box ----
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                // ---- BOX 1: Ciclo de vida ----
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(110.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                        .clickable { context.startActivity(Intent(context, CicloVidaActivity::class.java)) }
                        .padding(16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Column {
                        Text("1. Ciclo de vida", fontWeight = FontWeight.Bold)
                        Text("onCreate, onStart, onResume, onPause, onStop, onDestroy (Logcat)", style = MaterialTheme.typography.bodySmall)
                    }
                }
                // ---- BOX 2: Layouts Compose ----
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(110.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                        .clickable { context.startActivity(Intent(context, LayoutsActivity::class.java)) }
                        .padding(16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Column {
                        Text("2. Layouts Compose", fontWeight = FontWeight.Bold)
                        Text("Column, Row, Box, weight, Arrangement, Alignment, Modifier", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }

            // ---- BOX 3: Estado y Calculadora ----
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .clickable { context.startActivity(Intent(context, EstadoActivity::class.java)) }
                    .padding(16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Column {
                    Text("3. Estado y Calculadora", fontWeight = FontWeight.Bold)
                    Text("remember, mutableStateOf, TextField, Button, Image", style = MaterialTheme.typography.bodySmall)
                }
            }

            // ---- BOX 4: Intents explícitos ----
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .clickable { context.startActivity(Intent(context, IntentsExplicitosActivity::class.java)) }
                    .padding(16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Column {
                    Text("4. Intents explícitos", fontWeight = FontWeight.Bold)
                    Text("Ir a otra Activity, putExtra / getExtra, devolver resultado", style = MaterialTheme.typography.bodySmall)
                }
            }

            // ---- BOX 5: Intents implícitos y permisos ----
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .clickable { context.startActivity(Intent(context, IntentsImplicitosActivity::class.java)) }
                    .padding(16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Column {
                    Text("5. Intents implícitos y permisos", fontWeight = FontWeight.Bold)
                    Text("Alarma, llamada (permiso), marcar, web, compartir, mapa", style = MaterialTheme.typography.bodySmall)
                }
            }

            // ---- BOX 6: Vistas XML (clásico) ----
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .clickable { context.startActivity(Intent(context, VistasXmlActivity::class.java)) }
                    .padding(16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Column {
                    Text("6. Vistas XML (clásico)", fontWeight = FontWeight.Bold)
                    Text("AppCompatActivity + layout XML + ConstraintLayout + findViewById", style = MaterialTheme.typography.bodySmall)
                }
            }

            // ---- BOX 7: ListView simple ----
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .clickable { context.startActivity(Intent(context, ListViewSimpleActivity::class.java)) }
                    .padding(16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Column {
                    Text("7. ListView simple", fontWeight = FontWeight.Bold)
                    Text("ArrayAdapter + simple_list_item_1 + click en elemento", style = MaterialTheme.typography.bodySmall)
                }
            }

            // ---- BOX 8: ListView personalizado ----
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .clickable { context.startActivity(Intent(context, ListViewPersonalizadoActivity::class.java)) }
                    .padding(16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Column {
                    Text("8. ListView personalizado", fontWeight = FontWeight.Bold)
                    Text("ArrayAdapter propio + getView + data class", style = MaterialTheme.typography.bodySmall)
                }
            }

            // ---- BOX 9: GridView personalizado ----
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .clickable { context.startActivity(Intent(context, GridViewActivity::class.java)) }
                    .padding(16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Column {
                    Text("9. GridView personalizado", fontWeight = FontWeight.Bold)
                    Text("GridView + adaptador propio (celdas)", style = MaterialTheme.typography.bodySmall)
                }
            }

            // ---- BOX 10: Menú de opciones ----
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .clickable { context.startActivity(Intent(context, MenuOpcionesActivity::class.java)) }
                    .padding(16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Column {
                    Text("10. Menú de opciones", fontWeight = FontWeight.Bold)
                    Text("Toolbar + onCreateOptionsMenu / onOptionsItemSelected + submenú + XML", style = MaterialTheme.typography.bodySmall)
                }
            }

            // ---- BOX 11: Menú contextual ----
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .clickable { context.startActivity(Intent(context, MenuContextualActivity::class.java)) }
                    .padding(16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Column {
                    Text("11. Menú contextual", fontWeight = FontWeight.Bold)
                    Text("registerForContextMenu + onCreateContextMenu / onContextItemSelected", style = MaterialTheme.typography.bodySmall)
                }
            }

            // ---- BOX 12: Listas, menús y diálogos en Compose ----
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .clickable { context.startActivity(Intent(context, ComposeAvanzadoActivity::class.java)) }
                    .padding(16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Column {
                    Text("12. Listas, menús y diálogos en Compose", fontWeight = FontWeight.Bold)
                    Text("LazyColumn, LazyVerticalGrid, TopAppBar, DropdownMenu, AlertDialog, Toast, Snackbar", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaIndiceBoxPreview() {
    EjemploTodoTheme { PantallaIndiceBox() }
}

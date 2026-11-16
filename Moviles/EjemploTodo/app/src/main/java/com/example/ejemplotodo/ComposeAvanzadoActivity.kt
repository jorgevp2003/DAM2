package com.example.ejemplotodo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ejemplotodo.ui.theme.EjemploTodoTheme
import kotlinx.coroutines.launch

/**
 * LO MISMO QUE LISTVIEW / GRIDVIEW / MENÚS PERO EN COMPOSE
 *
 *  ListView      ->  LazyColumn { items(lista) { ... } }
 *  GridView      ->  LazyVerticalGrid(GridCells.Fixed(2) / GridCells.Adaptive(150.dp))
 *  Menú opciones ->  TopAppBar(actions = { IconButton + DropdownMenu })
 *  Menú contextual -> Modifier.combinedClickable(onLongClick = ...) + DropdownMenu
 *  Toast         ->  Toast.makeText(context, "...", Toast.LENGTH_SHORT).show()
 *  Snackbar      ->  Scaffold(snackbarHost) + scope.launch { snackbarHostState.showSnackbar("...") }
 *  Diálogo       ->  if (mostrar) AlertDialog(onDismissRequest, confirmButton, dismissButton, title, text)
 */
class ComposeAvanzadoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EjemploTodoTheme { PantallaComposeAvanzado() }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaComposeAvanzado() {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Lista observable: al añadir/borrar, la UI se actualiza sola (no hace falta notifyDataSetChanged)
    val elementos = remember { ejemplosDePrueba().toMutableStateList() }
    var pestana by remember { mutableIntStateOf(0) }
    var menuAbierto by remember { mutableStateOf(false) }
    var aBorrar by remember { mutableStateOf<Ejemplo?>(null) }  // != null -> mostrar diálogo

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Compose: listas y menús") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                actions = {
                    // MENÚ DE OPCIONES (3 puntos)
                    IconButton(onClick = { menuAbierto = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Menú")
                    }
                    DropdownMenu(expanded = menuAbierto, onDismissRequest = { menuAbierto = false }) {
                        DropdownMenuItem(text = { Text("Ordenar A-Z") }, onClick = {
                            elementos.sortBy { it.titulo }; menuAbierto = false
                        })
                        DropdownMenuItem(text = { Text("Ordenar Z-A") }, onClick = {
                            elementos.sortByDescending { it.titulo }; menuAbierto = false
                        })
                        DropdownMenuItem(text = { Text("Toast") }, onClick = {
                            Toast.makeText(context, "Hay ${elementos.size} elementos", Toast.LENGTH_SHORT).show()
                            menuAbierto = false
                        })
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val n = (elementos.maxOfOrNull { it.numeroEjemplo } ?: 0) + 1
                elementos.add(Ejemplo("Título Ejemplo $n", "Añadido con el FAB", "", n))
                scope.launch { snackbarHostState.showSnackbar("Añadido el ejemplo $n") }
            }) { Icon(Icons.Default.Add, contentDescription = "Añadir") }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(Modifier.padding(padding).fillMaxSize()) {
            TabRow(selectedTabIndex = pestana) {
                Tab(selected = pestana == 0, onClick = { pestana = 0 }, text = { Text("LazyColumn") })
                Tab(selected = pestana == 1, onClick = { pestana = 1 }, text = { Text("LazyVerticalGrid") })
            }
            if (pestana == 0) {
                LazyColumn(Modifier.fillMaxSize()) {
                    items(elementos) { e ->
                        FilaEjemplo(
                            e,
                            onClick = { Toast.makeText(context, e.titulo, Toast.LENGTH_SHORT).show() },
                            onBorrar = { aBorrar = e }
                        )
                    }
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(150.dp),
                    modifier = Modifier.fillMaxSize().padding(8.dp)
                ) {
                    items(elementos) { e -> CeldaEjemplo(e) }
                }
            }
        }
    }

    // DIÁLOGO DE CONFIRMACIÓN
    aBorrar?.let { e ->
        AlertDialog(
            onDismissRequest = { aBorrar = null },
            title = { Text("Borrar") },
            text = { Text("¿Seguro que quieres borrar \"${e.titulo}\"?") },
            confirmButton = {
                TextButton(onClick = { elementos.remove(e); aBorrar = null }) { Text("Sí") }
            },
            dismissButton = {
                TextButton(onClick = { aBorrar = null }) { Text("No") }
            }
        )
    }
}

/** Fila con "menú contextual" al mantener pulsado */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FilaEjemplo(e: Ejemplo, onClick: () -> Unit, onBorrar: () -> Unit) {
    var menuContextual by remember { mutableStateOf(false) }
    Row(
        Modifier
            .fillMaxWidth()
            .combinedClickable(onClick = onClick, onLongClick = { menuContextual = true })
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painterResource(R.drawable.logociudadescolar), contentDescription = null, Modifier.size(56.dp))
        Column(Modifier.padding(start = 12.dp)) {
            Text(e.titulo, fontWeight = FontWeight.Bold)
            Text(e.subtitulo)
            Text(e.numeroEjemplo.toString(), style = MaterialTheme.typography.bodySmall)
        }
        DropdownMenu(expanded = menuContextual, onDismissRequest = { menuContextual = false }) {
            DropdownMenuItem(text = { Text("Borrar") }, onClick = { menuContextual = false; onBorrar() })
        }
    }
}

@Composable
fun CeldaEjemplo(e: Ejemplo) {
    Card(Modifier.padding(4.dp)) {
        Column(Modifier.fillMaxWidth().padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painterResource(R.drawable.logociudadescolar), contentDescription = null, Modifier.size(80.dp))
            Text(e.titulo, fontWeight = FontWeight.Bold)
            Text(e.subtitulo, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaComposeAvanzadoPreview() {
    EjemploTodoTheme { PantallaComposeAvanzado() }
}

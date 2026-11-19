package com.example.examen


import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.examen.ui.theme.ExamenTheme


class MainActivity : ComponentActivity() {

    // Texto que devuelve SegundaActivity (estado observable por Compose)
    private var resultado by mutableStateOf("")

    // ---------- Recibir resultado de otra Activity ----------
    private val lanzadorResultado =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { r ->
            if (r.resultCode == RESULT_OK) {
                resultado = r.data?.getStringExtra(SegundaActivity.EXTRA_RESPUESTA) ?: ""
            }
        }

    // ---------- Pedir permiso peligroso (CALL_PHONE, CAMERA...) ----------


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate MainActivity")
        setContent {
            ExamenTheme {
                PantallaPrincipal()
            }
        }
    }




}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaPrincipal() {
    var menuAbierto by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Examen") },   // TODO: título
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                actions = {
                    IconButton(onClick = { menuAbierto = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Menú")
                    }
                    DropdownMenu(
                        expanded = menuAbierto,
                        onDismissRequest = { menuAbierto = false }) {

                        DropdownMenuItem(
                            text = { Text("1. Calcular nota") },
                            onClick = {
                                context.startActivity(
                                    Intent(
                                        context,
                                        SegundaActivity::class.java
                                    )
                                )
                            })
                        BotonAncho("Llamar (pide permiso)") {
                           /* val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
                                putExtra(AlarmClock.EXTRA_MESSAGE, "Hola")
                                putExtra(AlarmClock.EXTRA_HOUR, 11)
                                putExtra(AlarmClock.EXTRA_MINUTES, 20)
                            }
                            lanzar(intent)*/
                        }
                        DropdownMenuItem(
                            text = { Text("Menu") },
                            onClick = {
                                context.startActivity(
                                    Intent(
                                        context,
                                        Tercera::class.java
                                    )
                                )
                            })
                        DropdownMenuItem(text = { Text("4. Acerca de ") }, onClick = {

                        })
                    }
                }
            )
        }
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(text = "Bienvenido a mi examen de Moviles")
        }
    }



}

@Preview(showBackground = true)
@Composable
fun PantallaPrincipalPreview() {
    ExamenTheme { PantallaPrincipal() }
}
@Composable
fun BotonAncho(texto: String, onClick: () -> Unit) {
    Button(onClick = onClick, modifier = Modifier.fillMaxWidth()) { Text(texto) }
}
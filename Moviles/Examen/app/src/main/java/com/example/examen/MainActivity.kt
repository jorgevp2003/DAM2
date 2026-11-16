package com.example.examen

import android.Manifest
import android.app.Activity
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.examen.ui.theme.ExamenTheme

/**
 * =====================================================================
 *  EXAMEN - PANTALLA PRINCIPAL (Compose)
 * ---------------------------------------------------------------------
 *  Ya preparado:
 *   - Scaffold + TopAppBar + menú de 3 puntos (DropdownMenu)
 *   - Botones que abren las otras pantallas (Intents explícitos)
 *   - Lanzador para recibir resultado de SegundaActivity
 *   - Lanzador para pedir un permiso peligroso
 *  Busca los  TODO  para saber dónde escribir.
 * =====================================================================
 */
class MainActivity : ComponentActivity() {

    // Texto que devuelve SegundaActivity (estado observable por Compose)
    private var resultado by mutableStateOf("")

    // ---------- Recibir resultado de otra Activity ----------
    private val lanzadorResultado =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { r ->
            if (r.resultCode == Activity.RESULT_OK) {
                resultado = r.data?.getStringExtra(SegundaActivity.EXTRA_RESPUESTA) ?: ""
            }
        }

    // ---------- Pedir permiso peligroso (CALL_PHONE, CAMERA...) ----------
    private val lanzadorPermiso =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { concedido ->
            if (concedido) accionConPermiso() else toast("Permiso denegado")
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate MainActivity")
        setContent {
            ExamenTheme {
                PantallaPrincipal(
                    resultado = resultado,
                    irASegunda = {
                        val intent = Intent(this, SegundaActivity::class.java)
                        intent.putExtra(SegundaActivity.EXTRA_DATO, "Dato desde Main")
                        lanzadorResultado.launch(intent)       // o startActivity(intent) si no esperas respuesta
                    },
                    irAXml = { startActivity(Intent(this, XmlActivity::class.java)) },
                    irAClasica = { startActivity(Intent(this, ListaActivity::class.java)) },
                    pedirPermiso = { comprobarPermiso(Manifest.permission.CALL_PHONE) }
                )
            }
        }
    }

    private fun comprobarPermiso(permiso: String) {
        if (ContextCompat.checkSelfPermission(this, permiso) == PackageManager.PERMISSION_GRANTED) {
            accionConPermiso()
        } else {
            lanzadorPermiso.launch(permiso)
        }
    }

    private fun accionConPermiso() {
        // TODO: lo que haya que hacer cuando tengamos el permiso
        //  ej: startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:600000000")))
        toast("Permiso concedido ✔")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaPrincipal(
    resultado: String,
    irASegunda: () -> Unit,
    irAXml: () -> Unit,
    irAClasica: () -> Unit,
    pedirPermiso: () -> Unit,
) {
    var menuAbierto by remember { mutableStateOf(false) }

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
                    DropdownMenu(expanded = menuAbierto, onDismissRequest = { menuAbierto = false }) {
                        // TODO: opciones del menú
                        DropdownMenuItem(text = { Text("Opción 1") }, onClick = { menuAbierto = false })
                        DropdownMenuItem(text = { Text("Opción 2") }, onClick = { menuAbierto = false })
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
            // TODO: contenido del ejercicio

            Button(onClick = irASegunda, Modifier.fillMaxWidth()) { Text("Ir a SegundaActivity (Compose)") }
            Button(onClick = irAXml, Modifier.fillMaxWidth()) { Text("Ir a XmlActivity (layout XML)") }
            Button(onClick = irAClasica, Modifier.fillMaxWidth()) { Text("Ir a ListaActivity (ListView + menús)") }
            Button(onClick = pedirPermiso, Modifier.fillMaxWidth()) { Text("Pedir permiso") }

            if (resultado.isNotEmpty()) Text("Resultado recibido: $resultado")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaPrincipalPreview() {
    ExamenTheme { PantallaPrincipal("", {}, {}, {}, {}) }
}

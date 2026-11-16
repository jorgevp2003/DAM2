package com.example.ejemplotodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ejemplotodo.ui.theme.EjemploTodoTheme

/**
 * ESTADO EN COMPOSE
 *
 *  var x by remember { mutableStateOf(valor) }      -> se mantiene entre recomposiciones
 *  var x by rememberSaveable { mutableStateOf(v) }  -> además sobrevive a GIRAR la pantalla
 *  (hacen falta los imports getValue y setValue para usar "by")
 *
 *  Cuando cambia el estado -> Compose vuelve a ejecutar (recompone) las funciones que lo leen.
 */
class EstadoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EjemploTodoTheme {
                Column(
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Contador()
                    HorizontalDivider(Modifier.padding(vertical = 12.dp))
                    Formulario()
                    HorizontalDivider(Modifier.padding(vertical = 12.dp))
                    MiCalculadora()
                }
            }
        }
    }
}

@Composable
fun Contador() {
    var contador by rememberSaveable { mutableIntStateOf(0) }
    Text("Contador: $contador", fontSize = 24.sp)
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Button(onClick = { contador-- }) { Text("-1") }
        Button(onClick = { contador++ }) { Text("+1") }
        Button(onClick = { contador = 0 }) { Text("Reset") }
    }
}

@Composable
fun Formulario() {
    var nombre by remember { mutableStateOf("") }
    var acepto by remember { mutableStateOf(false) }
    var modoOscuro by remember { mutableStateOf(false) }

    // Imagen de res/drawable (2Activity)
    Image(
        painter = painterResource(id = R.drawable.logociudadescolar),
        contentDescription = "Logo",
        modifier = Modifier.size(100.dp)
    )
    OutlinedTextField(
        value = nombre,
        onValueChange = { nombre = it },   // it = texto nuevo
        label = { Text("Nombre") },
        singleLine = true
    )
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = acepto, onCheckedChange = { acepto = it })
        Text("Acepto las condiciones")
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Switch(checked = modoOscuro, onCheckedChange = { modoOscuro = it })
        Text("  Switch: $modoOscuro")
    }
    Text(if (nombre.isBlank()) "Escribe tu nombre" else "Hola $nombre (acepta: $acepto)")
}

/* ===================== CALCULADORA (KotlinConJetpack) ===================== */
@Composable
fun MiCalculadora() {
    var textoPantalla by remember { mutableStateOf("") }

    Text("Calculadora", style = MaterialTheme.typography.titleLarge)
    Box(
        Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(horizontal = 24.dp)
            .border(2.dp, Color.Black, RoundedCornerShape(10.dp))
            .background(Color.LightGray, RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(textoPantalla, fontSize = 30.sp)
    }
    Spacer(Modifier.height(8.dp))

    val filas = listOf(
        listOf("1", "2", "3", "+"),
        listOf("4", "5", "6", "-"),
        listOf("7", "8", "9", "*"),
        listOf("C", "0", "=", "/"),
    )
    for (fila in filas) {
        Row {
            for (tecla in fila) {
                Boton(tecla) {
                    textoPantalla = when (tecla) {
                        "C" -> ""
                        "=" -> calcular(textoPantalla)
                        else -> textoPantalla + tecla
                    }
                }
            }
        }
    }
}

/** Lógica separada de la interfaz -> más fácil de probar */
fun calcular(expresion: String): String {
    val operador = listOf("+", "-", "*", "/").firstOrNull { expresion.drop(1).contains(it) }
        ?: return expresion
    val idx = expresion.indexOf(operador, startIndex = 1)
    val a = expresion.substring(0, idx).toDoubleOrNull() ?: return "Error"
    val b = expresion.substring(idx + 1).toDoubleOrNull() ?: return "Error"
    val resultado = when (operador) {
        "+" -> a + b
        "-" -> a - b
        "*" -> a * b
        else -> if (b == 0.0) return "Error: división por 0" else a / b
    }
    return "%.2f".format(resultado)
}

@Composable
fun Boton(texto: String, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        modifier = Modifier
            .padding(2.dp)
            .size(64.dp)
            .background(Color.LightGray, RoundedCornerShape(10.dp))
            .border(2.dp, Color.Black, RoundedCornerShape(10.dp))
    ) {
        Text(texto, fontSize = 20.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun CalculadoraPreview() {
    EjemploTodoTheme { Column { MiCalculadora() } }
}

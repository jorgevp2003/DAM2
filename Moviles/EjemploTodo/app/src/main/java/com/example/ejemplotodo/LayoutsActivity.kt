package com.example.ejemplotodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ejemplotodo.ui.theme.EjemploTodoTheme

/**
 * LAYOUTS EN COMPOSE
 *
 *  Column -> hijos de ARRIBA a ABAJO.   verticalArrangement + horizontalAlignment
 *  Row    -> hijos de IZQUIERDA a DERECHA. horizontalArrangement + verticalAlignment
 *  Box    -> hijos UNO ENCIMA DE OTRO.  contentAlignment / Modifier.align(...)
 *
 *  Arrangement (eje principal): Start, End, Center, SpaceBetween, SpaceAround, SpaceEvenly, spacedBy(8.dp)
 *  Alignment   (eje cruzado):   Top/CenterVertically/Bottom (Row) ; Start/CenterHorizontally/End (Column)
 *  Modifier.weight(1f) -> reparte el espacio sobrante (solo dentro de Row/Column).
 *
 *  ¡EL ORDEN DE LOS MODIFIER IMPORTA!  .padding().background()  !=  .background().padding()
 */
class LayoutsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EjemploTodoTheme {
                Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    PantallaLayouts()
                }
            }
        }
    }
}

@Composable
fun Titulo(texto: String) {
    Text(texto, fontSize = 20.sp, modifier = Modifier.padding(top = 16.dp, bottom = 4.dp))
}

@Composable
fun PantallaLayouts() {
    // verticalScroll -> para que se pueda hacer scroll si no cabe
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(12.dp)
    ) {
        // ---------- 1. Column con weight (Layout: 50% / 25% / 25%) ----------
        Titulo("1. Column + weight (2f / 1f / 1f)")
        Column(Modifier.fillMaxWidth().height(200.dp)) {
            Box(Modifier.fillMaxWidth().weight(2f).background(Color.Gray)) {
                Text("weight 2f (50%)", Modifier.align(Alignment.TopStart))
            }
            Box(Modifier.fillMaxWidth().weight(1f).background(Color.LightGray)) {
                Text("weight 1f (25%)", Modifier.align(Alignment.Center))
            }
            Box(Modifier.fillMaxWidth().weight(1f).background(Color.Gray)) {
                Text("weight 1f (25%)", Modifier.align(Alignment.BottomEnd))
            }
        }

        // ---------- 2. Row con SpaceEvenly (Layout) ----------
        Titulo("2. Row + SpaceEvenly + iconos")
        Row(
            Modifier.fillMaxWidth().height(60.dp).background(Color(0xFFE0F7FA)),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(3) { Icon(Icons.Default.Star, contentDescription = "Estrella", Modifier.size(48.dp)) }
        }

        // ---------- 3. Row: icono + columna de textos ----------
        Titulo("3. Row con Icon + Column de textos")
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Star, contentDescription = null, Modifier.size(48.dp))
            Column(Modifier.padding(12.dp)) {
                Text("Texto 1", fontSize = 18.sp)
                Text("Texto 2", color = Color.Gray)
            }
        }

        // ---------- 4. Left / Center / Right (Layout12) ----------
        Titulo("4. Arrangement Start / Center / End")
        Column(Modifier.fillMaxWidth().height(180.dp).border(1.dp, Color.Black)) {
            Row(Modifier.fillMaxWidth().weight(1f), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                Text("Left", fontSize = 28.sp, modifier = Modifier.background(Color.Green))
            }
            Row(Modifier.fillMaxWidth().weight(1f), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                Text("Center", fontSize = 28.sp, modifier = Modifier.background(Color.Green))
            }
            Row(Modifier.fillMaxWidth().weight(1f), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically) {
                Text("Right", fontSize = 28.sp, modifier = Modifier.background(Color.Green))
            }
        }

        // ---------- 5. Cuadrícula 3x3 con huecos (Layout1) ----------
        Titulo("5. Cuadrícula con Row + weight y huecos (Box vacío)")
        Column(Modifier.fillMaxWidth().height(210.dp)) {
            Row(Modifier.fillMaxWidth().weight(1f)) {
                CeldaEstrella(Color.Green); CeldaEstrella(Color.Green); CeldaEstrella(Color.Green)
            }
            Row(Modifier.fillMaxWidth().weight(1f)) {
                CeldaEstrella(Color.LightGray); Spacer(Modifier.weight(1f)); CeldaEstrella(Color.LightGray)
            }
            Row(Modifier.fillMaxWidth().weight(1f)) {
                Spacer(Modifier.weight(1f)); CeldaEstrella(Color.Green); Spacer(Modifier.weight(1f))
            }
        }

        // ---------- 6. Box: elementos superpuestos ----------
        Titulo("6. Box: superponer y alinear")
        Box(
            Modifier
                .fillMaxWidth()
                .height(140.dp)
                .border(BorderStroke(2.dp, Color.Black), RoundedCornerShape(10.dp))
                .background(Color(0xFFFFF3E0), RoundedCornerShape(10.dp))
        ) {
            Text("TopStart", Modifier.align(Alignment.TopStart).padding(8.dp))
            Text("Center", Modifier.align(Alignment.Center))
            Text("BottomEnd", Modifier.align(Alignment.BottomEnd).padding(8.dp))
        }

        // ---------- 7. Orden de los Modifier ----------
        Titulo("7. El orden de Modifier importa")
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Text("padding→background", Modifier.padding(16.dp).background(Color.Yellow))
            Text("background→padding", Modifier.background(Color.Yellow).padding(16.dp))
        }
    }
}

// Función de extensión de RowScope -> así podemos usar weight() dentro
@Composable
fun RowScope.CeldaEstrella(color: Color) {
    Icon(
        Icons.Default.Star, contentDescription = null,
        Modifier.weight(1f).fillMaxHeight().padding(1.dp).background(color)
    )
}

@Preview(showBackground = true, heightDp = 1200)
@Composable
fun PantallaLayoutsPreview() {
    EjemploTodoTheme { PantallaLayouts() }
}

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ejemplotodo.ui.theme.EjemploTodoTheme

/**
 * SEGUNDO ÍNDICE (sin lista de temas).
 *
 * En MainActivity los temas están en una lista `temas` y un LazyColumn los recorre.
 * Aquí NO hay lista: cada botón es su PROPIO Box, escrito a mano, con su propio
 * Modifier.clickable { startActivity(Intent(...)) }.
 *
 *  Box(
 *      modifier = Modifier.clip(forma).background(color).clickable { ... }.padding(..),
 *      contentAlignment = Alignment.Center
 *  ) { Text("...") }
 *
 * Orden de los Modifier: clip -> background -> clickable -> padding
 * (si el padding va antes del background, el color no llega al borde).
 * Dos Box en un Row con weight(1f) = mitad y mitad.
 */
class MainBoxActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EjemploTodoTheme {
                PantallaBoxes()
            }
        }
    }
}

@Composable
fun PantallaBoxes() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())   // por si no caben todos
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            "Ejemplo TODO - Boxes",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // ---------- FILA 1 ----------
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            // BOX 1: Ciclo de vida
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(110.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFE57373))
                    .clickable {
                        context.startActivity(Intent(context, CicloVidaActivity::class.java))
                    }
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("1. Ciclo de vida", color = Color.White, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            }

            // BOX 2: Layouts
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(110.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFF06292))
                    .clickable {
                        context.startActivity(Intent(context, LayoutsActivity::class.java))
                    }
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("2. Layouts Compose", color = Color.White, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            }
        }

        // ---------- FILA 2 ----------
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            // BOX 3: Estado
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(110.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFBA68C8))
                    .clickable {
                        context.startActivity(Intent(context, EstadoActivity::class.java))
                    }
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("3. Estado y Calculadora", color = Color.White, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            }

            // BOX 4: Intents explícitos
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(110.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF9575CD))
                    .clickable {
                        context.startActivity(Intent(context, IntentsExplicitosActivity::class.java))
                    }
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("4. Intents explícitos", color = Color.White, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            }
        }

        // ---------- FILA 3 ----------
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            // BOX 5: Intents implícitos
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(110.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF7986CB))
                    .clickable {
                        context.startActivity(Intent(context, IntentsImplicitosActivity::class.java))
                    }
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("5. Intents implícitos y permisos", color = Color.White, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            }

            // BOX 6: Vistas XML
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(110.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF64B5F6))
                    .clickable {
                        context.startActivity(Intent(context, VistasXmlActivity::class.java))
                    }
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("6. Vistas XML", color = Color.White, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            }
        }

        // ---------- FILA 4 ----------
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            // BOX 7: ListView simple
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(110.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF4FC3F7))
                    .clickable {
                        context.startActivity(Intent(context, ListViewSimpleActivity::class.java))
                    }
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("7. ListView simple", color = Color.White, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            }

            // BOX 8: ListView personalizado
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(110.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF4DB6AC))
                    .clickable {
                        context.startActivity(Intent(context, ListViewPersonalizadoActivity::class.java))
                    }
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("8. ListView personalizado", color = Color.White, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            }
        }

        // ---------- FILA 5 ----------
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            // BOX 9: GridView
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(110.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF81C784))
                    .clickable {
                        context.startActivity(Intent(context, GridViewActivity::class.java))
                    }
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("9. GridView", color = Color.White, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            }

            // BOX 10: Menú de opciones
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(110.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFAED581))
                    .clickable {
                        context.startActivity(Intent(context, MenuOpcionesActivity::class.java))
                    }
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("10. Menú de opciones", color = Color.White, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            }
        }

        // ---------- FILA 6 ----------
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            // BOX 11: Menú contextual
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(110.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFFFB74D))
                    .clickable {
                        context.startActivity(Intent(context, MenuContextualActivity::class.java))
                    }
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("11. Menú contextual", color = Color.White, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            }

            // BOX 12: Compose avanzado
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(110.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFFF8A65))
                    .clickable {
                        context.startActivity(Intent(context, ComposeAvanzadoActivity::class.java))
                    }
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("12. Listas, menús y diálogos en Compose", color = Color.White, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            }
        }

        // ---------- BOX ANCHO (ocupa toda la fila) ----------
        // BOX 13: volver al índice con lista (MainActivity)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFF616161))
                .clickable {
                    context.startActivity(Intent(context, MainActivity::class.java))
                }
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("Ver el índice con lista (MainActivity)", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaBoxesPreview() {
    EjemploTodoTheme { PantallaBoxes() }
}

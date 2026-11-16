package com.example.ejemplotodo

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.ejemplotodo.ui.theme.EjemploTodoTheme

/**
 * INTENTS IMPLÍCITOS: decimos QUÉ queremos hacer (acción) y Android busca la app.
 *
 *  Alarma   -> Intent(AlarmClock.ACTION_SET_ALARM)  + permiso SET_ALARM (normal, sin preguntar)
 *  Llamar   -> Intent(Intent.ACTION_CALL, Uri.parse("tel:..."))  + permiso CALL_PHONE (PELIGROSO -> pedir)
 *  Marcar   -> Intent(Intent.ACTION_DIAL, Uri.parse("tel:..."))  (NO necesita permiso)
 *  Web      -> Intent(Intent.ACTION_VIEW, Uri.parse("https://..."))
 *  Mapa     -> Intent(Intent.ACTION_VIEW, Uri.parse("geo:40.4,-3.7?q=Madrid"))
 *  Compartir-> Intent(Intent.ACTION_SEND) type="text/plain" + EXTRA_TEXT  -> Intent.createChooser
 *  Email    -> Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:..."))
 *
 * PERMISOS PELIGROSOS EN TIEMPO DE EJECUCIÓN:
 *  1. Declararlo en el Manifest  <uses-permission android:name="android.permission.CALL_PHONE"/>
 *  2. Comprobar: ContextCompat.checkSelfPermission(this, permiso) == PERMISSION_GRANTED
 *  3. Si no: lanzador.launch(permiso) con registerForActivityResult(RequestPermission())
 */
class IntentsImplicitosActivity : ComponentActivity() {

    private val telefono = "+34600000000"

    private val pedirPermisoLlamada =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { concedido ->
            if (concedido) hacerLlamada()
            else Toast.makeText(this, "Permiso de llamada denegado", Toast.LENGTH_SHORT).show()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EjemploTodoTheme {
                Column(
                    Modifier.fillMaxSize().padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Intents implícitos", style = MaterialTheme.typography.headlineSmall)
                    BotonAncho("Crear alarma 11:20") { crearAlarma() }
                    BotonAncho("Llamar (pide permiso)") { crearLlamada() }
                    BotonAncho("Marcar (sin permiso)") { lanzar(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$telefono"))) }
                    BotonAncho("Abrir web") { lanzar(Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com"))) }
                    BotonAncho("Abrir mapa") { lanzar(Intent(Intent.ACTION_VIEW, Uri.parse("geo:40.4168,-3.7038?q=Puerta del Sol"))) }
                    BotonAncho("Enviar email") {
                        lanzar(Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:profe@ejemplo.com")).apply {
                            putExtra(Intent.EXTRA_SUBJECT, "Asunto")
                            putExtra(Intent.EXTRA_TEXT, "Cuerpo del correo")
                        })
                    }
                    BotonAncho("Compartir texto") {
                        val enviar = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_TEXT, "¡Mira qué app más chula!")
                        }
                        startActivity(Intent.createChooser(enviar, "Compartir con..."))
                    }
                    BotonAncho("Abrir otra app (Ajustes)") {
                        lanzar(Intent(android.provider.Settings.ACTION_SETTINGS))
                    }
                }
            }
        }
    }

    private fun crearAlarma() {
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, "Hola")
            putExtra(AlarmClock.EXTRA_HOUR, 11)
            putExtra(AlarmClock.EXTRA_MINUTES, 20)
        }
        lanzar(intent)
    }

    private fun crearLlamada() {
        val tienePermiso = ContextCompat.checkSelfPermission(
            this, Manifest.permission.CALL_PHONE
        ) == PackageManager.PERMISSION_GRANTED

        if (tienePermiso) hacerLlamada()
        else pedirPermisoLlamada.launch(Manifest.permission.CALL_PHONE)
    }

    private fun hacerLlamada() {
        lanzar(Intent(Intent.ACTION_CALL, Uri.parse("tel:$telefono")))
    }

    /** Si no hay ninguna app que lo pueda abrir, startActivity lanza ActivityNotFoundException */
    private fun lanzar(intent: Intent) {
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "No hay ninguna app para esto", Toast.LENGTH_SHORT).show()
        }
    }
}

@androidx.compose.runtime.Composable
fun BotonAncho(texto: String, onClick: () -> Unit) {
    Button(onClick = onClick, modifier = Modifier.fillMaxWidth()) { Text(texto) }
}

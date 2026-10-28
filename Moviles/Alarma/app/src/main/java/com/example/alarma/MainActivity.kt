package com.example.alarma

import android.content.Intent
import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.AlarmClock
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.alarma.ui.theme.AlarmaTheme
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat


class MainActivity : ComponentActivity() {
    private val pedirPermisoLlamada =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { concedido ->
            if (concedido) {
                hacerLlamada()
            } else {
                Toast.makeText(this, "Permiso de llamada denegado", Toast.LENGTH_SHORT).show()
            }
        }
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
       // val alarma = packageManager.getLaunchIntentForPackage("com.google.android.deskclock")
        super.onCreate(savedInstanceState)
        setContent {
            AlarmaTheme {
                // A surface container using the 'background' color from the theme
                Surface(

                    color = MaterialTheme.colorScheme.background
                ) {

                    Button(modifier = Modifier.padding(start = 100.dp),

                    onClick = {
                        crearAlarma()

                    }) {
                        Text("Ir a alarma")
                    }

                    Button(onClick = {crearLlamada() })


                    {
                        Text("Llamar")
                    }
                }
            }
        }
    }


    fun crearAlarma(){
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply{
            putExtra(AlarmClock.EXTRA_MESSAGE,"Hola")
            putExtra(AlarmClock.EXTRA_HOUR,11)
            putExtra(AlarmClock.EXTRA_MINUTES,20)
        }
        startActivity(intent)
    }


    private fun crearLlamada() {
        val tienePermiso = ContextCompat.checkSelfPermission(
            this, Manifest.permission.CALL_PHONE
        ) == PackageManager.PERMISSION_GRANTED

        if (tienePermiso) {
            hacerLlamada()
        } else {
            pedirPermisoLlamada.launch(Manifest.permission.CALL_PHONE)
        }
    }


    fun hacerLlamada(){
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:+34674274634"))
        startActivity(intent)
    }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {

    }

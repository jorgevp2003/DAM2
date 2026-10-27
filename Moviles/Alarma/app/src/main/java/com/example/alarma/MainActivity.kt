package com.example.alarma

import android.content.Intent
import android.net.Uri
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
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
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

                    Button(onClick = {
                        crearLlamada()

                    }) {
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

    fun crearLlamada(){
        //val intent = Intent(Intent.ACTION_DIAL, android.net.Uri.parse("tel:+34674274634"))
        val intent2 = Intent(Intent.ACTION_CALL, Uri.parse("tel:+34674274634"))
        //startActivity(intent)

        startActivity(intent2)
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {

    }
}
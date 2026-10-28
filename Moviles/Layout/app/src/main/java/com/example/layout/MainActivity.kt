package com.example.layout

import android.graphics.Paint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.example.layout.ui.theme.LayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Box( modifier = Modifier.fillMaxSize())
    {
        /*
        Column(modifier = Modifier.fillMaxSize()) {
            Box(Modifier.fillMaxWidth().weight(2f).background(Color.Gray)){
                Text(
                    text = "Hello $name!",
                    modifier = Modifier.align(Alignment.TopStart)
                )
            }    // 50%
            Box(Modifier.fillMaxWidth().weight(1f).background(Color.LightGray)){
                Text(
                    text = "Hello $name!",
                    modifier = Modifier.align(Alignment.TopStart)
                )
            }
            Box(Modifier.fillMaxWidth().weight(1f).background(Color.Gray)){
                Text(
                    text = "Hello $name!",
                    modifier = Modifier.align(Alignment.TopStart)
                )
            }

        }
        */
        /*
        Row(modifier = Modifier.fillMaxSize())
         {

             Icon(Icons.Default.Star, contentDescription = "Camara", Modifier.size(48.dp))
             Column(modifier = Modifier.padding(12.dp)) {
                 Text("Texto 1")
                 Text("Texto 2")
             }

         }
º       */

        Row(modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically                       ) {

                Icon(Icons.Default.Star, contentDescription = "Camara", Modifier.size(48.dp).padding(1.dp))
                Icon(Icons.Default.Star, contentDescription = "Camara", Modifier.size(48.dp).padding(1.dp))
                Icon(Icons.Default.Star, contentDescription = "Camara", Modifier.size(48.dp).padding(1.dp))

        }





    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LayoutTheme {
        Greeting("Android")
    }
}
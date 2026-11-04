package com.example.layout1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.example.layout1.ui.theme.Layout1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Layout1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    greating()
                }
            }
        }
    }
}

@Composable
fun greating() {
    //Row general
    Box {
        Column( modifier = Modifier.fillMaxSize()) {

            //Row primeras imagenes
            Row(
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color.Green),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically

                ) {

                Icon(
                    Icons.Default.Star, contentDescription = "Camara",
                    Modifier

                        .background(Color.Green)
                        .fillMaxWidth().weight(1f)
                        .fillMaxHeight()

                )
                Icon(
                    Icons.Default.Star, contentDescription = "Camara",
                    Modifier
                        .background(Color.Green)
                        .fillMaxWidth().weight(1f)
                        .fillMaxHeight()
                )
                Icon(
                    Icons.Default.Star, contentDescription = "Camara",
                    Modifier
                        .background(Color.Green)
                        .fillMaxWidth().weight(1f)
                        .fillMaxHeight()
                )

            }
            Row(Modifier.fillMaxWidth().weight(1f),

                verticalAlignment = Alignment.CenterVertically){
                Icon(
                    Icons.Default.Star, contentDescription = "Camara",
                    Modifier
                        .background(Color.LightGray)
                        .fillMaxWidth().weight(1f)
                        .fillMaxHeight()


                )
                Box(Modifier.fillMaxWidth().weight(1f)){

                }
                Icon(
                    Icons.Default.Star, contentDescription = "Camara",
                    Modifier
                        .background(Color.LightGray)
                        .fillMaxWidth().weight(1f)
                        .fillMaxHeight()
                )
            }
            Row(Modifier.fillMaxWidth().weight(1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically){
                Box(Modifier.fillMaxWidth().weight(1f)){

                }
                Icon(
                    Icons.Default.Star, contentDescription = "Camara",
                    Modifier

                        .padding(1.dp)
                        .background(Color.Green)
                        .fillMaxWidth().weight(1f)
                        .fillMaxHeight()
                )
                Box(Modifier.fillMaxWidth().weight(1f)){

                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Layout1Theme {
        greating()
    }
}
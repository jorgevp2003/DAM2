package com.example.kotlinconjetpack

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kotlinconjetpack.ui.theme.KotlinConJetpackTheme



class MainActivity : ComponentActivity() {

    override fun onStart() {
        super.onStart()
        Log.d("CICLO_VIDA", "OnStart ejecutado")
    }

    override fun onResume() {
        super.onResume()
        Log.d("CICLO_VIDA", "OnResume ejecutado")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KotlinConJetpackTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            var textoPantalla by remember {
                                mutableStateOf("")
                            }



                            Row {
                                Boton("1"){textoPantalla += " 1"}
                                Boton("2"){textoPantalla += " 1"}
                                Boton("3"){textoPantalla += " 1"}
                            }

                            Row {
                                Boton("4"){textoPantalla += " 1"}
                                Boton("5"){textoPantalla += " 1"}
                                Boton("6"){textoPantalla += " 1"}
                            }

                            Row {
                                Boton("7"){textoPantalla += " 1"}
                                Boton("8"){textoPantalla += " 1"}
                                Boton("9"){textoPantalla += " 1"}
                            }

                            Row {

                                Boton("0"){textoPantalla += " 1"}

                            }
                            Row{
                                Boton(texto = "+"){textoPantalla += " 1"}
                                Boton(texto = "-"){textoPantalla += " 1"}
                                Boton(texto = "*"){textoPantalla += " 1"}
                                Boton(texto = "/"){textoPantalla += " 1"}
                                Boton(texto = "/"){textoPantalla += " 1"}
                            }
                        }

                       
                        Button(
                            onClick = {
                                // Acción del botón +
                            },
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(16.dp)
                        ) {
                            Text("=")
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotlinConJetpackTheme {
        Greeting("Android")
    }
}
@Composable

fun Boton(texto: String,onClick: ()-> Unit){
    Button(
        onClick= onClick,
        modifier = Modifier
            .padding(4.dp)
            .size(80.dp)
    ){
        Text(texto)
    }
}



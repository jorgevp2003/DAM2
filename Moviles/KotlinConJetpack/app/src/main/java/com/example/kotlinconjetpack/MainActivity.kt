package com.example.kotlinconjetpack

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlinconjetpack.ui.theme.KotlinConJetpackTheme
import java.lang.ArithmeticException


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
                    MiCalculadora()

                }
            }
        }
    }
}



@Composable
fun MiCalculadora(){
    var textoPantalla by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()

    ){
        Box(modifier = Modifier
            .width(1200.dp)
            .height(80.dp)
            .padding(start = 40.dp, end = 40.dp, top = 30.dp)

            .border(
                2.dp,
                Color.Black,
                RoundedCornerShape(10.dp)
            )
            .background(
                Color.LightGray,
                RoundedCornerShape(10.dp)
            ),
            contentAlignment = Alignment.Center,


        )

        {
            Text(textoPantalla, fontSize = 35.sp)
        }

        Box(
            modifier = Modifier
                .height(500.dp)
                .padding(top = 30.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),

                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {



                Row {
                    Boton("1"){textoPantalla += "1"}
                    Boton("2"){textoPantalla += "2"}
                    Boton("3"){textoPantalla += "3"}
                }

                Row {
                    Boton("4"){textoPantalla += "4"}
                    Boton("5"){textoPantalla += "5"}
                    Boton("6"){textoPantalla += "6"}
                }

                Row {
                    Boton("7"){textoPantalla += "7"}
                    Boton("8"){textoPantalla += "8"}
                    Boton("9"){textoPantalla += "9"}
                }

                Row {

                    Boton("0"){textoPantalla += "0"}

                }
                Row{
                    Boton(texto = "+"){textoPantalla += "+"}
                    Boton(texto = "-"){textoPantalla += "-"}
                    Boton(texto = "*"){textoPantalla += "*"}
                    Boton(texto = "/"){textoPantalla += "/"}

                }

            }

            Button(
                onClick = {
                    var operador = ""
                    if(textoPantalla.contains("+"))
                        operador = "+"
                    else if (textoPantalla.contains("-"))
                        operador = "-"
                    else if (textoPantalla.contains("*"))
                        operador = "*"
                    else
                        operador = "/"

                    val partes = textoPantalla.split(operador)

                    if (operador == "+") {
                        var texto = (partes[0].toDouble() + partes[1].toDouble())
                        textoPantalla = "%.2f".format(texto).toString()
                    }else if (operador == "-"){
                        var texto = (partes[0].toDouble() - partes[1].toDouble())
                        textoPantalla = "%.2f".format(texto).toString()
                    }
                    else if (operador == "*"){
                        var texto = (partes[0].toDouble() * partes[1].toDouble())
                        textoPantalla = "%.2f".format(texto).toString()
                    }
                    else {
                        if(partes[0].toDouble() == 0.0 || partes[1].toDouble()== 0.0){
                            textoPantalla = "Error: No se puede dividir por 0"
                        } else {
                            var texto = (partes[0].toDouble() / partes[1].toDouble())
                            textoPantalla = "%.2f".format(texto).toString()
                        }


                    }


                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 26.dp)
            ) {
                Text("=")
            }
            Button(
                onClick = {
                    textoPantalla = ""
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end =90.dp)
            ){
                Text(text = "Clear")
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


@Composable
fun Boton(texto: String,onClick: ()-> Unit){
    TextButton(
        onClick= onClick,
        modifier = Modifier
            .padding(2.dp)
            .size(60.dp)
            .clickable { onClick() }
            .background(Color.LightGray, RoundedCornerShape(10.dp))
            .border(
                2.dp,
                Color.Black,
                RoundedCornerShape(10.dp)
            )
    ){
        Text(texto)
    }
}

@Preview(showBackground = true)
@Composable
fun CalculadoraPreview() {
    KotlinConJetpackTheme {
        MiCalculadora()
    }
}



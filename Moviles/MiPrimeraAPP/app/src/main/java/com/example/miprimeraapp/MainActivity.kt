package com.example.miprimeraapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import com.example.miprimeraapp.ui.theme.MiPrimeraAPPTheme
import kotlinx.coroutines.selects.select
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MiPrimeraAPPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Main()
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
    Main()
}

@Composable
fun Main(){

    MiPrimeraAPPTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(){

                var dias by remember { mutableStateOf("")}
                var horas by remember { mutableStateOf("")}
                var pago by remember{ mutableStateOf(0.0)}
                var pagoDesc by remember{ mutableStateOf(0.0)}

                Box(  modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                ){
                    Box(modifier = Modifier
                        .fillMaxSize(),
                        contentAlignment = Alignment.Center

                    ){
                        Box(modifier = Modifier.padding(top = 60.dp)){

                            Text(text = "IES Ciudad Escolar")
                        }
                        Box(modifier = Modifier ){
                            Text(text= "Calculo de Nomina", fontSize = 25.sp)

                        }
                    }
                }

                Box( modifier = Modifier.padding(top = 60.dp)){
                    Column(modifier = Modifier.height(100.dp)) {
                        Row( modifier = Modifier.height(30.dp)) {
                            Text(text = "Dias :" )
                            OutlinedTextField(
                                value = dias,
                                onValueChange = {dias = it}, modifier = Modifier.padding(start = 10.dp).background(Color.LightGray)

                                    .border(2.dp, Color.Black))

                            Text(text = dias )


                        }
                        Row(modifier = Modifier
                            .height(50.dp)
                            .padding(top = 20.dp) ) {
                            Text(text = "Horas :" )
                            OutlinedTextField(
                                value = horas,
                                onValueChange = {horas = it}, modifier = Modifier.background(Color.LightGray)
                                    .border(2.dp, Color.Black))
                        }
                        Text(text = horas )
                    }
                    Column( modifier = Modifier
                        .height(300.dp)
                        .padding(bottom = 30.dp)){
                        var marcado1 by remember{
                            mutableStateOf(false)
                        }
                        var marcado2 by remember{
                            mutableStateOf(false)
                        }
                        Column(modifier = Modifier.padding(top = 80.dp)){
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ){
                                Text(text = "Pago:",
                                    Modifier
                                        .align(Alignment.Center)
                                        .padding(end = 250.dp))
                                Checkbox(
                                    checked = marcado1 ,
                                    onCheckedChange = {
                                        marcado ->
                                        marcado1 = marcado
                                        if(marcado) {
                                            pago = (horas.toDoubleOrNull()?.times(13.5) ?: 0.0) + (dias.toInt()*8) * 13.5
                                        } else {
                                            pago = 0.0
                                        }
                                })
                                Text(text = pago.toString(),
                                    Modifier
                                        .align(Alignment.Center)
                                )


                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ){
                                Text(text="Descuento:",
                                    Modifier
                                        .align(Alignment.Center)
                                        .padding(end = 210.dp))
                                Checkbox(checked = marcado2 , onCheckedChange ={
                                    marcado ->
                                    marcado2 = marcado
                                    if(marcado && pago >2000) {
                                        pagoDesc = pago - (pago*0.10)
                                    } else {
                                        pagoDesc = pago
                                    }
                                })
                                Text(text = pagoDesc.toString(),
                                    Modifier
                                        .align(Alignment.Center)
                                        .padding(start = 70.dp))
                            }
                        }

                        Column(){

                            Box(
                                modifier = Modifier
                                .fillMaxWidth())
                            {

                                var red1 by remember {
                                    mutableStateOf(false)
                                }
                                var red2 by remember {
                                    mutableStateOf(false)
                                }

                                RadioButton(selected = red1, onClick = {
                                    pago = pago.roundToInt().toDouble()
                                    pagoDesc = pagoDesc.roundToInt().toDouble()
                                    red1 = true
                                    red2 = false
                                })
                                Text(text = "Redondeo",
                                    modifier = Modifier
                                        .padding(top = 12.dp, start= 40.dp))


                                RadioButton(selected = red2, onClick = {
                                    pago = (horas.toDoubleOrNull()?.times(13.5) ?: 0.0) + (dias.toInt()*8) * 13.5
                                    pagoDesc = pago - (pago*0.10)
                                    red1 = false
                                    red2 = true
                                },
                                    modifier = Modifier
                                        .padding(top = 30.dp)
                                )
                                Text(text = "No Redondeo",
                                    modifier = Modifier
                                        .padding(top = 42.dp, start= 40.dp)

                                    )
                            }
                        }



                    }

                }
            }



        }
    }


}
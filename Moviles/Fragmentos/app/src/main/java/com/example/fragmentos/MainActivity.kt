package com.example.fragmentos

import com.example.fragmentos.HolaFragment
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.fragment.app.FragmentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.commit
import com.example.fragmentos.ui.theme.FragmentosTheme

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState : Bundle?){

        super.onCreate(savedInstanceState)
        //Creamos su caja para cada uno:
        val caja1 = FrameLayout(this)
        caja1.id = R.id.caja_1

        val caja2 = FrameLayout(this)
        caja2.id = R.id.caja_2

        val caja3 = FrameLayout(this)
        caja3.id = R.id.caja_3

        // Lo ponemos en responsive
        val esAncha = resources.configuration.screenWidthDp >= 600
        val pantalla = LinearLayout(this)
        //Dependiendo de si es mas ancho que la pantalla coloca de manera horizontal o vertical
        pantalla.orientation = if (esAncha) LinearLayout.HORIZONTAL else LinearLayout.VERTICAL

        val tamaño = if (esAncha) {
            LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1f)
        } else {
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f)
        }

        //Metemos cada caja
        pantalla.addView(caja1, LinearLayout.LayoutParams(tamaño))
        pantalla.addView(caja2, LinearLayout.LayoutParams(tamaño))
        pantalla.addView(caja3, LinearLayout.LayoutParams(tamaño))

        setContentView(pantalla)

        if(savedInstanceState == null){
            supportFragmentManager.commit{
                replace(R.id.caja_1, HolaFragment())
                replace(R.id.caja_2,ImagenFragment())
                replace(R.id.caja_3,FinalFragment())
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
    FragmentosTheme {
        Greeting("Android")
    }
}
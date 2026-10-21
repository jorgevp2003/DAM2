
package com.example.kotlinconjetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.kotlinconjetpack.ui.theme.KotlinConJetpackTheme

class SegundaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KotlinConJetpackTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {

                    cajas()

                }
            }
        }
    }
    @Composable
    fun cajas() {
        Image(
            painter = painterResource(id = R.drawable.logociudadescolar2),
            contentDescription = "Mi imagen",
            modifier = Modifier.size(200.dp)
        )
    }
}


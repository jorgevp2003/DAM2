package com.example.miprimeraapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    private var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("CICLO", "onCreate: ${++i}")
    }

    override fun onStart() {
        super.onStart()
        Log.d("CICLO", "onStart: ${++i}")
    }

    override fun onResume() {
        super.onResume()
        Log.d("CICLO", "onResume: ${++i}")
    }

    override fun onPause() {
        super.onPause()
        Log.d("CICLO", "onPause: ${++i}")
    }

    override fun onStop() {
        super.onStop()
        Log.d("CICLO", "onStop: ${++i}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("CICLO", "onDestroy: ${++i}")
    }
}
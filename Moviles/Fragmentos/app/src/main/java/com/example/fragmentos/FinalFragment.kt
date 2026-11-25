package com.example.fragmentos

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.ui.graphics.Color
import androidx.fragment.app.Fragment

class FinalFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val texto = TextView(requireContext())
        texto.text = "Fragmento final"
        texto.gravity = Gravity.CENTER
        return texto
    }
}
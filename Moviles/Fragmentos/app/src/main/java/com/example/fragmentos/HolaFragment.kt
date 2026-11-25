package com.example.fragmentos

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

class HolaFragment : Fragment(){
    //Metodo al que llama kt cuando necesita vista de fragmento
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Texto
        val texto = TextView(requireContext())
        texto.text = "Hola mundo 2 "
        texto.gravity = Gravity.CENTER


        //EstiloBackground
        val fondo = GradientDrawable()
        fondo.setColor(Color.parseColor("#E53935"))
        fondo.setStroke(dp(3), Color.parseColor("#E53935"))
        fondo.cornerRadius = dp(16).toFloat()

        val caja = FrameLayout(requireContext())
        caja.background = fondo
        caja.setPadding(dp(24),dp(24),dp(24),dp(24))

        caja.addView(
            texto,
            FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER
            )
        )


        //Con exterior se adpata al tamaño del texto
        val exterior = FrameLayout(requireContext())
        exterior.setPadding(dp(16),dp(16),dp(16),dp(16))
        exterior.addView(
            caja,
            FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER
            )
        )
        return exterior

    }

    private fun dp(valor: Int) = (valor * resources.displayMetrics.density).toInt()
}
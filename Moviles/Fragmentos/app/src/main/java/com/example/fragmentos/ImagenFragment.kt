package com.example.fragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class ImagenFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        val imagen = ImageView(requireContext())
        imagen.setImageResource(R.drawable.logo)
        imagen.scaleType = ImageView.ScaleType.FIT_CENTER
        return imagen
    }
}
package com.example.shafie.presentation.ui.Congratulations

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shafie.databinding.FragmentOrderPlacedBinding
import com.example.shafie.presentation.ui.home.HomeActivity


class CongratulationFragment : Fragment() {

    lateinit var binding:FragmentOrderPlacedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderPlacedBinding.inflate(inflater)

        //getting back to the HomeActivity after click
        binding.btnContinueShopping.setOnClickListener {
            val intent = Intent(context,HomeActivity::class.java)
            startActivity(intent)

        }
        // Inflate the layout for this fragment
        return binding.root
    }

}
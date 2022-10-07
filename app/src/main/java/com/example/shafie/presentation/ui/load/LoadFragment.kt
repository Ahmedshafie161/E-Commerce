package com.example.shafie.presentation.ui.load

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shafie.R
import com.example.shafie.presentation.ui.Congratulations.CongratulationFragment


class LoadFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Handler(Looper.getMainLooper()).postDelayed({
        activity?.supportFragmentManager?.beginTransaction()?.
            addToBackStack(null)?.
                replace(R.id.loaderFragment,CongratulationFragment())?.commit()
        },30000)


        return inflater.inflate(R.layout.fragment_loader, container, false)
    }

}
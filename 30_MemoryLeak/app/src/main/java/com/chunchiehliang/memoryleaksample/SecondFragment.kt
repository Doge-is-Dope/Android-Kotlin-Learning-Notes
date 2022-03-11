package com.chunchiehliang.memoryleaksample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.chunchiehliang.memoryleaksample.databinding.FragmentFirstBinding
import com.chunchiehliang.memoryleaksample.databinding.FragmentSecondBinding

class SecondFragment :  BaseFragment<FragmentSecondBinding>(FragmentSecondBinding::inflate)  {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSecond.setOnClickListener {
            findNavController().navigate(R.id.action_to_first)
        }
    }
}
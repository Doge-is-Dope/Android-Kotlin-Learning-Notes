package com.chunchiehliang.memoryleaksample

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.chunchiehliang.memoryleaksample.databinding.FragmentFirstBinding

class FirstFragment : BaseFragment<FragmentFirstBinding>(FragmentFirstBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.btnSecond.setOnClickListener {
            findNavController().navigate(R.id.action_to_second)
        }
    }
}
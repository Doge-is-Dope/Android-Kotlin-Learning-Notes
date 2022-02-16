package com.chunchiehliang.memoryleaksample

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.chunchiehliang.memoryleaksample.databinding.FragmentFirstBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FirstFragment : BaseFragment<FragmentFirstBinding>(FragmentFirstBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Default) {
            delay(3000)
            withContext(Dispatchers.Main) {
                binding.tvLabelFirst.text = "Finished"
            }
        }

        binding.btnSecond.setOnClickListener {
            findNavController().navigate(R.id.action_to_second)
        }
    }
}
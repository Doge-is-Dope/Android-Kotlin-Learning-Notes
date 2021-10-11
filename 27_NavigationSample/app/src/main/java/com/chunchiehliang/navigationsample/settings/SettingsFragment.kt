package com.chunchiehliang.navigationsample.settings

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.chunchiehliang.navigationsample.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnToDetails.setOnClickListener {
            Log.d("Settings", "click")
            findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToSettingsDetailsFragment())

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
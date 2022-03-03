package com.chunchiehliang.material3.ui.filter

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedDispatcher
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.chunchiehliang.material3.R
import com.chunchiehliang.material3.databinding.FragmentFilterBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class FilterFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleNavigation()
        binding.toolbar.setupWithNavController(navController)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun handleNavigation() {
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.fragment_container_filter) as NavHostFragment
        navController = navHostFragment.navController
        dialog?.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP)
                if (!navController.navigateUp()) dismiss()
            true
        }
    }

}
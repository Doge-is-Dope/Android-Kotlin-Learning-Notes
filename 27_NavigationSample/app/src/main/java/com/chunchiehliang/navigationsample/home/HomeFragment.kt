package com.chunchiehliang.navigationsample.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chunchiehliang.navigationsample.R
import com.chunchiehliang.navigationsample.databinding.FragmentHomeBinding
import com.chunchiehliang.navigationsample.databinding.FragmentUserBinding
import com.chunchiehliang.navigationsample.utils.navigate
import timber.log.Timber

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.apply {
            navigateToProduct.observe(viewLifecycleOwner, {
                it?.let {
                    navigate(HomeFragmentDirections.actionToProduct())
                    onNavigateToProductComplete()
                }
            })

            navigateToUser.observe(viewLifecycleOwner, {
                it?.let {
                    navigate(HomeFragmentDirections.actionToUser())
                    onNavigateToUserComplete()
                }
            })
        }

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = homeViewModel
        }
    }
}
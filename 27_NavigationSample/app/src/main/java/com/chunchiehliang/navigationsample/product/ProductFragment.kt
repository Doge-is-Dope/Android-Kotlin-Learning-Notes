package com.chunchiehliang.navigationsample.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.chunchiehliang.navigationsample.databinding.FragmentProductBinding
import com.chunchiehliang.navigationsample.utils.navigate

class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    private val productViewModel by viewModels<ProductViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productViewModel.apply {
            navigateToUser.observe(viewLifecycleOwner, {
                it?.let {
                    navigate(ProductFragmentDirections.actionToUser())
                    onNavigateToUserComplete()
                }
            })
        }

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = productViewModel
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.chunchiehliang.navigationsample.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.chunchiehliang.navigationsample.databinding.FragmentProductBinding
import com.chunchiehliang.navigationsample.utils.InternalDeepLink
import com.chunchiehliang.navigationsample.utils.navigate

class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<ProductFragmentArgs>()

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
            setProductNo(args.productNo)
        }

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = productViewModel
            toolbar.setupWithNavController(findNavController())

            btnToUser.setOnClickListener {
                val deeplink = InternalDeepLink.getUserDeepLink(username = "john")
                findNavController().navigate(deeplink.toUri())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
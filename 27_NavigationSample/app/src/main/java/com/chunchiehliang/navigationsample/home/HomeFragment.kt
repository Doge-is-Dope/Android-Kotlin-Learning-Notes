package com.chunchiehliang.navigationsample.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chunchiehliang.navigationsample.databinding.FragmentHomeBinding
import com.chunchiehliang.navigationsample.utils.InternalDeepLink

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
//
//        requireActivity()
//            .onBackPressedDispatcher
//            .addCallback(this, object : OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    // if you want onBackPressed() to be called as normal afterwards
//                    findNavController().popBackStack()
//                }
//            }
//            )

        homeViewModel.apply {
            navigateToUser.observe(viewLifecycleOwner, {
                it?.let {
                    val deeplink = InternalDeepLink.getUserDeepLink(username = "momo")
                    findNavController().navigate(deeplink.toUri())
                    onNavigateToUserComplete()
                }
            })
        }

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = homeViewModel

            btnToProduct.setOnClickListener {
                val deeplink = InternalDeepLink.getProductDeepLink(productNo = 111L)
                findNavController().navigate(deeplink.toUri())
            }
        }
    }
}
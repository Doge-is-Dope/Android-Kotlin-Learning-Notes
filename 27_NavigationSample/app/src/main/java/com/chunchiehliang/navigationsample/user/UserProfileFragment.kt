package com.chunchiehliang.navigationsample.user

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
import com.chunchiehliang.navigationsample.databinding.FragmentUserProfileBinding
import com.chunchiehliang.navigationsample.utils.InternalDeepLink
import com.chunchiehliang.navigationsample.utils.RandomNames
import timber.log.Timber

class UserProfileFragment : Fragment() {

    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<UserProfileFragmentArgs>()

    private val userViewModel by viewModels<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Timber.d("User profile")

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = userViewModel
            findNavController().let {
                toolbar.setupWithNavController(it)
            }

            btnToProduct.setOnClickListener {
                val deeplink = InternalDeepLink.getProductDeepLink(productNo = 111L)
                findNavController().navigate(deeplink.toUri())
            }

            btnToMy.setOnClickListener {
                val deeplink = InternalDeepLink.getUserDeepLink(username = "wei")
                findNavController().navigate(deeplink.toUri())
            }

            btnToUser.setOnClickListener {
                val deeplink = InternalDeepLink.getUserDeepLink(username = RandomNames.getName())
                findNavController().navigate(deeplink.toUri())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
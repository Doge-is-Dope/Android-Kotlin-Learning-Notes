package com.chunchiehliang.navigationsample.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.chunchiehliang.navigationsample.R
import com.chunchiehliang.navigationsample.databinding.FragmentUserMyProfileBinding
import com.chunchiehliang.navigationsample.utils.InternalDeepLink
import timber.log.Timber

class MyProfileFragment : Fragment() {

    private var _binding: FragmentUserMyProfileBinding? = null
    private val binding get() = _binding!!

    private val userViewModel by viewModels<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserMyProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Timber.d("My profile")

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = userViewModel
            findNavController().let {
                val appBarConfiguration = AppBarConfiguration(
                    setOf(R.id.myProfileFragment)
                )
                toolbar.setupWithNavController(it, appBarConfiguration)
            }

            btnToUser.setOnClickListener {
                val deeplink = InternalDeepLink.getUserDeepLink(username = "andrew")
                findNavController().navigate(deeplink.toUri())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
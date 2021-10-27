package com.chunchiehliang.navigationsample.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.chunchiehliang.navigationsample.databinding.FragmentAddBinding
import com.chunchiehliang.navigationsample.utils.InternalDeepLink
import com.chunchiehliang.navigationsample.utils.RandomNames


class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            toolbar.setupWithNavController(findNavController())
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
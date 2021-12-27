package com.chunchiehliang.navigationsample.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.chunchiehliang.navigationsample.R
import com.chunchiehliang.navigationsample.databinding.FragmentExploreBinding
import timber.log.Timber

class ExploreFragment : Fragment() {

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<ExploreFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            val appBarConfiguration = AppBarConfiguration(
                setOf(R.id.exploreFragment)
            )
            toolbar.setupWithNavController(findNavController(), appBarConfiguration)
        }

        Timber.d("args postNo: ${args.postNo}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.chunchiehliang.material3.ui.search

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.chunchiehliang.material3.data.City
import com.chunchiehliang.material3.databinding.FragmentComponentListBinding
import com.chunchiehliang.material3.databinding.FragmentSearchBinding
import com.chunchiehliang.material3.ui.setDrawableEnd
import org.imaginativeworld.popchillimagecarousel.model.CarouselItem
import timber.log.Timber


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.toolbar.setupWithNavController(findNavController())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.chunchiehliang.material3.ui.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.chunchiehliang.material3.databinding.FragmentFilterPriceBinding


class FilterPriceFragment : Fragment() {

    private var _binding: FragmentFilterPriceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFilterPriceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            slider.setLabelFormatter { value: Float ->
                formatValueToCurrencyString(value)
            }
            slider.addOnChangeListener { slider, value, fromUser ->
                val (minValue, maxValue) = slider.values
                tvPrice.text = formatCurrencyStringToLabel(requireContext(), minValue, maxValue)
            }

            btnApply.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
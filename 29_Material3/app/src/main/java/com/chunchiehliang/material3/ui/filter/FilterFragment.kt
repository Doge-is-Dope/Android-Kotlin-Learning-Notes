package com.chunchiehliang.material3.ui.filter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.chunchiehliang.material3.R
import com.chunchiehliang.material3.databinding.FragmentFilterBinding
import com.chunchiehliang.material3.utils.JsonUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.slider.RangeSlider
import com.google.android.material.slider.Slider
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import timber.log.Timber
import java.text.NumberFormat
import java.util.*


class FilterFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<FilterFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.slider.setLabelFormatter { value: Float ->
            formatValue(value)
        }
        binding.slider.addOnChangeListener { slider, value, fromUser ->
            val (minValue, maxValue) = slider.values
            val formattedMinValueString = formatValue(minValue)
            val formattedMaxValueString = formatValue(maxValue)
//            Timber.d("min: $minValue, max: $maxValue")
            if (minValue == 0.0F && maxValue == 20000.0F) binding.tvPrice.text =
                getString(R.string.any)
            else if (maxValue == 20000.0F) binding.tvPrice.text =
                getString(R.string.above_value, formattedMinValueString)
            else if (minValue == 0.0F) binding.tvPrice.text =
                getString(R.string.up_to_value, formattedMaxValueString)
            else binding.tvPrice.text =
                getString(R.string.between_values, formattedMinValueString, formattedMaxValueString)
        }

        val sizes = args.sizes?.let { JsonUtil.parseStringToIntList(it) }
        val conditions = args.conditions?.let { JsonUtil.parseStringToIntList(it) }
        Timber.d("id: ${args.id}" +
                "\nmax: ${args.maxPrice}" +
                "\nmin: ${args.minPrice}" +
                "\nconditions: $conditions" +
                "\nsizes: $sizes" +
                "\nsort: ${args.sort}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun formatValue(value: Float): String {
        val format = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 0
        format.currency = Currency.getInstance("TWD")
        return format.format(value.toInt())
    }
}
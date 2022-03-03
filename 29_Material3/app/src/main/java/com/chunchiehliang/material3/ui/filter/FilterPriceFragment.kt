package com.chunchiehliang.material3.ui.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.chunchiehliang.material3.R
import com.chunchiehliang.material3.databinding.FragmentFilterPriceBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import timber.log.Timber
import java.text.NumberFormat
import java.util.*


class FilterPriceFragment : Fragment() {

    private var _binding: FragmentFilterPriceBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            // Handle the back button event
            Timber.d("back")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFilterPriceBinding.inflate(inflater, container, false)
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

//        val sizes = args.sizes?.let { JsonUtil.parseStringToIntList(it) }
//        val conditions = args.conditions?.let { JsonUtil.parseStringToIntList(it) }
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
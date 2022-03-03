package com.chunchiehliang.material3.ui.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.chunchiehliang.material3.R
import com.chunchiehliang.material3.databinding.FragmentFilterPriceBinding
import com.chunchiehliang.material3.utils.setNavigationResult
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class FilterPriceFragment : Fragment() {

    private var _binding: FragmentFilterPriceBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<FilterPriceFragmentArgs>()

    private var priceRange: Pair<Float, Float> = Pair(0F, 20_000F)

    private val filterViewModel by viewModel<FilterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFilterPriceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()
        binding.apply {
            slider.setLabelFormatter { value: Float ->
                formatValueToCurrencyString(value)
            }
            slider.addOnChangeListener { slider, value, fromUser ->
                val (minValue, maxValue) = slider.values
                priceRange = Pair(minValue, maxValue)
                tvPrice.text = formatCurrencyStringToLabel(requireContext(), minValue, maxValue)
            }

            slider.setValues(args.prevMinPrice, args.prevMaxPrice)

            btnNav.setOnClickListener {
                setNavigationResult("price", priceRange)
                navController.popBackStack()
            }

            btnApply.setOnClickListener {
                parentFragment?.parentFragment?.findNavController()?.apply {
                    previousBackStackEntry?.savedStateHandle?.set("filter_price", priceRange)
                    popBackStack()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
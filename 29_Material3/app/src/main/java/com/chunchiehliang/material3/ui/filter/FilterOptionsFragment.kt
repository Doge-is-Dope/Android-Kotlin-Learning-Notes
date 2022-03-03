package com.chunchiehliang.material3.ui.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.chunchiehliang.material3.data.FilterOption
import com.chunchiehliang.material3.databinding.FragmentFilterOptionsBinding
import com.chunchiehliang.material3.utils.getNavigationResult
import com.chunchiehliang.material3.utils.setNavigationResult
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class FilterOptionsFragment : Fragment() {

    private var _binding: FragmentFilterOptionsBinding? = null
    private val binding get() = _binding!!

    private val filterViewModel by viewModel<FilterViewModel>()

    private val optionAdapter by lazy {
        FilterOptionAdapter(OptionListener {
            when (it.id) {
                0 -> Timber.d("0")
                1 -> {
                    val prevMinPrice = filterViewModel.priceRange.value?.first ?: 0.0F
                    val prevMaxPrice = filterViewModel.priceRange.value?.second ?: 20000.0F
                    findNavController().navigate(FilterOptionsFragmentDirections.actionToFilterPrice(
                        prevMinPrice,
                        prevMaxPrice))
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFilterOptionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.options.apply {
            setHasFixedSize(true)
            stateListAnimator = null
            adapter = optionAdapter
        }

        binding.btnApply.setOnClickListener {
            parentFragment?.parentFragment?.findNavController()?.apply {
                previousBackStackEntry?.savedStateHandle?.set<List<FilterOption>>("filter",
                    filterViewModel.optionsFlow.value)
                popBackStack()
            }
        }

        getNavigationResult<Pair<Float, Float>>("price")
            ?.observe(viewLifecycleOwner) {
                Timber.d("result: $it")
                filterViewModel.updatePriceRange(it)
            }

        viewLifecycleOwner.lifecycleScope.launch {
            filterViewModel.combinedFlow.collectLatest {
                optionAdapter.submitList(it)
                optionAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
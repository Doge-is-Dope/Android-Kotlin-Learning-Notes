package com.chunchiehliang.material3.ui.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.chunchiehliang.material3.data.FilterOption
import com.chunchiehliang.material3.databinding.FragmentFilterOptionsBinding
import timber.log.Timber


class FilterOptionsFragment : Fragment() {

    private var _binding: FragmentFilterOptionsBinding? = null
    private val binding get() = _binding!!

    private val optionAdapter by lazy {
        FilterOptionAdapter(OptionListener {
            when (it.id) {
                0 -> Timber.d("0")
                1 -> findNavController().navigate(FilterOptionsFragmentDirections.actionToFilterPrice())
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

        binding.options.apply {
            setHasFixedSize(true)
            adapter = optionAdapter
        }

        optionAdapter.submitList(options)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        val options = listOf(
            FilterOption(0, "Category"), FilterOption(1, "Price")
        )
    }
}
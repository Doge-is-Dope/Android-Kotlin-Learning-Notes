package com.chunchiehliang.material3.ui.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.chunchiehliang.material3.data.FilterOption
import com.chunchiehliang.material3.databinding.FragmentFilterBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import timber.log.Timber


class FilterOptionsFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!

    private val optionAdapter by lazy {
        FilterOptionAdapter(OptionListener {
            Timber.d("clicked: $it")
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.options.apply {
            setHasFixedSize(true)
            adapter = optionAdapter
        }

        optionAdapter.submitList(listOf(FilterOption(0, "Recommended"), FilterOption(1, "Price")))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
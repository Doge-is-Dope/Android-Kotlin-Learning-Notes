package com.chunchiehliang.material3.ui.sort

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chunchiehliang.material3.data.SortOption
import com.chunchiehliang.material3.databinding.FragmentSortBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import timber.log.Timber


class SortFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentSortBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSortBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sortOptionAdapter = SortOptionAdapter(listener = OptionListener {
            Timber.d("clicked: $it")
        })
        binding.options.apply {
            setHasFixedSize(true)
            adapter = sortOptionAdapter
        }

        sortOptionAdapter.submitList(listOf(SortOption(0, "Default"),
            SortOption(1, "Price: High to low"),
            SortOption(2, "Price: Low to high"),
            SortOption(3, "Most popular"),
            SortOption(4, "Newly listed")))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.chunchiehliang.material3.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.chunchiehliang.material3.data.SearchTerm
import com.chunchiehliang.material3.data.SearchTermsModule
import com.chunchiehliang.material3.databinding.FragmentSearchBinding
import timber.log.Timber


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val searchDefaultModuleAdapter = SearchTermsModuleAdapter(SearchTermListener {
        Timber.d("clicked: $it")
    })

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
        binding.dataList.apply {
            adapter = searchDefaultModuleAdapter
        }

        searchDefaultModuleAdapter.submitList(listOf(
            SearchTermsModule("Recent", listOf(
                SearchTerm(0, "大衣大衣", false),
            )),
            SearchTermsModule("Hot", listOf(
                SearchTerm(0, "大衣", true),
                SearchTerm(1, "ZARA"),
                SearchTerm(2, "格紋西外"),
                SearchTerm(3, "CHARLES & KEITH"),
                SearchTerm(4, "新年斷捨離故事集"),
            )),
        ))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
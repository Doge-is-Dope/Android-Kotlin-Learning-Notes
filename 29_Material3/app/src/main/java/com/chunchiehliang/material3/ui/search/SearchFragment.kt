package com.chunchiehliang.material3.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chunchiehliang.material3.R
import com.chunchiehliang.material3.data.SearchPromotion
import com.chunchiehliang.material3.data.SearchPromotionsModule
import com.chunchiehliang.material3.data.SearchTerm
import com.chunchiehliang.material3.data.SearchTermsModule
import com.chunchiehliang.material3.databinding.FragmentSearchBinding
import com.chunchiehliang.material3.databinding.ItemSearchPromotionBinding
import timber.log.Timber


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val searchTermsModuleAdapter by lazy {
        SearchTermsModuleAdapter(SearchTermListener {
            Timber.d("clicked: $it")
        })
    }

    private val promotionsModuleAdapter by lazy {
        SearchPromotionsModuleAdapter(SearchPromotionListener {
            Timber.d("clicked: $it")
        })
    }

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
        val concatAdapter = ConcatAdapter(searchTermsModuleAdapter, promotionsModuleAdapter)
        binding.dataList.apply {
            adapter = concatAdapter
        }

        searchTermsModuleAdapter.submitList(listOf(
            SearchTermsModule("Recent", listOf(
                SearchTerm(0, "Shirt", false),
            )),
            SearchTermsModule("Hot", listOf(
                SearchTerm(0, "Lorem ipsum sit amet", true),
                SearchTerm(3, "aliquam"),
                SearchTerm(1, "egeturna"),
                SearchTerm(2, "vel sit"),
                SearchTerm(4, "Etiam egestas"),
            )),
        ))

        promotionsModuleAdapter.submitList(listOf(
            SearchPromotionsModule(
                getString(R.string.recommended),
                listOf(
                    SearchPromotion(
                        100,
                        "https://i.imgur.com/yG9yUVa.jpg",
                        "Tzuyu", null, null),
                    SearchPromotion(
                        102,
                        "https://i.imgur.com/W4tdtZT.jpg",
                        "IU", null, null),
                    SearchPromotion(
                        103,
                        "https://i.imgur.com/kMY5o1L.jpg",
                        "Taeyeon", null, null),
                )
            )
        ))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
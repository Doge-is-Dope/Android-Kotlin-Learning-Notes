package com.chunchiehliang.material3.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.PagerSnapHelper
import com.chunchiehliang.material3.data.City
import com.chunchiehliang.material3.databinding.FragmentComponentListBinding
import timber.log.Timber


class ComponentListFragment : Fragment() {

    private val cityAdapter by lazy {
        CityAdapter(listener = CityListener {
            Timber.d("clicked: $it")
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentComponentListBinding.inflate(layoutInflater)

        binding.cityList.apply {
            setHasFixedSize(true)
            adapter = cityAdapter
            PagerSnapHelper().attachToRecyclerView(this)
        }

        cityAdapter.submitList(listOf(City(0, "#EF7C8E", "New York City", "sub", "desc"),
            City(1, "#FAE8E0", "Boston", "sub", "desc"),
            City(2, "#B6E2D3", "Philadelphia", "sub", "desc"),
            City(3, "#D8A7B1", "Washington DC", "sub", "desc"),
            City(4, "#FFF4BD", "New Haven", "sub", "desc"),
            City(5, "#F4B9B8", "Cambridge", "sub", "desc"),
            City(6, "#887BB0", "Providence", "sub", "desc"),
            City(7, "#85D2D0", "Hartford", "sub", "desc"),
            City(8, "#376380", "Worcester", "sub", "desc")))

        return binding.root
    }
}
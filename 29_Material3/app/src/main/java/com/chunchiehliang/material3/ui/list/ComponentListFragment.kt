package com.chunchiehliang.material3.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.chunchiehliang.material3.data.City
import com.chunchiehliang.material3.data.FilterOption
import com.chunchiehliang.material3.databinding.FragmentComponentListBinding
import org.imaginativeworld.popchillimagecarousel.model.CarouselItem
import timber.log.Timber


class ComponentListFragment : Fragment() {

    private var _binding: FragmentComponentListBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<ComponentListFragmentArgs>()

    private val cityAdapter by lazy {
        CityAdapter(listener = CityListener {
            Timber.d("clicked: $it")
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentComponentListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.toolbar.setupWithNavController(findNavController())

        binding.cityList.apply {
            setHasFixedSize(true)
            adapter = cityAdapter
            val listener = object : HidingScrollListener() {
                override fun onHide() {
                    hideViews()
                }

                override fun onShow() {
                    showViews()
                }
            }
            addOnScrollListener(listener)
        }

        binding.cityImgList.apply {
            registerLifecycle(viewLifecycleOwner)
            val images = listOf(
                CarouselItem("https://images.unsplash.com/photo-1532581291347-9c39cf10a73c?w=800"),
                CarouselItem("https://images.unsplash.com/photo-1644976541214-2a5d7e1a1f79?w=800"),
                CarouselItem("https://images.unsplash.com/photo-1643944398479-0fd9eaee5cbc?w=800"),
            )
            setData(images)
        }

        cityAdapter.submitList(listOf(
            City(0, "#EF7C8E", "New York City", "The Big Apple", "desc"),
            City(1, "#FAE8E0", "Boston", "The City on a Hill", "desc"),
            City(2, "#354f52", "Philadelphia", "The City of Brotherly Love", "desc"),
            City(3, "#e3d5ca", "Washington DC", "The District", "desc"),
            City(4, "#FFF4BD", "New Haven", "The Elm City", "desc"),
            City(5, "#F4B9B8", "Seattle", "Emerald City", "desc"),
            City(6, "#f2d0a9", "Providence", "Chance", "desc"),
            City(7, "#85D2D0", "Houston", "Space City", "desc"),
            City(8, "#376380", "Los Angeles", "L.A.", "desc"),
            City(9, "#7678ed", "Salt Lake City", "The Crossroads of the West", "desc"),
            City(10, "#f07167", "Atlanta", "The ATL", "desc"),
            City(11, "#B6E2D3", "Chicago", "Windy City", "desc"),
        ))

        binding.layoutSearchButtonGroup.apply {
            linearButtonGroup.clipToOutline = true
            btnSort.setOnClickListener {
                findNavController().navigate(ComponentListFragmentDirections.actionToSort())
            }
            btnFilter.setOnClickListener {
                findNavController().navigate(ComponentListFragmentDirections.actionToFilter())
            }
        }

        val sizes = args.sizes?.split(",")
        val conditions = args.conditions
        Timber.d("id: ${args.term}" +
                "\nmax: ${args.maxPrice}" +
                "\nmin: ${args.minPrice}" +
                "\nconditions: $conditions" +
                "\nsizes: $sizes" +
                "\nsort: ${args.sort}")


        findNavController().currentBackStackEntry?.savedStateHandle
            ?.getLiveData<Pair<Float, Float>>("filter_price")
            ?.observe(viewLifecycleOwner) {
                Timber.d("filter_price: $it")
                Toast.makeText(context, "min: ${it.first}, max: ${it.second}", Toast.LENGTH_SHORT)
                    .show()
            }

        findNavController().currentBackStackEntry?.savedStateHandle
            ?.getLiveData<List<FilterOption>>("filter")
            ?.observe(viewLifecycleOwner) {
                Timber.d("filter: $it")
                Toast.makeText(context, "filter: $it", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun hideViews() {
        val lp =
            binding.layoutSearchButtonGroup.linearButtonGroup.layoutParams as ViewGroup.MarginLayoutParams
        val groupMargin = lp.bottomMargin
        binding.layoutSearchButtonGroup.linearButtonGroup.animate()
            .translationY((binding.layoutSearchButtonGroup.linearButtonGroup.height + groupMargin).toFloat())
            .setInterpolator(AccelerateInterpolator(1.5F))
            .start()
    }

    private fun showViews() {
        binding.layoutSearchButtonGroup.linearButtonGroup.animate()
            .translationY(0F)
            .setInterpolator(DecelerateInterpolator(1.5F))
            .start()
    }
}
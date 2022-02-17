package com.chunchiehliang.material3.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.chunchiehliang.material3.data.City
import com.chunchiehliang.material3.databinding.FragmentComponentListBinding
import org.imaginativeworld.popchillimagecarousel.model.CarouselItem
import timber.log.Timber


class ComponentListFragment : Fragment() {

    private var _binding: FragmentComponentListBinding? = null
    private val binding get() = _binding!!

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
                CarouselItem("https://images.unsplash.com/photo-1532581291347-9c39cf10a73c?w=1080"),
                CarouselItem("https://images.unsplash.com/photo-1532581291347-9c39cf10a73c?w=1080"),
                CarouselItem("https://images.unsplash.com/photo-1532581291347-9c39cf10a73c?w=1080"),
            )
            setData(images)
        }

        cityAdapter.submitList(listOf(City(0, "#EF7C8E", "New York City", "sub", "desc"),
            City(1, "#FAE8E0", "Boston", "Boston is a state of mind", "desc"),
            City(2, "#B6E2D3", "Philadelphia", "sub", "desc"),
            City(3, "#D8A7B1", "Washington DC", "sub", "desc"),
            City(4, "#FFF4BD", "New Haven", "sub", "desc"),
            City(5, "#F4B9B8", "Cambridge", "sub", "desc"),
            City(6, "#887BB0", "Providence", "sub", "desc"),
            City(7, "#85D2D0", "Hartford", "sub", "desc"),
            City(8, "#376380", "Worcester", "sub", "desc")))

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun hideViews() {
        val lp = binding.buttonGroup.layoutParams as ViewGroup.MarginLayoutParams
        val groupMargin = lp.bottomMargin
        binding.buttonGroup.animate()
            .translationY((binding.buttonGroup.height + groupMargin).toFloat())
            .setInterpolator(AccelerateInterpolator(1.5F))
            .start()
    }

    private fun showViews() {
        binding.buttonGroup.animate()
            .translationY(0F)
            .setInterpolator(DecelerateInterpolator(1.5F))
            .start()
    }
}
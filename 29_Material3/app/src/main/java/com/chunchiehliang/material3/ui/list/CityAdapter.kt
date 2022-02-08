package com.chunchiehliang.material3.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chunchiehliang.material3.data.City
import com.chunchiehliang.material3.databinding.ItemCityBinding

class CityAdapter(private val listener: CityListener) :
    ListAdapter<City, CityAdapter.CityViewHolder>(CityDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        getItem(position)?.let { user ->
            holder.bind(user, listener)
        }
    }


    class CityViewHolder(private var binding: ItemCityBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(city: City, listener: CityListener) {
            binding.city = city
            binding.listener = listener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): CityViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCityBinding.inflate(layoutInflater, parent, false)
                return CityViewHolder(binding)
            }
        }
    }
}


class CityDiffCallback : DiffUtil.ItemCallback<City>() {
    override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem.cityId == newItem.cityId
    }

    override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem == newItem
    }
}

class CityListener(val listener: (city: City) -> Unit) {
    fun onCityClick(city: City) = listener(city)
}
package com.chunchiehliang.material3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chunchiehliang.material3.databinding.FragmentComponentListBinding


class ComponentListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentComponentListBinding.inflate(layoutInflater)



        return binding.root
    }
}
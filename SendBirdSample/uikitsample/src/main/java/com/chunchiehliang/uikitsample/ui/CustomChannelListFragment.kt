package com.chunchiehliang.uikitsample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sendbird.uikit.fragments.ChannelListFragment

class CustomChannelListFragment : ChannelListFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        setHasOptionsMenu(false)
        return super.onCreateView(inflater, container, savedInstanceState)

    }

}
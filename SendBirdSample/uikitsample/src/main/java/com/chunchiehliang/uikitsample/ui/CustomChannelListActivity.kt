package com.chunchiehliang.uikitsample.ui

import android.content.Intent
import com.sendbird.uikit.activities.ChannelActivity
import com.sendbird.uikit.activities.ChannelListActivity
import com.sendbird.uikit.fragments.ChannelListFragment
import timber.log.Timber


class CustomChannelListActivity : ChannelListActivity() {
    override fun createChannelListFragment(): ChannelListFragment {
        return ChannelListFragment.Builder()
            .setChannelListAdapter(null)
            .setCustomChannelListFragment(CustomChannelListFragment())
            .setGroupChannelListQuery(null)

            .setUseHeader(true)
            .setHeaderTitle("Channel Name")

            .setUseHeaderLeftButton(true)
            .setHeaderLeftButtonListener(null)
//            .setHeaderLeftButtonIconResId(R.drawable.any_icon)

            .setUseHeaderRightButton(true)
            .setHeaderRightButtonListener(null)
//            .setHeaderRightButtonIconResId(R.drawable.any_icon)

            .setItemClickListener { view, position, channel ->
                Timber.d("Test clicked: pos: $position, channel: $channel")
                showCustomChannelActivity(channel.url)
            }
            .setItemLongClickListener(null)

            .build()
    }

    private fun showCustomChannelActivity(channelUrl: String) {
        val intent = ChannelActivity.newIntentFromCustomActivity(
            applicationContext, CustomChannelActivity::class.java, channelUrl)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}
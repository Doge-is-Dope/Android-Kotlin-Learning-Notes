package com.chunchiehliang.uikitsample.ui

import com.chunchiehliang.uikitsample.sendbird.SendbirdUiKitUtils
import com.sendbird.uikit.activities.ChannelActivity
import com.sendbird.uikit.activities.ChannelListActivity
import com.sendbird.uikit.fragments.ChannelListFragment
import timber.log.Timber


class CustomChannelListActivity : ChannelListActivity() {
    override fun createChannelListFragment(): ChannelListFragment {
        return ChannelListFragment.Builder()
            .setUseHeader(true)
            .setItemClickListener { view, i, groupChannel ->
                val intent = ChannelActivity.newIntentFromCustomActivity(
                    this,
                    CustomChannelActivity::class.java, groupChannel.url
                )
                startActivity(intent)
            }
            .build()
    }

    override fun onPause() {
        super.onPause()
        SendbirdUiKitUtils.disconnect {
            Timber.d("test - disconnected")
        }
    }

    override fun onResume() {
        super.onResume()
        SendbirdUiKitUtils.connect(onSuccess = { user, isOffline ->
            Timber.d("test - connected: $user, isOffline: $isOffline")
        }, onFailure = {})
    }
}
package com.chunchiehliang.uikitsample.ui

import com.sendbird.uikit.activities.ChannelActivity
import com.sendbird.uikit.fragments.ChannelFragment


class CustomChannelActivity: ChannelActivity() {
    override fun createChannelFragment(channelUrl: String): ChannelFragment {
        return ChannelFragment.Builder(channelUrl)
            .setCustomChannelFragment(CustomChannelFragment())
            .setUseHeader(true)
            .setUseHeaderLeftButton(true)
            .setUseHeaderRightButton(true)
            .setUseTypingIndicator(true)
//            .setHeaderLeftButtonIconResId(R.drawable.icon_arrow_left)
//            .setHeaderRightButtonIconResId(R.drawable.icon_info)
//            .setInputLeftButtonIconResId(R.drawable.icon_add)
//            .setInputRightButtonIconResId(R.drawable.icon_send)
//            .setInputHint(getString(R.string.sb_text_channel_input_text_hint))
            .setHeaderLeftButtonListener(null)
//            .setHeaderRightButtonListener { v -> showCustomChannelSettingsActivity(channelUrl) }
//            .setMessageListAdapter(CustomMessageListAdapter(useMessageGroupUI))
            .setListItemClickListener(null)
            .setListItemLongClickListener(null)
            .setInputLeftButtonListener {}
            .setMessageListParams(null)
//            .setUseMessageGroupUI()
            .build()
    }
}
package com.chunchiehliang.uikitsample.ui

import com.sendbird.android.UserMessageParams
import com.sendbird.uikit.fragments.ChannelFragment
import timber.log.Timber

class CustomChannelFragment : ChannelFragment() {

    override fun onBeforeSendUserMessage(params: UserMessageParams) {
        super.onBeforeSendUserMessage(params)
        val type = getType()
        Timber.d("type: $type")
        params.apply {
            customType = type
            data = null
            mentionedUserIds = null
            metaArrays = null
            parentMessageId = 0
        }


    }

    private fun getType(): String {
        return activity.takeIf { it is CustomChannelActivity }.let { "ABC" }
    }
}
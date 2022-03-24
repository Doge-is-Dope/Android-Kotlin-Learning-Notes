package com.chunchiehliang.uikitsample.sendbird

import com.sendbird.android.GroupChannel
import com.sendbird.android.GroupChannelTotalUnreadMessageCountParams
import com.sendbird.android.SendBird
import timber.log.Timber

object SendbirdSDKUtils {
    fun getUnreadMsgCount(onSuccess: (Int) -> Unit) {
        SendBird.getTotalUnreadMessageCount(GroupChannelTotalUnreadMessageCountParams()) { totalCount, e ->
            if (e != null) {
                Timber.e("getUnreadMsgCount: $e")
                return@getTotalUnreadMessageCount
            }
            onSuccess(totalCount)
        }
    }

    fun createChannel(
        userIds: List<String>,
        onSuccess: (channel: GroupChannel) -> Unit,
        onFailure: (e: Exception) -> Unit,
    ) {
        GroupChannel.createChannelWithUserIds(userIds, true) { channel, exception ->
            if (exception != null) onFailure(exception)
            onSuccess(channel)
        }
    }
}
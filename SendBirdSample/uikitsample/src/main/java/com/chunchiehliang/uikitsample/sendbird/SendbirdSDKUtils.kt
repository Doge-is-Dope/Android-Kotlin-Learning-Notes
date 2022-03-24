package com.chunchiehliang.uikitsample.sendbird

import com.sendbird.android.GroupChannelTotalUnreadMessageCountParams
import com.sendbird.android.SendBird
import timber.log.Timber

object SendbirdSDKUtils {
    fun getUnreadMsgCount(unreadCallback: (Int) -> Unit) {
        SendBird.getTotalUnreadMessageCount(GroupChannelTotalUnreadMessageCountParams()) { totalCount, e ->
            if (e != null) {
                Timber.e("getUnreadMsgCount: $e")
                return@getTotalUnreadMessageCount
            }
            unreadCallback(totalCount)
        }
    }
}
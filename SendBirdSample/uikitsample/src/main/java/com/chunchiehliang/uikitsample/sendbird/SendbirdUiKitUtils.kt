package com.chunchiehliang.uikitsample.sendbird

import android.content.Context
import com.chunchiehliang.uikitsample.BuildConfig
import com.sendbird.android.*
import com.sendbird.android.handlers.InitResultHandler
import com.sendbird.uikit.SendBirdUIKit
import com.sendbird.uikit.adapter.SendBirdUIKitAdapter
import com.sendbird.uikit.interfaces.UserInfo
import timber.log.Timber

object SendbirdUiKitUtils {
    fun initSendbird(context: Context, onSuccess: () -> Unit) {
        SendBirdUIKit.init(object : SendBirdUIKitAdapter {
            override fun getAppId(): String = BuildConfig.SENDBIRD_APP_ID

            override fun getAccessToken(): String = "4867e748fb7707ed33c985f2e37d8b25bc22c590"

            override fun getUserInfo(): UserInfo = object : UserInfo {
                override fun getUserId(): String = "1"

                override fun getNickname(): String = ""

                override fun getProfileUrl(): String = ""
            }

            override fun getInitResultHandler() = object : InitResultHandler {
                override fun onInitFailed(e: SendBirdException) {
                    Timber.e("onInitFailed")
                }

                override fun onInitSucceed() {
                    Timber.d("onInitSucceed")
                    onSuccess()
                }

                override fun onMigrationStarted() {
                    Timber.d("onMigrationStarted")
                }

            }
        }, context)
    }

    fun connect(
        onSuccess: (user: User, isOffline: Boolean) -> Unit,
        onFailure: (e: Exception) -> Unit,
    ) {
        try {
            SendBirdUIKit.connect { user, e ->
                if (e != null) {
                    if (user != null) {
                        // The user is offline but you can access user information stored in the local cache.
                        Timber.e("Offline: $e")
                        onSuccess(user, true)
                    } else {
                        // The user is offline and you can't access any user information stored in the local cache.
                        Timber.e("Can't retrieve user info $e")
                    }
                } else {
                    onSuccess(user, false)
                }
            }
        } catch (e: Exception) {
            onFailure(e)
        }
    }

    fun disconnect(onSuccess: () -> Unit) {
        SendBirdUIKit.disconnect {
            Timber.d("disconnected")
            onSuccess()
        }
    }
}
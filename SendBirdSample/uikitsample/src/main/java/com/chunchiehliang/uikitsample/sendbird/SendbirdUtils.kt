package com.chunchiehliang.uikitsample.sendbird

import android.content.Context
import com.chunchiehliang.uikitsample.BuildConfig
import com.sendbird.android.SendBird
import com.sendbird.android.SendBirdException
import com.sendbird.android.User
import com.sendbird.android.handlers.InitResultHandler
import com.sendbird.uikit.SendBirdUIKit
import com.sendbird.uikit.adapter.SendBirdUIKitAdapter
import com.sendbird.uikit.interfaces.UserInfo
import timber.log.Timber

object SendbirdUtils {
    fun initSendbird(context: Context) {
        SendBird.init(BuildConfig.SENDBIRD_APP_ID, context, true, object : InitResultHandler {
            override fun onInitFailed(e: SendBirdException) {
                Timber.e("onInitFailed: $e")
            }

            override fun onInitSucceed() {
                Timber.d("onInitSucceed")
            }

            override fun onMigrationStarted() {
                Timber.d("onMigrationStarted")
            }

        })
    }

    fun connect(
        userId: String,
        accessToken: String,
        connectedCallback: (user: User, isOffline: Boolean) -> Unit,
        connectFailedCallback: (e: Exception) -> Unit,
    ) {
        try {
            SendBird.connect(userId, accessToken) { user, e ->
                user?.let {
                    if (e != null) {
                        Timber.d("connected offline")
                        connectedCallback(user, true)
                    } else {
                        Timber.d("connected online")
                        connectedCallback(user, false)
                    }
                }
            }
        } catch (e: Exception) {
            connectFailedCallback(e)
        }
    }

    fun setUSer() {

    }
}
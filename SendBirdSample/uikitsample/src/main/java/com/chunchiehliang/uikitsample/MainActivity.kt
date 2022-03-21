package com.chunchiehliang.uikitsample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.chunchiehliang.uikitsample.BuildConfig.SENDBIRD_APP_ID
import com.chunchiehliang.uikitsample.databinding.ActivityMainBinding
import com.chunchiehliang.uikitsample.ui.CustomChannelListActivity
import com.sendbird.android.SendBirdException
import com.sendbird.android.handlers.InitResultHandler
import com.sendbird.uikit.SendBirdUIKit
import com.sendbird.uikit.adapter.SendBirdUIKitAdapter
import com.sendbird.uikit.interfaces.UserInfo
import timber.log.Timber


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            clearFocus()

            btnToChannelList.setOnClickListener {
                if (editUserId.text.isNullOrBlank()) {
                    inputUserId.isErrorEnabled = true
                    inputUserId.error = "User ID is required"
                } else {
                    clearFocus()
                    initSendbird()
                    startActivity(Intent(applicationContext, CustomChannelListActivity::class.java))
                }
            }

            btnToChat.setOnClickListener {
                if (editUserId.text.isNullOrBlank()) {
                    inputUserId.isErrorEnabled = true
                    inputUserId.error = "User ID is required"
                } else {
                    clearFocus()
                    initSendbird()
                }
            }
        }
    }


    private fun ActivityMainBinding.clearFocus() {
        editUserId.clearFocus()
        root.requestFocus()
        inputUserId.isErrorEnabled = false
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editUserId.windowToken, 0)
    }

    private fun ActivityMainBinding.initSendbird() {
        SendBirdUIKit.init(object : SendBirdUIKitAdapter {
            override fun getAppId(): String = SENDBIRD_APP_ID

            override fun getAccessToken(): String = ""

            override fun getUserInfo(): UserInfo {
                return object : UserInfo {
                    override fun getUserId(): String = editUserId.text.toString()

                    override fun getNickname(): String = "Cat"

                    override fun getProfileUrl(): String = "https://i.imgur.com/nzM9MUe.png"

                }
            }

            override fun getInitResultHandler(): InitResultHandler {
                return object : InitResultHandler {
                    override fun onInitFailed(e: SendBirdException) {
                        Timber.e("onInitFailed: $e")
                    }

                    override fun onInitSucceed() {
                        Timber.d("onInitSucceed")
                    }

                    override fun onMigrationStarted() {
                        Timber.d("onMigrationStarted")
                    }
                }
            }

        }, applicationContext)
    }
}
package com.chunchiehliang.sendbirdsample.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chunchiehliang.sendbirdsample.BuildConfig
import com.chunchiehliang.sendbirdsample.databinding.ActivityMainBinding
import com.chunchiehliang.sendbirdsample.model.User
import com.chunchiehliang.sendbirdsample.util.showToastMsg
import com.sendbird.android.SendBird
import com.sendbird.android.SendBirdException
import com.sendbird.android.handlers.InitResultHandler
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        initSendbird()

        val user = User(12, "Tzuyu", "https://i.imgur.com/1mMTNWE.jpg")

        binding.btnConnect.setOnClickListener {
            connectToSendBird(user)
        }
    }

    private fun initSendbird() {
        SendBird.init(BuildConfig.SENDBIRD_APP_ID, this, true, object : InitResultHandler {
            override fun onInitSucceed() {
                Timber.d("init succeed")
            }

            override fun onInitFailed(e: SendBirdException) {
                Timber.e("init failed")
            }

            override fun onMigrationStarted() {
                Timber.d("migration started")
            }

        })
    }

    private fun connectToSendBird(user: User) {
        SendBird.connect(user.userId.toString()) { existedUser, exception ->
            if (exception != null)
                showToastMsg(this, "Connection failed: ${exception.message}")
            else {
                showToastMsg(this, "Connected: $user")
                updateUserInfo(user)
            }
        }
    }

    private fun updateUserInfo(user: User) {
        SendBird.updateCurrentUserInfo(user.name, user.avatarUrl) { exception ->
            if (exception != null) {
                showToastMsg(this, "Update failed: ${exception.message}")
            } else {
                showToastMsg(this, "Updated")
                val intent = Intent(this, ChannelListActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
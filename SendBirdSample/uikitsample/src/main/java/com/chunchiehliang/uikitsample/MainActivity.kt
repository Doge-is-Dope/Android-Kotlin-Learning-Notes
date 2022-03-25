package com.chunchiehliang.uikitsample

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chunchiehliang.uikitsample.databinding.ActivityMainBinding
import com.chunchiehliang.uikitsample.sendbird.SendbirdSDKUtils
import com.chunchiehliang.uikitsample.sendbird.SendbirdUiKitUtils
import com.chunchiehliang.uikitsample.ui.CustomChannelActivity
import com.chunchiehliang.uikitsample.ui.CustomChannelListActivity
import com.sendbird.uikit.activities.ChannelActivity
import timber.log.Timber


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnConnect.setOnClickListener {
                SendbirdUiKitUtils.connect(
                    onSuccess = { user, isOffline -> showToastMsg("Connected user ${user.nickname}, isOffline: $isOffline") },
                    onFailure = { e -> showToastMsg("Connect failed: ${e.message}") }
                )
            }

            btnToChannelList.setOnClickListener {
                SendbirdUiKitUtils.connect(
                    onSuccess = { user, isOffline ->
                        showToastMsg("Connected user ID:  ${user.userId}, isOffline: $isOffline")
                        startActivity(
                            Intent(
                                applicationContext,
                                CustomChannelListActivity::class.java
                            )
                        )
                    },
                    onFailure = { e -> showToastMsg("Connect failed: ${e.message}") }
                )
            }

            btnCreateChannel.setOnClickListener {
                SendbirdUiKitUtils.connect(
                    onSuccess = { user, isOffline ->
                        showToastMsg("Connected user ID:  ${user.userId}, isOffline: $isOffline")

                        SendbirdSDKUtils.createChannel(
                            userIds = listOf("1", "7"),
                            onSuccess = {
                                Timber.d("created successfully: $it")
                                val intent = ChannelActivity.newIntentFromCustomActivity(
                                    applicationContext,
                                    CustomChannelActivity::class.java,
                                    it.url
                                )
                                startActivity(intent)
                            }, onFailure = {
                                showToastMsg("Failed to create channel: $it")
                            })
                    },
                    onFailure = { e -> showToastMsg("Connect failed: ${e.message}") }
                )
            }

            btnGetUnreadCount.setOnClickListener {
                SendbirdSDKUtils.getUnreadMsgCount {
                    showToastMsg("Unread: $it")
                }
            }
        }
    }

    private fun showToastMsg(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}
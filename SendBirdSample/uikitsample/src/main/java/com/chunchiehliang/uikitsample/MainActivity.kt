package com.chunchiehliang.uikitsample

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.chunchiehliang.uikitsample.databinding.ActivityMainBinding
import com.chunchiehliang.uikitsample.sendbird.SendbirdSDKUtils
import com.chunchiehliang.uikitsample.sendbird.SendbirdUiKitUtils
import com.chunchiehliang.uikitsample.ui.CustomChannelActivity
import com.chunchiehliang.uikitsample.ui.CustomChannelListActivity
import com.sendbird.uikit.SendBirdUIKit
import com.sendbird.uikit.activities.ChannelActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnToChannelList.setOnClickListener {
                SendbirdUiKitUtils.initSendbird(applicationContext) {
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
            }

            btnCreateChannel.setOnClickListener {
                SendbirdUiKitUtils.initSendbird(applicationContext) {
                    SendbirdUiKitUtils.connect(
                        onSuccess = { user, isOffline ->
                            showToastMsg("Connected user ID:  ${user.userId}, isOffline: $isOffline")
                            // Create channel
                            SendbirdSDKUtils.createChannel(
                                userIds = listOf("1", "7"),
                                onSuccess = {
                                    Timber.d("created successfully: $it")
                                    startActivity(
                                        Intent(
                                            applicationContext,
                                            CustomChannelListActivity::class.java
                                        ).apply {
                                            putExtra("DIRECT_TO_CHANNEL", it.url)
                                        }
                                    )
//                                    val intent = ChannelActivity.newIntentFromCustomActivity(
//                                        applicationContext,
//                                        CustomChannelActivity::class.java,
//                                        it.url
//                                    )
//                                    startActivity(intent)
                                }, onFailure = {
                                    showToastMsg("Failed to create channel: $it")
                                })
                        },
                        onFailure = { e -> showToastMsg("Connect failed: ${e.message}") }
                    )
                }
            }

            btnGetUnreadCount.setOnClickListener {
                SendbirdSDKUtils.getUnreadMsgCount {
                    showToastMsg("Unread: $it")
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Timber.d("Test - onResume")
        SendbirdUiKitUtils.disconnect { Timber.d("Test - Sendbird disconnected") }
    }

    private fun showToastMsg(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}
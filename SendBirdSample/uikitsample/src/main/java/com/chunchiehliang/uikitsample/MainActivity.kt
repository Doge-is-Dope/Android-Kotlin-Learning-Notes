package com.chunchiehliang.uikitsample

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chunchiehliang.uikitsample.databinding.ActivityMainBinding
import com.chunchiehliang.uikitsample.sendbird.SendbirdSDKUtils
import com.chunchiehliang.uikitsample.sendbird.SendbirdUiKitUtils
import com.sendbird.uikit.activities.ChannelListActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnConnect.setOnClickListener {
                SendbirdUiKitUtils.connect(
                    connectedCallback = { user, isOffline -> showToastMsg("Connected user ${user.nickname}, isOffline: $isOffline") },
                    connectFailedCallback = { e -> showToastMsg("Connect failed: ${e.message}") }
                )
            }

            btnToChannelList.setOnClickListener {
                SendbirdUiKitUtils.connect(
                    connectedCallback = { user, isOffline ->
                        showToastMsg("Connected user ID:  ${user.userId}, isOffline: $isOffline")
                        startActivity(Intent(applicationContext,
                            ChannelListActivity::class.java))
                    },
                    connectFailedCallback = { e -> showToastMsg("Connect failed: ${e.message}") }
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
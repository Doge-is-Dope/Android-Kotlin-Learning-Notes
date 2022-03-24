package com.chunchiehliang.uikitsample

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chunchiehliang.uikitsample.databinding.ActivityMainBinding
import com.chunchiehliang.uikitsample.sendbird.SendbirdUtils.connect
import com.chunchiehliang.uikitsample.sendbird.SendbirdUtils.initSendbird
import com.chunchiehliang.uikitsample.ui.CustomChannelListActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnInitSendbird.setOnClickListener {
                initSendbird(applicationContext)
            }

            btnConnect.setOnClickListener {
                connect(
                    userId = "1",
                    accessToken = "4867e748fb7707ed33c985f2e37d8b25bc22c590",
                    connectedCallback = { user, isOffline -> showToastMsg("Connected user ${user.nickname}, isOffline: $isOffline") },
                    connectFailedCallback = { e -> showToastMsg("Connect failed: ${e.message}") }
                )
            }

            btnToChannelList.setOnClickListener {
                startActivity(Intent(applicationContext, CustomChannelListActivity::class.java))
            }

            btnToChat.setOnClickListener {
            }
        }
    }

    private fun showToastMsg(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}
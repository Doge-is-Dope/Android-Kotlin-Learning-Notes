package com.chunchiehliang.uikitsample.ui

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.chunchiehliang.uikitsample.sendbird.SendbirdUiKitUtils
import com.sendbird.uikit.activities.ChannelActivity
import com.sendbird.uikit.activities.ChannelListActivity
import com.sendbird.uikit.fragments.ChannelListFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber


class CustomChannelListActivity : ChannelListActivity() {

    val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val isDirectlyClose = result.data?.getBooleanExtra("CLOSE_DIRECTLY", false)
                Timber.d("is direct close: $isDirectlyClose")
                lifecycleScope.launch {
                    delay(2000)
                    if (isDirectlyClose == true) finish()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.getStringExtra("DIRECT_TO_CHANNEL")?.let {
            val channelIntent = ChannelActivity.newIntentFromCustomActivity(
                applicationContext, CustomChannelActivity::class.java, it
            )
            startForResult.launch(channelIntent)
//            startActivity(channelIntent)
        }
    }

    override fun createChannelListFragment(): ChannelListFragment {
        return ChannelListFragment.Builder()
            .setUseHeader(true)
            .setItemClickListener { view, i, groupChannel ->
                val intent = ChannelActivity.newIntentFromCustomActivity(
                    this,
                    CustomChannelActivity::class.java, groupChannel.url
                )
                startActivity(intent)
            }
            .build()
    }

    override fun onResume() {
        super.onResume()
        SendbirdUiKitUtils.connect(onSuccess = { user, isOffline ->
            Timber.d("test - connected: $user, isOffline: $isOffline")
        }, onFailure = {})
    }
}
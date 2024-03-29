package com.chunchiehliang.uikitsample.ui

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import com.chunchiehliang.uikitsample.MainActivity
import com.chunchiehliang.uikitsample.sendbird.SendbirdUiKitUtils
import com.sendbird.uikit.activities.ChannelActivity
import com.sendbird.uikit.fragments.ChannelFragment
import timber.log.Timber


class CustomChannelActivity : ChannelActivity() {

    override fun createChannelFragment(channelUrl: String): ChannelFragment {
        return ChannelFragment.Builder(channelUrl)
            .setCustomChannelFragment(CustomChannelFragment())
            .setUseHeader(true)
            .setUseTypingIndicator(true)

            // Header right button
            .setUseHeaderRightButton(false)
//            .setHeaderRightButtonIconResId(R.drawable.icon_info)
//            .setHeaderRightButtonListener { v -> showCustomChannelSettingsActivity(channelUrl) }

            // Header left button
            .setUseHeaderLeftButton(true)
//            .setInputLeftButtonIconResId(R.drawable.icon_add)
//            .setHeaderLeftButtonIconResId(R.drawable.icon_arrow_left)
            .setHeaderLeftButtonListener {
                setResult(RESULT_OK, Intent().putExtra("CLOSE_DIRECTLY", true))
                finish()
            }

            // Input
//            .setInputRightButtonIconResId(R.drawable.icon_send)
//            .setInputHint(getString(R.string.sb_text_channel_input_text_hint))
//            .setInputLeftButtonListener { _ -> showMessageTypeDialog() }


//            .setMessageListAdapter(CustomMessageListAdapter(useMessageGroupUI))
//            .setListItemClickListener(null)
//            .setListItemLongClickListener { _, _, _, _ -> }
//
//
//            .setMessageListParams(null)
//            .setUseMessageGroupUI()
            .build()
    }

//    private fun showMessageTypeDialog() {
//        val builder = AlertDialog.Builder(this)
//        builder.setTitle("Pick message type")
//            .setMultiChoiceItems(arrayOf<String>(com.sendbird.uikit.customsample.consts.StringSet.highlight),
//                booleanArrayOf(customChannelFragment.getCustomMessageType()
//                    .equals(CustomMessageType.HIGHLIGHT))
//            ) { dialog: DialogInterface?, which: Int, isChecked: Boolean ->
//                val type: CustomMessageType =
//                    if (isChecked) CustomMessageType.HIGHLIGHT else CustomMessageType.NONE
//                customChannelFragment.setCustomMessageType(type)
//            }
//            .create()
//            .show()
//    }

    override fun onResume() {
        super.onResume()
        SendbirdUiKitUtils.connect(onSuccess = { user, isOffline ->
            Timber.d("test - connected: $user, isOffline: $isOffline")
        }, onFailure = {})
    }
}
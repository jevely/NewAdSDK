package com.tb.adsdk.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.tb.adsdk.tool.AdBeanTool
import com.tb.adsdk.tool.AdShowTool
import com.tb.adsdk.tool.Logger

/**
 * Home键监听
 */
class HomeButtonReceiver : BroadcastReceiver() {

    private val SYSTEM_DIALOG_REASON_KEY = "reason"
    private val SYSTEM_DIALOG_REASON_HOME_KEY = "homekey"

    override fun onReceive(context: Context, intent: Intent) {

        val action = intent.action

        if (action == Intent.ACTION_CLOSE_SYSTEM_DIALOGS) {
            val reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY)
            if (SYSTEM_DIALOG_REASON_HOME_KEY == reason) {//Home键点击
                Logger.d("click home key")
                //展示广告
                val adBean = AdBeanTool.getInstance().beanMap["outside"] ?: return
                AdShowTool.getInstance().showAd(adBean)
            }
        }
    }
}
package com.tb.adsdk

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

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
                //展示广告

            }
        }
    }
}
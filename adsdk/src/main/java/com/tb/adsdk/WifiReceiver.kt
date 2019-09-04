package com.tb.adsdk

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.WINDOW_SERVICE
import android.content.Intent
import android.text.TextUtils
import android.view.Display.STATE_ON
import android.view.WindowManager

/**
 * Wifi广播
 */
class WifiReceiver : BroadcastReceiver() {

    private val ANDROID_NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE"
    private var mIsFirst = true

    override fun onReceive(context: Context, intent: Intent) {

        if (TextUtils.equals(intent.action, ANDROID_NET_CHANGE_ACTION)) {

            if (mIsFirst) {
                mIsFirst = false
                return
            }

            val windowManager = context.getSystemService(WINDOW_SERVICE) as WindowManager
            val display = windowManager.defaultDisplay

            if (display.state == STATE_ON) {
                return
            }

            //展示广告

        }
    }

}
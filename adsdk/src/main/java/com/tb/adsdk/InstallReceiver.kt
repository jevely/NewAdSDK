package com.tb.adsdk

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.text.TextUtils

/**
 * 安装监听
 */
class InstallReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        //Google安装的会返回referrer
        val referrer = intent.getStringExtra("referrer")

        if (TextUtils.isEmpty(referrer)) {
            if (context != null) {
                //referrer为null
            }
        } else {
            if (context != null) {
                //referrer不为null
            }
        }
    }

}
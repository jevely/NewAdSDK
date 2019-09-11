package com.tb.adsdk.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.WINDOW_SERVICE
import android.content.Intent
import android.view.Display.STATE_ON
import android.view.WindowManager
import com.tb.adsdk.tool.AdBeanTool
import com.tb.adsdk.tool.AdShowTool
import com.tb.adsdk.tool.Logger

/**
 * 电池广播
 */
class BatteryReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val action = intent.action
        if (action === Intent.ACTION_BATTERY_OKAY
            || action === Intent.ACTION_POWER_CONNECTED
            || action === Intent.ACTION_POWER_DISCONNECTED) {
            Logger.d("battery action")
            //充电完成 插入USB 拔出USB
            val windowManager = context.getSystemService(WINDOW_SERVICE) as WindowManager
            val display = windowManager.defaultDisplay

            if (display.state != STATE_ON) {
                return
            }
            //展示广告
            val adBean = AdBeanTool.getInstance().beanMap["outside"] ?: return
            AdShowTool.getInstance().showAd(adBean)
        }
    }

}
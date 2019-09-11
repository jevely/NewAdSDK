package com.tb.adsdk.service

import android.app.IntentService
import android.content.Intent
import com.tb.adsdk.tool.ActivityLifeTool
import com.tb.adsdk.tool.AdBeanTool
import com.tb.adsdk.tool.AdShowTool

class InnerService : IntentService("inner") {

    override fun onHandleIntent(intent: Intent?) {
        innerInit()
    }

    private fun innerInit() {
        try {
            val adBean = AdBeanTool.getInstance().beanMap["inner"] ?: return

            //判断是否在应用内
            if (ActivityLifeTool.getInstance().outApp()) {
                return
            }

            //展示广告
            AdShowTool.getInstance().showAd(adBean)

            Thread.sleep(45 * 1000)
            startService(Intent(this, InnerService::class.java))
        } catch (e: Exception) {
            e.printStackTrace()
            Thread.sleep(45 * 1000)
            startService(Intent(this, InnerService::class.java))
        }
    }

}
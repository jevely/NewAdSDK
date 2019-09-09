package com.tb.adsdk.tool

import android.content.Context
import com.tb.adsdk.ad.AdmobInitHelper
import com.tb.adsdk.ad.AudienceNetworkInitializeHelper
import com.tb.adsdk.initReceiver
import com.tb.adsdk.logProcessName

object AdManager {

    fun applicationInit(context: Context) {

        //只初始化一次 限主进程
        val processName = logProcessName(context)
        if (null != processName) {
            if (processName != context.packageName) {
                return
            }
        }

        SharedPreTool.getInstance().init(context)

        if (SharedPreTool.getInstance().getLong(SharedPreTool.INSTALL_TIME) == 0L) {
            SharedPreTool.getInstance()
                .putLong(SharedPreTool.INSTALL_TIME, System.currentTimeMillis())
        }

        SharedPreTool.getInstance()
            .putLong(SharedPreTool.START_APP_TIME, System.currentTimeMillis())

        initReceiver(context)
        AudienceNetworkInitializeHelper.initialize(context)
        AdmobInitHelper.initAdmobSDK(context)
        AdShowTool.getInstance().init(context)
    }

    fun activityInit() {

    }

    var last_inner_show_time = 0L
    var in_app = true
    fun innerInit() {
        val adBean = AdBeanTool.getInstance().beanMap["inner"] ?: return

        if (!adBean.enable) {
            return
        }

        //广告延迟展示时间
        if (System.currentTimeMillis() - SharedPreTool.getInstance().getLong(SharedPreTool.START_APP_TIME) < adBean.enableTime) {
            return
        }

        //间隔时间
        if (System.currentTimeMillis() - last_inner_show_time < adBean.enableGapTime) {
            return
        }

        //判断是否在应用内
        if (ActivityLifeTool.getInstance().outApp()) {
            return
        }



    }

}
package com.tb.adsdk.tool

import android.content.Context
import android.content.Intent
import com.tb.adsdk.ad.AdmobInitHelper
import com.tb.adsdk.ad.AudienceNetworkInitializeHelper
import com.tb.adsdk.initReceiver
import com.tb.adsdk.logProcessName
import com.tb.adsdk.service.InnerService

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

    fun activityInit(context: Context) {
        context.startService(Intent(context, InnerService::class.java))
    }


}
package com.tb.newadsdk

import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks2
import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import com.tb.adsdk.ad.AdmobInitHelper
import com.tb.adsdk.ad.AudienceNetworkInitializeHelper
import com.tb.adsdk.initReceiver
import com.tb.adsdk.tool.ActivityLifeTool
import com.tb.adsdk.tool.AdManager
import com.tb.adsdk.tool.AdShowTool
import com.tb.adsdk.tool.Logger
import java.io.BufferedReader
import java.io.InputStreamReader

class MyApplication : Application(), ComponentCallbacks2 {

    override fun onCreate() {
        super.onCreate()
        AdManager.applicationInit(applicationContext)
        registerActivityCallBack()
    }

    private fun registerActivityCallBack() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
                Logger.d("${p0.toString()} callback -> onActivitySaveInstanceState")
            }

            override fun onActivityPaused(p0: Activity) {
                Logger.d("${p0.toString()} callback -> onActivityPaused")
                ActivityLifeTool.getInstance().outActivity(p0.toString())
            }

            override fun onActivityStarted(p0: Activity) {
                Logger.d("${p0.toString()} callback -> onActivityStarted")
            }

            override fun onActivityDestroyed(p0: Activity) {
                Logger.d("${p0.toString()} callback -> onActivityDestroyed")
            }

            override fun onActivityStopped(p0: Activity) {
                Logger.d("${p0.toString()} callback -> onActivityStopped")
            }

            override fun onActivityCreated(p0: Activity, p1: Bundle?) {
                Logger.d("${p0.toString()} callback -> onActivityCreated")
            }

            override fun onActivityResumed(p0: Activity) {
                Logger.d("${p0.toString()} callback -> onActivityResumed")
                ActivityLifeTool.getInstance().inActivity(p0.toString())
            }
        })
    }

}
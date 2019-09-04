package com.tb.newadsdk

import android.app.Application
import com.tb.adsdk.initReceiver

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        initReceiver(applicationContext)
    }
}
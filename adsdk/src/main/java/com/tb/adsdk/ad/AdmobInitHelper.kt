package com.tb.adsdk.ad

import android.content.Context
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds

object AdmobInitHelper {

    fun initAdmobSDK(context: Context) {
//        MobileAds.initialize(context, "ca-app-pub-3940256099942544~3347511713")

        MobileAds.initialize(context) {

        }
    }

    fun addTestDvices(testID: String) {
        val request = AdRequest.Builder()
            .addTestDevice(testID)  // An example device ID
            .build()
    }
}
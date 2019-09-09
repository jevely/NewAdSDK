package com.tb.adsdk.ad

import android.content.Context
import com.facebook.ads.AdSettings
import com.facebook.ads.AudienceNetworkAds
import com.tb.adsdk.BuildConfig.DEBUG
import com.tb.adsdk.tool.Logger

class AudienceNetworkInitializeHelper : AudienceNetworkAds.InitListener {


    companion object {
        fun initialize(context: Context) {
            if (!AudienceNetworkAds.isInitialized(context)) {
                if (DEBUG) {
                    AdSettings.turnOnSDKDebugger(context)
                }

                AudienceNetworkAds
                    .buildInitSettings(context)
                    .withInitListener(AudienceNetworkInitializeHelper())
                    .initialize()
            }
        }
    }

    override fun onInitialized(result: AudienceNetworkAds.InitResult) {
        Logger.e(result.message)
    }

}
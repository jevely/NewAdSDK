package com.tb.adsdk.tool

import android.content.Context
import android.text.TextUtils
import android.view.View
import com.tb.adsdk.ad.AdmobAd
import com.tb.adsdk.ad.FacebookAd
import com.tb.adsdk.content.AdBean
import com.tb.adsdk.content.NomalAdBean
import com.tb.adsdk.content.ParentBean
import java.text.SimpleDateFormat
import java.util.*

class AdShowTool private constructor() {

    companion object {

        private var adShowTool: AdShowTool? = null

        fun getInstance(): AdShowTool {
            if (adShowTool == null) {
                @Synchronized
                if (adShowTool == null) {
                    adShowTool = AdShowTool()
                }

            }
            return adShowTool as AdShowTool
        }

        fun detroy() {
            adShowTool = null
        }
    }

    private val facebookad: FacebookAd = FacebookAd()
    private val admobAd: AdmobAd = AdmobAd()

    private lateinit var mContext: Context

    fun init(context: Context) {
        mContext = context
    }

    private lateinit var outShowAdView: View
    //外插
    fun showOutAd() {

        val adBean = AdBeanTool.getInstance().beanMap["outside"] ?: return

        if (!adBean.enable) {
            return
        }

        val waitTime =
            System.currentTimeMillis() - SharedPreTool.getInstance().getLong(SharedPreTool.INSTALL_TIME)
        if (waitTime < adBean.enableTime) {
            return
        }

        checkNextDay(adBean)

        if (adBean.requestCount > adBean.maximum) {
            return
        }

        val adItemBean = adBean.item
        val parentBean = adItemBean?.adGroups?.get(adBean.requestCount % adBean.items.size)
        when (adItemBean?.adType) {
            "NORMAL" -> {
                showNomalAd(parentBean!!)
            }
        }

    }

    fun showNomalAd(parentBean: ParentBean) {
        val nomalAdBean = parentBean as NomalAdBean
        when (nomalAdBean.platFormName) {
            "Facebook" -> {
                //展示facebook广告
                showFacebookAd(nomalAdBean)
            }
        }
    }

    fun showFacebookAd(nomalAdBean: NomalAdBean) {
        when (nomalAdBean.adTypeName) {
            "Native" -> {
                facebookad.nativeAd(mContext, nomalAdBean.adLocation!!)
            }
            "Interstital" -> {
                facebookad.InterstitalAd(mContext, nomalAdBean.adLocation!!)
            }
        }
    }

    fun checkNextDay(adBean: AdBean) {
        val last_show_ad_time = SharedPreTool.getInstance().getLong(SharedPreTool.LAST_SHOW_AD_TIME)
        val simpleDateFormat = SimpleDateFormat("dd")
        val last_date = Date(last_show_ad_time)
        val last_day = simpleDateFormat.format(last_date)
        val now_date = Date(System.currentTimeMillis())
        val now_day = simpleDateFormat.format(now_date)
        if (!TextUtils.equals(last_day, now_day)) {
            adBean.requestCount = 0
        }
    }

}
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

    val facebookad: FacebookAd = FacebookAd()
    val admobAd: AdmobAd = AdmobAd()

    private lateinit var mContext: Context

    fun init(context: Context) {
        mContext = context
    }

    fun showPopupAd() {
        val adBean = AdBeanTool.getInstance().beanMap["popup"] ?: return
        showAd(adBean)
    }

    var last_inner_show_time = 0L

    fun showAd(adBean: AdBean) {

        if (!adBean.enable) {
            return
        }

//        val waitTime =
//            System.currentTimeMillis() - SharedPreTool.getInstance().getLong(SharedPreTool.INSTALL_TIME)
//        if (waitTime < adBean.enableTime) {
//            return
//        }

        //广告延迟展示时间
        if (System.currentTimeMillis() - SharedPreTool.getInstance().getLong(SharedPreTool.START_APP_TIME) < adBean.enableTime) {
            return
        }

        //间隔时间
        if (System.currentTimeMillis() - last_inner_show_time < adBean.enableGapTime) {
            return
        }

        checkNextDay(adBean)

        if (adBean.requestCount > adBean.maximum) {
            return
        }

        Logger.d("show ${adBean.apName} ad")

        val adItemBean = adBean.item
        val parentBean = adItemBean?.adGroups?.get(adBean.requestCount % adBean.items.size)
        when (adItemBean?.adType) {
            "NORMAL" -> {
                showNomalAd(parentBean!!, adBean)
            }
        }
        last_inner_show_time = System.currentTimeMillis()
    }

    private fun showNomalAd(parentBean: ParentBean, adBean: AdBean) {
        val nomalAdBean = parentBean as NomalAdBean
        when (nomalAdBean.platFormName) {
            "Facebook" -> {
                //展示facebook广告
                showFacebookAd(nomalAdBean, adBean)
            }
            "Admob" -> {
                showAdmobAd(nomalAdBean, adBean)
            }
        }
    }

    private fun showFacebookAd(nomalAdBean: NomalAdBean, adBean: AdBean) {

        if (TextUtils.isEmpty(nomalAdBean.adLocation))
            return

        when (nomalAdBean.adTypeName) {
            "Native" -> {
                facebookad.nativeAd(mContext, nomalAdBean.adLocation!!, adBean)
            }
            "Interstital" -> {
                facebookad.InterstitalAd(mContext, nomalAdBean.adLocation!!)
            }
        }
    }

    private fun showAdmobAd(nomalAdBean: NomalAdBean, adBean: AdBean) {
        when (nomalAdBean.adTypeName) {
            "Native" -> {
                admobAd.nativeAd(mContext, nomalAdBean.adLocation!!, adBean, false, false)
            }
            "Interstital" -> {
                admobAd.interstitialAd(mContext, nomalAdBean.adLocation!!)
            }
        }
    }

    private fun checkNextDay(adBean: AdBean) {
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
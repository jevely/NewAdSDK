package com.tb.adsdk.ad

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.facebook.ads.*
import com.tb.adsdk.tool.Logger
import com.tb.adsdk.R
import com.tb.adsdk.ShowAdActivity
import com.tb.adsdk.content.AdBean


class FacebookAd {

    lateinit var preShowFacebookAdView: View

    //插屏广告
    private var interstitialAd: InterstitialAd? = null

    fun InterstitalAd(context: Context, adId: String) {
        interstitialAd = InterstitialAd(context, adId)

        interstitialAd?.setAdListener(object : InterstitialAdListener {
            override fun onInterstitialDisplayed(ad: Ad) {
                // Interstitial ad displayed callback
                Logger.d("Interstitial ad displayed.")
            }

            override fun onInterstitialDismissed(ad: Ad) {
                // Interstitial dismissed callback
                Logger.d("Interstitial ad dismissed.")
            }

            override fun onError(ad: Ad, adError: AdError) {
                // Ad error callback
                Logger.d("Interstitial ad failed to load: " + adError.errorMessage)
            }

            override fun onAdLoaded(ad: Ad) {
                // Interstitial ad is loaded and ready to be displayed
                Logger.d("Interstitial ad is loaded and ready to be displayed!")
                if (interstitialAd == null || !interstitialAd!!.isAdLoaded) {
                    return
                }
                // 检查是否无效
                if (interstitialAd!!.isAdInvalidated) {
                    return
                }
                // Show the ad
                interstitialAd?.show()
            }

            override fun onAdClicked(ad: Ad) {
                // Ad clicked callback
                Logger.d("Interstitial ad clicked!")
            }

            override fun onLoggingImpression(ad: Ad) {
                // Ad impression logged callback
                Logger.d("Interstitial ad impression logged!")
            }
        })

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown
        interstitialAd?.loadAd()
    }

    fun destroyIntersititialAd() {
        if (interstitialAd != null) {
            interstitialAd?.destroy()
        }
    }

    //banner广告
    private var adView: AdView? = null

    fun bannerAd(context: Context, parent: ViewGroup, adId: String) {
        adView = AdView(context, adId, AdSize.BANNER_HEIGHT_50)
        // Add the ad view to your activity layout
        parent.addView(adView)

        adView?.setAdListener(object : AdListener {
            override fun onError(ad: Ad, adError: AdError) {
                // Ad error callback
                Logger.e("Error: ${adError.errorMessage}")
            }

            override fun onAdLoaded(ad: Ad) {
                // Ad loaded callback

            }

            override fun onAdClicked(ad: Ad) {
                // Ad clicked callback
            }

            override fun onLoggingImpression(ad: Ad) {
                // Ad impression logged callback
            }
        })
        // Request an ad
        adView?.loadAd()
    }

    fun destroyBannerAd() {
        if (adView != null) {
            adView?.destroy()
        }
    }

    //原生广告
    private var nativeAd: NativeAd? = null

    fun nativeAd(context: Context, adId: String, adBean: AdBean) {
        nativeAd = NativeAd(context, adId)
        nativeAd?.setAdListener(object : NativeAdListener {
            override fun onMediaDownloaded(ad: Ad) {
                // Native ad finished downloading all assets
                Logger.d("Native ad finished downloading all assets.")
            }

            override fun onError(ad: Ad, adError: AdError) {
                // Native ad failed to load
                Logger.d("Native ad failed to load: " + adError.errorMessage)
            }

            override fun onAdLoaded(ad: Ad) {
                // Native ad is loaded and ready to be displayed
                if (nativeAd == null || nativeAd != ad) {
                    return
                }
                Logger.d("Native ad is loaded and ready to be displayed!")
                if (!nativeAd!!.isAdLoaded) {
                    return
                }
                // Check if ad is already expired or invalidated, and do not show ad if that is the case. You will not get paid to show an invalidated ad.
                if (nativeAd!!.isAdInvalidated) {
                    return
                }

                inflateAd(context, nativeAd!!, adBean)
            }

            override fun onAdClicked(ad: Ad) {
                // Native ad clicked
                Logger.d("Native ad clicked!")
            }

            override fun onLoggingImpression(ad: Ad) {
                // Native ad impression
                Logger.d("Native ad impression logged!")
            }
        })

        // Request an ad
        nativeAd?.loadAd()
    }

    private fun inflateAd(context: Context, nativeAd: NativeAd, adBean: AdBean) {
        try {
            nativeAd.unregisterView()

            val inflater = LayoutInflater.from(context)
            val parentView = inflater.inflate(R.layout.facebook_native_layout,null)
            val adView = parentView.findViewById<NativeAdLayout>(R.id.native_ad_container)

            // Add the AdOptionsView
            val adChoicesContainer = adView.findViewById<LinearLayout>(R.id.ad_choices_container)
            val adOptionsView = AdOptionsView(context, nativeAd, adView)
            adChoicesContainer.removeAllViews()
            adChoicesContainer.addView(adOptionsView, 0)

            // Create native UI using the ad metadata.
            val nativeAdIcon = adView.findViewById<MediaView>(R.id.native_ad_icon)
            val nativeAdTitle = adView.findViewById<TextView>(R.id.native_ad_title)
            val nativeAdMedia = adView.findViewById<MediaView>(R.id.native_ad_media)
            val nativeAdSocialContext = adView.findViewById<TextView>(R.id.native_ad_social_context)
            val nativeAdBody = adView.findViewById<TextView>(R.id.native_ad_body)
            val sponsoredLabel = adView.findViewById<TextView>(R.id.native_ad_sponsored_label)
            val nativeAdCallToAction = adView.findViewById<Button>(R.id.native_ad_call_to_action)


            Logger.d("nativeAd.adBodyText = ${nativeAd.adBodyText}")
            Logger.d("nativeAd.advertiserName = ${nativeAd.advertiserName}")
            Logger.d("nativeAd.adCallToAction = ${nativeAd.adCallToAction}")
            Logger.d("nativeAd.adHeadline = ${nativeAd.adHeadline}")
            Logger.d("nativeAd.adSocialContext = ${nativeAd.adSocialContext}")
            Logger.d("nativeAd.adTranslation = ${nativeAd.adTranslation}")
            Logger.d("nativeAd.sponsoredTranslation = ${nativeAd.sponsoredTranslation}")

            val title = nativeAd.advertiserName
            if (!TextUtils.isEmpty(title))
                nativeAdTitle.text = title

            val content1 = nativeAd.adBodyText
            if (!TextUtils.isEmpty(content1))
                nativeAdBody.text = content1

            val content2 = nativeAd.adSocialContext
            if (!TextUtils.isEmpty(content2))
                nativeAdSocialContext.text = content2

            if (nativeAd.hasCallToAction()) {
                nativeAdCallToAction.visibility = View.VISIBLE
                val action = nativeAd.adCallToAction
                if (!TextUtils.isEmpty(action))
                    nativeAdCallToAction.text = action
            } else {
                nativeAdCallToAction.visibility = View.INVISIBLE
            }

            val more = nativeAd.sponsoredTranslation
            if (!TextUtils.isEmpty(more))
                sponsoredLabel.text = more

            // Create a list of clickable views
            val clickableViews = mutableListOf<View>()
            if (adBean.naIconClick) {
                clickableViews.add(nativeAdIcon)
            }

            if (adBean.naTitleClick) {
                clickableViews.add(nativeAdTitle)
            }

//            if (adBean.naDescClick) {
//                clickableViews.add(nativeAdTitle)
//            }

            clickableViews.add(nativeAdCallToAction)

            // Register the Title and CTA button to listen for clicks.
            //必须在主线程调用
            nativeAd.registerViewForInteraction(
                adView,
                nativeAdMedia,
                nativeAdIcon,
                clickableViews
            )

            NativeAdBase.NativeComponentTag.tagView(nativeAdIcon, NativeAdBase.NativeComponentTag.AD_ICON)

            preShowFacebookAdView = adView
            //跳转activity
            val intent = Intent(context, ShowAdActivity::class.java)
            intent.putExtra("ad", "fb")
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //原生banner广告
    fun nativeBannerAd() {

    }

}
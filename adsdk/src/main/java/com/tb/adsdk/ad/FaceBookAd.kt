package com.tb.adsdk.ad

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.facebook.ads.*
import com.tb.adsdk.tool.Logger
import com.tb.adsdk.R


class FaceBookAd {

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

    fun nativeAd(context: Context, adId: String) {
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
                if(!nativeAd!!.isAdLoaded) {
                    return
                }
                // Check if ad is already expired or invalidated, and do not show ad if that is the case. You will not get paid to show an invalidated ad.
                if(nativeAd!!.isAdInvalidated) {
                    return
                }

                inflateAd(context,nativeAd!!)
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

    private fun inflateAd(context: Context, nativeAd: NativeAd) {

        nativeAd.unregisterView()

        val parentView = LayoutInflater.from(context).inflate(R.layout.facebook_parent_layout, null)
        // Add the Ad view into the ad container.
        val nativeAdLayout = parentView.findViewById<NativeAdLayout>(R.id.native_ad_container)
        val inflater = LayoutInflater.from(context)
        // Inflate the Ad view.  The layout referenced should be the one you created in the last step.
        val adView = inflater.inflate(R.layout.facebook_native_layout, nativeAdLayout, false) as LinearLayout
        nativeAdLayout.addView(adView)

        // Add the AdOptionsView
        val adChoicesContainer = adView.findViewById<LinearLayout>(R.id.ad_choices_container)
        val adOptionsView = AdOptionsView(context, nativeAd, nativeAdLayout)
        adChoicesContainer.removeAllViews()
        adChoicesContainer.addView(adOptionsView, 0)

        // Create native UI using the ad metadata.
        val nativeAdIcon = adView.findViewById<AdIconView>(R.id.native_ad_icon)
        val nativeAdTitle = adView.findViewById<TextView>(R.id.native_ad_title)
        val nativeAdMedia = adView.findViewById<MediaView>(R.id.native_ad_media)
        val nativeAdSocialContext = adView.findViewById<TextView>(R.id.native_ad_social_context)
        val nativeAdBody = adView.findViewById<TextView>(R.id.native_ad_body)
        val sponsoredLabel = adView.findViewById<TextView>(R.id.native_ad_sponsored_label)
        val nativeAdCallToAction = adView.findViewById<Button>(R.id.native_ad_call_to_action)

        // Set the Text.
        nativeAdTitle.setText(nativeAd.advertiserName)
        nativeAdBody.setText(nativeAd.adBodyText)
        nativeAdSocialContext.setText(nativeAd.adSocialContext)
        nativeAdCallToAction.setVisibility(if (nativeAd.hasCallToAction()) View.VISIBLE else View.INVISIBLE)
        nativeAdCallToAction.setText(nativeAd.adCallToAction)
        sponsoredLabel.setText(nativeAd.sponsoredTranslation)

        // Create a list of clickable views
        val clickableViews = mutableListOf<View>()
        clickableViews.add(nativeAdTitle)
        clickableViews.add(nativeAdCallToAction)

        // Register the Title and CTA button to listen for clicks.
        //必须在主线程调用
        nativeAd.registerViewForInteraction(
            adView,
            nativeAdMedia,
            nativeAdIcon,
            clickableViews
        )
    }

    //原生banner广告
    fun nativeBannerAd(){

    }

}
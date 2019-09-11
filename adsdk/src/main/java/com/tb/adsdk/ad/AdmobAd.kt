package com.tb.adsdk.ad

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.android.gms.ads.*
import com.google.android.gms.ads.AdRequest.*
import com.google.android.gms.ads.formats.NativeAdOptions
import com.google.android.gms.ads.formats.UnifiedNativeAd
import com.google.android.gms.ads.formats.UnifiedNativeAdView
import com.tb.adsdk.R
import com.tb.adsdk.tool.Logger
import com.google.android.ads.nativetemplates.TemplateView
import com.google.android.ads.nativetemplates.NativeTemplateStyle
import com.google.android.gms.ads.formats.MediaView
import com.tb.adsdk.ShowAdActivity
import com.tb.adsdk.content.AdBean
import com.tb.adsdk.logProcessName

/**
 * 横幅广告	ca-app-pub-3940256099942544/6300978111
 * 插页式广告	ca-app-pub-3940256099942544/1033173712
 * 插页式视频广告	ca-app-pub-3940256099942544/8691691433
 * 激励视频广告	ca-app-pub-3940256099942544/5224354917
 * 原生高级广告	ca-app-pub-3940256099942544/2247696110
 * 原生高级视频广告	ca-app-pub-3940256099942544/1044960115
 */
class AdmobAd {

    //插屏广告
    private lateinit var mInterstitialAd: InterstitialAd

    fun interstitialAd(context: Context, adId: String) {
        mInterstitialAd = InterstitialAd(context)
        mInterstitialAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"

        mInterstitialAd.adListener = object : AdListener() {
            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                if (mInterstitialAd.isLoaded) {
                    mInterstitialAd.show()
                } else {
                    Logger.d("The interstitial wasn't loaded yet.")
                }
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                // Code to be executed when an ad request fails.
                when (errorCode) {
                    ERROR_CODE_INTERNAL_ERROR -> Logger.e("内部出现问题")
                    ERROR_CODE_INVALID_REQUEST -> Logger.e("广告请求无效,ID可能不正确")
                    ERROR_CODE_NETWORK_ERROR -> Logger.e("由于网络连接问题，广告请求失败。")
                    ERROR_CODE_NO_FILL -> Logger.e("广告请求成功，但由于缺少广告资源，未返回广告。")
                }
            }

            override fun onAdOpened() {
                // Code to be executed when the ad is displayed.
                Logger.e("广告加载完成")
            }

            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            override fun onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            override fun onAdClosed() {
                // Code to be executed when the interstitial ad is closed.

                //可以在这里加载下一个广告
//                mInterstitialAd.loadAd(AdRequest.Builder().build())
            }
        }

        mInterstitialAd.loadAd(AdRequest.Builder().build())
    }

    /**
     * banner广告
     * 320x50	Banner	Phones and Tablets	BANNER
     *320x100	Large Banner	Phones and Tablets	LARGE_BANNER
     *300x250	IAB Medium Rectangle	Phones and Tablets	MEDIUM_RECTANGLE
     *468x60	IAB Full-Size Banner	Tablets	FULL_BANNER
     *728x90	IAB Leaderboard	Tablets	LEADERBOARD
     *Screen width x 32|50|90	Smart Banner	Phones and Tablets	SMART_BANNER
     */
    fun bannerAd(contxt: Context, parent: ViewGroup) {
        val adView = AdView(contxt)
        adView.adSize = AdSize.BANNER
        adView.adUnitId = "ca-app-pub-3940256099942544/6300978111"

        adView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                // Code to be executed when an ad request fails.
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            override fun onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        }

        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        parent.addView(adView)
    }

    var preAdmobShowAdView: View? = null
    //原生广告
    lateinit var adLoader: AdLoader

    fun nativeAd(
        context: Context,
        adId: String,
        adBean: AdBean,
        isDefaultLayout: Boolean,
        isSmallLayout: Boolean
    ) {
        adLoader = AdLoader.Builder(context, "ca-app-pub-3940256099942544/2247696110")
            .forUnifiedNativeAd { ad: UnifiedNativeAd ->
                // Show the ad.
                if (adLoader.isLoading) {
                    // The AdLoader is still loading ads.
                    // Expect more adLoaded or onAdFailedToLoad callbacks.
                } else {
                    // The AdLoader has finished loading ads.
                    if (isDefaultLayout) {
                        if (isSmallLayout) {
                            loadDefaultNativeLayout(context, ad)
                        } else {
                            loadDefaultNativeLayout2(context, ad)
                        }
                    } else {
                        loadAdmobNativeLayout(context, ad, adBean)
                    }
                }
            }
            .withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(errorCode: Int) {
                    // Handle the failure by logging, altering the UI, and so on.
                }
            })
            .withNativeAdOptions(
                NativeAdOptions.Builder()
                    // Methods in the NativeAdOptions.Builder class can be
                    // used here to specify individual options settings.
                    .build()
            )
            .withAdListener(object : AdListener() {
                override fun onAdLeftApplication() {
                    super.onAdLeftApplication()
                }

                override fun onAdClicked() {
                    super.onAdClicked()
                }

                override fun onAdFailedToLoad(p0: Int) {
                    super.onAdFailedToLoad(p0)
                }

                override fun onAdClosed() {
                    super.onAdClosed()
                }

                override fun onAdOpened() {
                    super.onAdOpened()
                }
            }).build()

        adLoader.loadAd(AdRequest.Builder().build())
//        adLoader.loadAds(AdRequest.Builder().build(), 3)//max is load 5 ads
    }

    private fun loadAdmobNativeLayout(context: Context, ad: UnifiedNativeAd, adBean: AdBean) {

        val parentView = LayoutInflater.from(context).inflate(R.layout.admob_native_layout, null)

        val adView = parentView.findViewById<UnifiedNativeAdView>(R.id.unifiednativeadview)
        val icon = parentView.findViewById<ImageView>(R.id.icon)
        val content1 = parentView.findViewById<TextView>(R.id.content1)
        val rating_bar = parentView.findViewById<RatingBar>(R.id.rating_bar)
        val content2 = parentView.findViewById<TextView>(R.id.content2)
        val title = parentView.findViewById<TextView>(R.id.title)
//        val media_view = parentView.findViewById<MediaView>(R.id.media_view)
        val cta = parentView.findViewById<Button>(R.id.cta)
        val big_image = parentView.findViewById<ImageView>(R.id.big_image)

        rating_bar.visibility = View.GONE

        title.text = ad.advertiser
        if (adBean.naTitleClick)
            adView.advertiserView = title

        content1.text = ad.headline
        content2.text = ad.body
        if (adBean.naDescClick) {
            adView.headlineView = content1
            adView.bodyView = content2
        }

        cta.text = ad.callToAction
        adView.callToActionView = cta

        val adIcon = ad.icon
        if (adIcon != null) {
            icon.setImageDrawable(adIcon.drawable)
            if (adBean.naIconClick)
                adView.iconView = icon
        }

        val images = ad.images
        if (images.isNotEmpty()) {
            val image = images[0]
            big_image.setImageDrawable(image.drawable)
            if (adBean.naRbClick)
                adView.imageView = big_image
        }

//        adView.mediaView = media_view

        adView.setNativeAd(ad)

        preAdmobShowAdView = adView
        Logger.d("adView = $adView")
        Logger.d("preAdmobShowAdView = $preAdmobShowAdView")
        logProcessName(context)
        goToShow(context)
    }

    private fun loadDefaultNativeLayout(
        context: Context,
        unifiedNativeAd: UnifiedNativeAd
    ) {
        val styles = NativeTemplateStyle.Builder()
            .withMainBackgroundColor(ColorDrawable(Color.parseColor("#ffffff"))).build()
        val parentView =
            LayoutInflater.from(context).inflate(R.layout.admob_default_native_small_layout, null)
        val template = parentView.findViewById<TemplateView>(R.id.my_template_small)
        template.setStyles(styles)
        template.setNativeAd(unifiedNativeAd)
        preAdmobShowAdView = parentView
        goToShow(context)
    }

    private fun loadDefaultNativeLayout2(
        context: Context,
        unifiedNativeAd: UnifiedNativeAd
    ) {
        val styles = NativeTemplateStyle.Builder()
            .withMainBackgroundColor(ColorDrawable(Color.parseColor("#000000"))).build()
        val parentView =
            LayoutInflater.from(context).inflate(R.layout.admob_default_native_nomal_layout, null)
        val template = parentView.findViewById<TemplateView>(R.id.my_template_nomal)
        template.setStyles(styles)
        template.setNativeAd(unifiedNativeAd)
        preAdmobShowAdView = parentView
        goToShow(context)
    }

    fun goToShow(context: Context) {
        val intent = Intent(context, ShowAdActivity::class.java)
        intent.putExtra("ad", "admob")
        context.startActivity(intent)
    }

}
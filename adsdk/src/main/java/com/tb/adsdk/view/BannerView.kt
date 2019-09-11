package com.tb.adsdk.view

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.tb.adsdk.tool.AdShowTool

class BannerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    init {
        AdShowTool.getInstance().admobAd.bannerAd(context, this@BannerView)
    }

}
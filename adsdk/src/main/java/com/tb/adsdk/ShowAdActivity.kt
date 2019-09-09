package com.tb.adsdk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout
import com.tb.adsdk.tool.AdShowTool
import com.tb.adsdk.tool.Logger

class ShowAdActivity : AppCompatActivity() {

    private lateinit var ad_show_re: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_ad)
        ad_show_re = findViewById(R.id.ad_show_re)
        val intent = intent
        val ad = intent.getStringExtra("ad")
        logProcessName(this)
        Logger.d("view = ${AdShowTool.getInstance().admobAd.preAdmobShowAdView}")
        when (ad) {
            "fb" -> {
                ad_show_re.addView(AdShowTool.getInstance().facebookad.preShowFacebookAdView)
            }
            "admob" -> {
                ad_show_re.addView(AdShowTool.getInstance().admobAd.preAdmobShowAdView)
            }
        }
    }
}

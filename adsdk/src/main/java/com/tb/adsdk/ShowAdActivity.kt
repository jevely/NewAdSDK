package com.tb.adsdk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout

class ShowAdActivity : AppCompatActivity() {

    private lateinit var ad_show_re : RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_ad)
        ad_show_re = findViewById(R.id.ad_show_re)
    }
}

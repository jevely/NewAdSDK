package com.tb.adsdk.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.WINDOW_SERVICE
import android.content.Intent
import android.text.TextUtils
import android.view.Display.STATE_ON
import android.view.WindowManager
import com.tb.adsdk.tool.Logger
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.core.content.ContextCompat.getSystemService
import com.tb.adsdk.tool.AdBeanTool
import com.tb.adsdk.tool.AdShowTool


/**
 * Wifi广播
 */
class NetReceiver : BroadcastReceiver() {

    var firstTime = true

    override fun onReceive(context: Context, intent: Intent) {

        if (ConnectivityManager.CONNECTIVITY_ACTION == intent.action) {

            if(firstTime){
                firstTime = false
                Logger.d("net first")
                return
            }

            val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (manager != null) {
                val activeNetwork = manager.activeNetworkInfo
                if (activeNetwork != null) { // connected to the internet
                    if (activeNetwork.isConnected) {
                        if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                            Logger.d("当前WiFi连接可用 ")
                            showAd()
                        } else if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                            Logger.d("当前移动网络连接可用 ")
                            showAd()
                        }
                    } else {
                        Logger.d("当前没有网络连接，请确保你已经打开网络 ")
                    }
                } else {
                    Logger.d("当前没有网络连接，请确保你已经打开网络 ")
                }
            }
        }
    }

    private var inBroadTime = 0L

    fun showAd() {
        if (System.currentTimeMillis() - inBroadTime > 1000 * 60) {
            inBroadTime = System.currentTimeMillis()
            Logger.d("show net ad")
            val adBean = AdBeanTool.getInstance().beanMap["outside"] ?: return
            AdShowTool.getInstance().showAd(adBean)
        }
    }

}
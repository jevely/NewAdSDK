package com.tb.adsdk.receiver

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.view.Display
import android.view.WindowManager
import com.tb.adsdk.tool.Logger

/**
 * 蓝牙广播
 */
class BluetoothReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        if (action == BluetoothAdapter.ACTION_STATE_CHANGED) {
            val state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)
            if (state == BluetoothAdapter.STATE_OFF || state == BluetoothAdapter.STATE_ON) {
                Logger.d("bluetooth action")
                //蓝牙关闭 蓝牙打开
                val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
                val display = windowManager.defaultDisplay

                if (display.state != Display.STATE_ON) {
                    return
                }
                //展示广告
            }
        }
    }

}
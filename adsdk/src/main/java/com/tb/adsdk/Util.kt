package com.tb.adsdk

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import com.tb.adsdk.receiver.*

fun initReceiver(context: Context){
    //锁屏广播
    var filter = IntentFilter()
    filter.addAction(Intent.ACTION_SCREEN_ON)
    filter.addAction(Intent.ACTION_SCREEN_OFF)
    filter.addAction(Intent.ACTION_USER_PRESENT)
    context.registerReceiver(LockScreenReceiver(), filter)

    //WIFI广播
    filter = IntentFilter()
    filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
    context.registerReceiver(NetReceiver(), filter)

    //电池广播
    filter = IntentFilter()
    filter.addAction(Intent.ACTION_BATTERY_OKAY)
    filter.addAction(Intent.ACTION_POWER_CONNECTED)
    filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
    context.registerReceiver(BatteryReceiver(), filter)

    //蓝牙广播
    filter = IntentFilter()
    filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED)
    filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED)
    filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED)
    context.registerReceiver(BluetoothReceiver(), filter)

    //HOME键监听
    filter = IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
    context.registerReceiver(HomeButtonReceiver(), filter)
}
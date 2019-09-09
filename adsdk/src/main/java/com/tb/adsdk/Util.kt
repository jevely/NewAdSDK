package com.tb.adsdk

import android.app.ActivityManager
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import com.tb.adsdk.receiver.*
import com.google.android.gms.common.internal.ConnectionErrorMessages.getAppName
import com.tb.adsdk.tool.Logger


fun initReceiver(context: Context) {
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

fun logProcessName(context: Context): String? {
    val pid = android.os.Process.myPid()
    val name = getAppName(context, pid)
    Logger.d("当前进程位：$name")
    return name
}

fun getAppName(context: Context, pid: Int): String? {
    val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val list = activityManager.getRunningAppProcesses()
    val i = list.iterator()
    while (i.hasNext()) {
        val info = i.next() as ActivityManager.RunningAppProcessInfo
        try {
            if (info.pid == pid) {
                // 根据进程的信息获取当前进程的名字
                return info.processName;
            }
        } catch (e: Exception) {
            e.printStackTrace();
        }
    }
    // 没有匹配的项，返回为null
    return null
}
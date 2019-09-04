package com.tb.adsdk

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioManager

/**
 * 锁屏广播
 */
class LockScreenReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val mAction = intent.action
        if (mAction == Intent.ACTION_USER_PRESENT) {//解锁
            //如果在打电话或者播放音乐就不弹框
            val am = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
            when (am.mode) {
                AudioManager.MODE_RINGTONE, AudioManager.MODE_IN_CALL, AudioManager.MODE_IN_COMMUNICATION -> return
            }
            //展示广告

        }
    }

}
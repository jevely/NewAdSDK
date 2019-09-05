package com.tb.adsdk.tool

import android.util.Log

object Logger {
    private val _TAG = "LJW"
    private val _shouldShow = true

    fun d(content: String) {
        if (_shouldShow)
            Log.d(_TAG, content)
    }

    fun e(content: String) {
        if (_shouldShow)
            Log.e(_TAG, content)
    }
}
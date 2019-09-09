package com.tb.adsdk.tool

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class SharedPreTool private constructor() {

    companion object {

        private var sharedPreTool: SharedPreTool? = null

        fun getInstance(): SharedPreTool {
            if (sharedPreTool == null) {
                @Synchronized
                if (sharedPreTool == null) {
                    sharedPreTool = SharedPreTool()
                }

            }
            return sharedPreTool as SharedPreTool
        }

        fun detroy() {
            sharedPreTool = null
        }

        val INSTALL_TIME = "install_time"
        val LAST_SHOW_AD_TIME = "last_show_ad_time"
        val START_APP_TIME = "start_app_time"
    }

    private lateinit var sharedPre: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    fun init(context: Context) {
        sharedPre = context.getSharedPreferences("ad_share", MODE_PRIVATE)
        editor = sharedPre.edit()
    }

    fun putString(key: String, value: String) {
        editor.putString(key, value)
        editor.commit()
    }

    fun getString(key: String): String {
        return sharedPre.getString(key, "") ?: return ""
    }

    fun putInt(key: String, value: Int) {
        editor.putInt(key, value)
        editor.commit()
    }

    fun getInt(key: String): Int {
        return sharedPre.getInt(key, 0)
    }

    fun putBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value)
        editor.commit()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPre.getBoolean(key, false)
    }

    fun putLong(key: String, value: Long) {
        editor.putLong(key, value)
        editor.commit()
    }

    fun getLong(key: String): Long {
        return sharedPre.getLong(key, 0L)
    }

}
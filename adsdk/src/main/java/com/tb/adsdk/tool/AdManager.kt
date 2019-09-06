package com.tb.adsdk.tool

object AdManager {

    fun applicationInit() {
        if (SharedPreTool.getInstance().getLong(SharedPreTool.INSTALL_TIME) == 0L) {
            SharedPreTool.getInstance()
                .putLong(SharedPreTool.INSTALL_TIME, System.currentTimeMillis())
        }
    }

    fun activityInit() {

    }

}
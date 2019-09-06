package com.tb.adsdk.content

class AdBean {

    var apGroup: IntArray? = null
    var apGroupMaximum: IntArray? = null
    var apGroupRequest: IntArray? = null

    var requestCount: Int = 0
    var maximum: Int = 99

    var enable: Boolean = false
    var enableTime: Long = 0L
    var enableGapTime: Long = 0L
    var extraInsert: Int = 1

    var id: String = ""
    var apName: String = ""

    var naCbDelayTime: Long = 0L

    var naRbClick: Boolean = true
    var naRbDelayTime: Long = 0L

    var naInstallClick: Boolean = true
    var naTitleClick: Boolean = true
    var naIconClick: Boolean = true
    var naDescClick: Boolean = true

    var items: MutableList<AdItemBean> = mutableListOf()
    var item: AdItemBean ?= null

}
package com.tb.adsdk.content

class CPSAdBean : ParentBean() {
    var country: String? = null
    var cpiUrl: String? = null
    var pUrl: String? = null
    var pkg: String? = null
    var timeList: MutableList<CPSTimeBean> = mutableListOf()
}
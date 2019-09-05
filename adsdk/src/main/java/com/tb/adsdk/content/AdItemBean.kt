package com.tb.adsdk.content

class AdItemBean {
    var adType : String ?= null
    var groupName: String? = null
    var type: Int? = null
    var adGroups: MutableList<ParentBean> = mutableListOf()
}
package com.tb.adsdk.tool

import com.tb.adsdk.content.AdBean

class AdBeanTool private constructor() {

    companion object {

        private var adBeanTool: AdBeanTool? = null

        fun getInstance(): AdBeanTool {
            if (adBeanTool == null) {
                @Synchronized
                if (adBeanTool == null) {
                    adBeanTool = AdBeanTool()
                }

            }
            return adBeanTool as AdBeanTool
        }

        fun detroy() {
            adBeanTool = null
        }
    }

    val beanMap = mutableMapOf<String, AdBean>()


}
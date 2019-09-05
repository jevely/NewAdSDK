package com.tb.adsdk.net

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

class NetTool private constructor() {

    companion object {

        private var netTool: NetTool? = null

        fun getInstance(): NetTool {
            if (netTool == null) {
                @Synchronized
                if (netTool == null) {
                    netTool = NetTool()
                }
            }
            return netTool as NetTool
        }

        fun detroy() {
            netTool = null
        }
    }

    val okHttpClient = OkHttpClient()

    fun post(url: String, key: String, value: String, callback: ResultCallBack) {
        val requestMap = mutableMapOf<String, String>()
        requestMap[key] = value
        post(url, requestMap, callback)
    }

    fun post(url: String, requestMap: MutableMap<String, String>, callback: ResultCallBack) {

        val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()

        val json = JSONObject()
        requestMap.forEach {
            json.put(it.key, it.value)
        }

        val requestBody = json.toString().toRequestBody(mediaType)

        val request = Request
            .Builder()
            .url(url)
            .post(requestBody)
            .build()

        val newCall = okHttpClient.newCall(request)

        newCall.enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                callback.error(e.message!!)
            }

            override fun onResponse(call: Call, response: Response) {

                val body = response.body
                val code = response.code
                if (code == 200 && body != null) {
                    callback.success(body.string())
                } else {
                    callback.error("request fail code = $code")
                }
            }
        })
    }


}
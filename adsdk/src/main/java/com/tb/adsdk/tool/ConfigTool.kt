package com.tb.adsdk.tool

import android.content.Context
import android.text.TextUtils
import com.tb.adsdk.content.*
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

object ConfigTool {

    fun getLocalConfig(context: Context) {
        val config = readStringFromAssets(context)
        analyticConfig(config)
    }

    fun getLocalConfig(config : String) {
        analyticConfig(config)
    }

    fun getNetAllConfig() {

    }

    fun getNetADConfig() {

    }

    private fun readStringFromAssets(context: Context): String {
        val assetManager = context.applicationContext.assets

        var inputStream: InputStream? = null
        var isr: InputStreamReader? = null
        var br: BufferedReader? = null

        val sb = StringBuffer()
        try {
            inputStream = assetManager.open(context.packageName + "_v52.json")
            isr = InputStreamReader(inputStream)
            br = BufferedReader(isr)

            sb.append(br.readLine())
            var line: String? = null
            while (({ line = br.readLine();line }) != null) {
                sb.append("\n" + line!!)
            }

            br.close()
            isr.close()
            inputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                br?.close()
                isr?.close()
                inputStream?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }

        return sb.toString()
    }


    var s_parse_config_status = true

    @Synchronized
    private fun analyticConfig(config: String) {
        s_parse_config_status = true
        try {
            val jObj = JSONObject(config)
            val jHeader = jObj.optJSONObject("header")
            if (jHeader != null) {
//                try {
//                    //版本号
//                    val config_version = jHeader.optLong("version")
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }

                val h5AdBean = HeaderBean()
                try {
                    h5AdBean.appIcon = jHeader.optInt("appIcon") == 1
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                try {
                    h5AdBean.appIconTime = jHeader.optLong("appIconTime") * 1000
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                try {
                    h5AdBean.H5CloseIcon = jHeader.optInt("H5CloseIcon") == 1
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                try {
                    h5AdBean.H5CanGoback = jHeader.optInt("H5CanGoback") == 1
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                try {
                    h5AdBean.LocalPushEnable = jHeader.optInt("LocalPushEnable") == 1
                } catch (e: Exception) {
                    e.printStackTrace()
                }

//                try {
//                    val yunReferrer = jHeader.optString("Referrer")
//                    val ReferrerType = jHeader.optInt("useReferrer", 0)
//                    //判断是否位自然用户
//                setFreeUser(context, yunReferrer, ReferrerType)
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
            }

            val jsonArr = jObj.optJSONArray("ad") ?: return
            var jsonObj: JSONObject
            var adBean: AdBean
            for (i in 0 until jsonArr.length()) {
                jsonObj = jsonArr.optJSONObject(i)
                adBean = AdBean()
                try {
                    adBean.id = jsonObj.optInt("id").toString()
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                try {
                    adBean.apName = jsonObj.optString("apName")
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                try {
                    adBean.enable = jsonObj.optInt("enable") == 1
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                try {
                    //广告延迟展示时间
                    adBean.enableTime = jsonObj.optInt("enableTime") * 60 * 1000L
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                try {
                    //广告展示间隔时间
                    adBean.enableGapTime = jsonObj.optInt("enableGapTime") * 1000L
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                try {
                    adBean.extraInsert = jsonObj.optInt("extraInsert")
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                try {
                    //底部back按钮延迟时间
                    adBean.naCbDelayTime = jsonObj.optInt("naCbDelayTime") * 1000L
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                try {
                    //右上角关闭按钮
                    adBean.naRbClick = jsonObj.optInt("naRbClick") == 1
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                try {
                    //右上角关闭按钮现实延迟时间
                    adBean.naRbDelayTime = jsonObj.optInt("naRbDelayTime") * 1000L
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                try {
                    adBean.naInstallClick = jsonObj.optInt("naInstallClick") == 1
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                try {
                    adBean.naTitleClick = jsonObj.optInt("naTitleClick") == 1
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                try {
                    adBean.naIconClick = jsonObj.optInt("naIconClick") == 1
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                try {
                    adBean.naDescClick = jsonObj.optInt("naDescClick") == 1
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                try {
                    val idx = jsonObj.optString("apGroup")
                    if (!TextUtils.isEmpty(idx)) {
                        val groupIdx =
                            idx.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                        val groupIdx1 = IntArray(groupIdx.size)
                        for (j in groupIdx.indices) {
                            groupIdx1[j] = Integer.valueOf(groupIdx[j])
                        }
                        adBean.apGroup = groupIdx1
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                try {
                    val idx = jsonObj.optString("apGroupMaximum")
                    if (!TextUtils.isEmpty(idx)) {
                        val groupIdx =
                            idx.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                        val groupIdx1 = IntArray(groupIdx.size)
                        for (j in groupIdx.indices) {
                            groupIdx1[j] = Integer.valueOf(groupIdx[j])
                        }
                        adBean.apGroupMaximum = groupIdx1
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                try {
                    val idx = jsonObj.optString("apGroupRequest")
                    if (!TextUtils.isEmpty(idx)) {
                        val groupIdx =
                            idx.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                        val groupIdx1 = IntArray(groupIdx.size)
                        for (j in groupIdx.indices) {
                            groupIdx1[j] = Integer.valueOf(groupIdx[j])
                        }
                        adBean.apGroupRequest = groupIdx1
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                try {
                    val groups = jsonObj.optJSONArray("groups")
                    if (groups != null && groups.length() > 0) {
                        for (j in 0 until groups.length()) {
                            val adItem = AdItemBean()
                            val item: JSONObject? = groups.optJSONObject(j)
                            if (item != null) {
                                val type = item.optInt("type")
                                adItem.type = type
                                if (type == 4 && adBean.extraInsert == 3) { //CPS
                                    adItem.adType = "CPS"
                                    val cpsGroups = item.optJSONArray("cps")
                                    for (z in 0 until cpsGroups!!.length()) {
                                        val cpsItem = cpsGroups.optJSONObject(z)
                                        val tscpsBean = CPSAdBean()
                                        getCpsAdInfo(tscpsBean, cpsItem)
                                        adItem.adGroups.add(tscpsBean)
                                    }
                                } else if (type == 1) {//PUSH广告
                                    adItem.adType = "PUSH"
                                    val contents = item.optJSONArray("contents")
                                    if (contents != null && contents.length() > 0) {
                                        for (k in 0 until contents.length()) {
                                            val pushAdBean = PushAdBean()
                                            pushAdBean.adType = "H5"
                                            pushAdBean.content = contents.getString(k)
                                            adItem.adGroups.add(pushAdBean)
                                        }
                                    }
                                } else if (type == 3) {//Normal广告
                                    adItem.adType = "NORMAL"
                                    for (k in 0..2) {
                                        val nomalAdBean = NomalAdBean()
                                        var state = "state"
                                        if (k != 0) {
                                            state += k + 1
                                        }
                                        if (item.optInt(state) == 0) {
                                            continue
                                        }
                                        getNomalAdInfo(k, nomalAdBean, item)
                                        adItem.adGroups.add(nomalAdBean)
                                    }
                                }
                                adItem.groupName = item.optString("groupName")
                            }
                            adBean.items.add(adItem)
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                AdBeanTool.getInstance().beanMap[adBean.id] = adBean
            }
        } catch (e: JSONException) {
            e.printStackTrace()
            s_parse_config_status = false
        }
        s_parse_config_status = false
    }

    private fun getCpsAdInfo(tscpsBean: CPSAdBean, cpsItem: JSONObject) {
        tscpsBean.country = cpsItem.optString("country")
        tscpsBean.pUrl = cpsItem.optString("pUrl")
        tscpsBean.cpiUrl = cpsItem.optString("cpiUrl")
        tscpsBean.pkg = cpsItem.optString("pkg")
        val cpsTimes = cpsItem.optJSONArray("appCpsTimeList")
        for (x in 0 until cpsTimes!!.length()) {
            val cpsTimeItem = cpsTimes.optJSONObject(x)
            val tscpsTime = CPSTimeBean()
            tscpsTime.startTime = cpsTimeItem.optString("startTime")
            tscpsTime.endTime = cpsTimeItem.optString("endTime")
            //展示次数
            tscpsTime.number = cpsTimeItem.optInt("number")
            tscpsTime.appCpsId = cpsTimeItem.optString("appCpsId")
            tscpsBean.timeList.add(tscpsTime)
        }
    }

    private fun getNomalAdInfo(count: Int, nomalAdBean: NomalAdBean, groupItem: JSONObject) {
        var adLocation = "adLocation"
        var adTypeName = "adTypeName"
        var platformName = "platformName"

        if (count != 0) {
            adLocation += count + 1
            adTypeName += count + 1
            platformName += count + 1
        }

        nomalAdBean.adLocation = groupItem.optString(adLocation)
        nomalAdBean.platFormName = groupItem.optString(platformName)

        if (TextUtils.equals(nomalAdBean.platFormName, "h5games")) {
            nomalAdBean.adTypeName = ("H5")
        } else {
            nomalAdBean.adTypeName = groupItem.optString(adTypeName)
        }
    }

}
package com.tb.newadsdk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import com.tb.adsdk.ad.AdmobAd
import com.tb.adsdk.ad.AdmobInitHelper
import com.tb.adsdk.tool.AdBeanTool
import com.tb.adsdk.tool.ConfigTool
import com.tb.adsdk.tool.Logger

class MainActivity : AppCompatActivity() {

    val _Config: String = "{\n" +
            "\t\"ad\": [{\n" +
            "\t\t\"apGroup\": \"1\",\n" +
            "\t\t\"apGroupMaximum\": \"999\",\n" +
            "\t\t\"apGroupRequest\": \"1\",\n" +
            "\t\t\"apName\": \"cps\",\n" +
            "\t\t\"enable\": 1,\n" +
            "\t\t\"extraInsert\": 3,\n" +
            "\t\t\"groups\": [{\n" +
            "\t\t\t\"cps\": [{\n" +
            "\t\t\t\t\"appCpsTimeList\": [{\n" +
            "\t\t\t\t\t\"appCpsId\": \"098b59d2da2a4444b0642c10a0d7b943\",\n" +
            "\t\t\t\t\t\"endTime\": \"23:01\",\n" +
            "\t\t\t\t\t\"id\": 380,\n" +
            "\t\t\t\t\t\"number\": 1,\n" +
            "\t\t\t\t\t\"startTime\": \"07:01\"\n" +
            "\t\t\t\t}],\n" +
            "\t\t\t\t\"country\": \"0\",\n" +
            "\t\t\t\t\"cpiUrl\": \"\",\n" +
            "\t\t\t\t\"id\": \"098b59d2da2a4444b0642c10a0d7b943\",\n" +
            "\t\t\t\t\"pName\": \"速卖通\",\n" +
            "\t\t\t\t\"pUrl\": \"http://click.tecdo-ec.com/aff_c?offer_id=91313160&affiliate_id=8209\",\n" +
            "\t\t\t\t\"pkg\": \"com.alibaba.aliexpresshd\"\n" +
            "\t\t\t}, {\n" +
            "\t\t\t\t\"appCpsTimeList\": [{\n" +
            "\t\t\t\t\t\"appCpsId\": \"53ccf91208cc461ea8ebe4597e96d8c3\",\n" +
            "\t\t\t\t\t\"endTime\": \"23:00\",\n" +
            "\t\t\t\t\t\"id\": 413,\n" +
            "\t\t\t\t\t\"number\": 1,\n" +
            "\t\t\t\t\t\"startTime\": \"07:00\"\n" +
            "\t\t\t\t}],\n" +
            "\t\t\t\t\"country\": \"PHL\",\n" +
            "\t\t\t\t\"cpiUrl\": \"\",\n" +
            "\t\t\t\t\"id\": \"53ccf91208cc461ea8ebe4597e96d8c3\",\n" +
            "\t\t\t\t\"pName\": \"lazada-PH\",\n" +
            "\t\t\t\t\"pUrl\": \"http://click.tecdo-ec.com/aff_c?offer_id=105877423&affiliate_id=8209\",\n" +
            "\t\t\t\t\"pkg\": \"com.lazada.android\"\n" +
            "\t\t\t}, {\n" +
            "\t\t\t\t\"appCpsTimeList\": [{\n" +
            "\t\t\t\t\t\"appCpsId\": \"8eab3d60dc854be5a0cbb0b8da3a9594\",\n" +
            "\t\t\t\t\t\"endTime\": \"23:10\",\n" +
            "\t\t\t\t\t\"id\": 391,\n" +
            "\t\t\t\t\t\"number\": 1,\n" +
            "\t\t\t\t\t\"startTime\": \"07:00\"\n" +
            "\t\t\t\t}],\n" +
            "\t\t\t\t\"country\": \"IDN\",\n" +
            "\t\t\t\t\"cpiUrl\": \"\",\n" +
            "\t\t\t\t\"id\": \"8eab3d60dc854be5a0cbb0b8da3a9594\",\n" +
            "\t\t\t\t\"pName\": \"Lazada- ID\",\n" +
            "\t\t\t\t\"pUrl\": \"http://click.tecdo-ec.com/aff_c?offer_id=105877319&affiliate_id=8209\",\n" +
            "\t\t\t\t\"pkg\": \"com.lazada.android\"\n" +
            "\t\t\t}, {\n" +
            "\t\t\t\t\"appCpsTimeList\": [{\n" +
            "\t\t\t\t\t\"appCpsId\": \"29edbb773d4a4144bfcf3ac1a3f0dfe1\",\n" +
            "\t\t\t\t\t\"endTime\": \"23:01\",\n" +
            "\t\t\t\t\t\"id\": 405,\n" +
            "\t\t\t\t\t\"number\": 1,\n" +
            "\t\t\t\t\t\"startTime\": \"07:01\"\n" +
            "\t\t\t\t}],\n" +
            "\t\t\t\t\"country\": \"THA\",\n" +
            "\t\t\t\t\"cpiUrl\": \"\",\n" +
            "\t\t\t\t\"id\": \"29edbb773d4a4144bfcf3ac1a3f0dfe1\",\n" +
            "\t\t\t\t\"pName\": \"Lazada-TH\",\n" +
            "\t\t\t\t\"pUrl\": \"http://click.tracksummer.com/aff_c?offer_id=105877266&affiliate_id=8209\",\n" +
            "\t\t\t\t\"pkg\": \"com.lazada.android\"\n" +
            "\t\t\t}, {\n" +
            "\t\t\t\t\"appCpsTimeList\": [{\n" +
            "\t\t\t\t\t\"appCpsId\": \"5f9f0e901dc2496d95ff975f40ef6e3e\",\n" +
            "\t\t\t\t\t\"endTime\": \"23:00\",\n" +
            "\t\t\t\t\t\"id\": 383,\n" +
            "\t\t\t\t\t\"number\": 1,\n" +
            "\t\t\t\t\t\"startTime\": \"07:00\"\n" +
            "\t\t\t\t}],\n" +
            "\t\t\t\t\"country\": \"0\",\n" +
            "\t\t\t\t\"cpiUrl\": \"\",\n" +
            "\t\t\t\t\"id\": \"5f9f0e901dc2496d95ff975f40ef6e3e\",\n" +
            "\t\t\t\t\"pName\": \"Banggood\",\n" +
            "\t\t\t\t\"pUrl\": \"http://click.tecdo-ec.com/aff_c?offer_id=113762858&affiliate_id=8209\",\n" +
            "\t\t\t\t\"pkg\": \"com.banggood.client\"\n" +
            "\t\t\t}, {\n" +
            "\t\t\t\t\"appCpsTimeList\": [{\n" +
            "\t\t\t\t\t\"appCpsId\": \"d22c6e9596aa4047adebf908695e68b0\",\n" +
            "\t\t\t\t\t\"endTime\": \"23:00\",\n" +
            "\t\t\t\t\t\"id\": 408,\n" +
            "\t\t\t\t\t\"number\": 2,\n" +
            "\t\t\t\t\t\"startTime\": \"07:00\"\n" +
            "\t\t\t\t}],\n" +
            "\t\t\t\t\"country\": \"0\",\n" +
            "\t\t\t\t\"cpiUrl\": \"\",\n" +
            "\t\t\t\t\"id\": \"d22c6e9596aa4047adebf908695e68b0\",\n" +
            "\t\t\t\t\"pName\": \"Alibaba- Global\",\n" +
            "\t\t\t\t\"pUrl\": \"http://click.tecdo-ec.com/aff_c?offer_id=125715274&affiliate_id=8209\",\n" +
            "\t\t\t\t\"pkg\": \"com.alibaba.intl.android.apps.poseidon\"\n" +
            "\t\t\t}],\n" +
            "\t\t\t\"groupName\": \"cps\",\n" +
            "\t\t\t\"type\": \"4\"\n" +
            "\t\t}],\n" +
            "\t\t\"id\": 2002,\n" +
            "\t\t\"naCbExpand\": 0,\n" +
            "\t\t\"naCkgClickSg\": 0,\n" +
            "\t\t\"naDescClick\": 0,\n" +
            "\t\t\"naIconClick\": 0,\n" +
            "\t\t\"naInstallClick\": 1,\n" +
            "\t\t\"naRbClick\": 1,\n" +
            "\t\t\"naTitleClick\": 0,\n" +
            "\t\t\"preload\": 1\n" +
            "\t}, {\n" +
            "\t\t\"apGroup\": \"1\",\n" +
            "\t\t\"apGroupMaximum\": \"999\",\n" +
            "\t\t\"apGroupRequest\": \"1\",\n" +
            "\t\t\"apName\": \"push\",\n" +
            "\t\t\"enable\": 0,\n" +
            "\t\t\"extraInsert\": 2,\n" +
            "\t\t\"groups\": [{\n" +
            "\t\t\t\"contents\": [\"http://game.cnc-routers.org/drawline/nx1/game.html\"],\n" +
            "\t\t\t\"groupName\": \"push\",\n" +
            "\t\t\t\"type\": \"1\"\n" +
            "\t\t}],\n" +
            "\t\t\"id\": 2001,\n" +
            "\t\t\"naCbExpand\": 0,\n" +
            "\t\t\"naCkgClickSg\": 0,\n" +
            "\t\t\"naDescClick\": 0,\n" +
            "\t\t\"naIconClick\": 0,\n" +
            "\t\t\"naInstallClick\": 1,\n" +
            "\t\t\"naRbClick\": 1,\n" +
            "\t\t\"naTitleClick\": 0,\n" +
            "\t\t\"preload\": 1\n" +
            "\t}, {\n" +
            "\t\t\"apGroup\": \"1\",\n" +
            "\t\t\"apGroupMaximum\": \"999\",\n" +
            "\t\t\"apGroupRequest\": \"1\",\n" +
            "\t\t\"apName\": \"start\",\n" +
            "\t\t\"enable\": 1,\n" +
            "\t\t\"extraInsert\": 2,\n" +
            "\t\t\"groups\": [{\n" +
            "\t\t\t\"adLocation\": \"1161988514010068_1161992707342982\",\n" +
            "\t\t\t\"adLocation2\": \"\",\n" +
            "\t\t\t\"adLocation3\": \"\",\n" +
            "\t\t\t\"adTypeName\": \"Native\",\n" +
            "\t\t\t\"adTypeName2\": \"\",\n" +
            "\t\t\t\"adTypeName3\": \"\",\n" +
            "\t\t\t\"groupName\": \"start\",\n" +
            "\t\t\t\"platformName\": \"Facebook\",\n" +
            "\t\t\t\"platformName2\": \"\",\n" +
            "\t\t\t\"platformName3\": \"\",\n" +
            "\t\t\t\"state\": \"1\",\n" +
            "\t\t\t\"state2\": \"0\",\n" +
            "\t\t\t\"state3\": \"0\",\n" +
            "\t\t\t\"type\": \"3\"\n" +
            "\t\t}],\n" +
            "\t\t\"id\": 2000,\n" +
            "\t\t\"naCbExpand\": 0,\n" +
            "\t\t\"naCkgClickSg\": 0,\n" +
            "\t\t\"naDescClick\": 0,\n" +
            "\t\t\"naIconClick\": 0,\n" +
            "\t\t\"naInstallClick\": 1,\n" +
            "\t\t\"naRbClick\": 1,\n" +
            "\t\t\"naTitleClick\": 0,\n" +
            "\t\t\"preload\": 1\n" +
            "\t}, {\n" +
            "\t\t\"apGroup\": \"1\",\n" +
            "\t\t\"apGroupMaximum\": \"999\",\n" +
            "\t\t\"apGroupRequest\": \"1\",\n" +
            "\t\t\"apName\": \"banner\",\n" +
            "\t\t\"enable\": 1,\n" +
            "\t\t\"extraInsert\": 2,\n" +
            "\t\t\"groups\": [{\n" +
            "\t\t\t\"adLocation\": \"1161988514010068_1161992387343014\",\n" +
            "\t\t\t\"adLocation2\": \"\",\n" +
            "\t\t\t\"adLocation3\": \"\",\n" +
            "\t\t\t\"adTypeName\": \"Banner\",\n" +
            "\t\t\t\"adTypeName2\": \"\",\n" +
            "\t\t\t\"adTypeName3\": \"\",\n" +
            "\t\t\t\"groupName\": \"banner\",\n" +
            "\t\t\t\"platformName\": \"Facebook\",\n" +
            "\t\t\t\"platformName2\": \"\",\n" +
            "\t\t\t\"platformName3\": \"\",\n" +
            "\t\t\t\"state\": \"1\",\n" +
            "\t\t\t\"state2\": \"0\",\n" +
            "\t\t\t\"state3\": \"0\",\n" +
            "\t\t\t\"type\": \"3\"\n" +
            "\t\t}],\n" +
            "\t\t\"id\": 1999,\n" +
            "\t\t\"naCbExpand\": 0,\n" +
            "\t\t\"naCkgClickSg\": 0,\n" +
            "\t\t\"naDescClick\": 0,\n" +
            "\t\t\"naIconClick\": 0,\n" +
            "\t\t\"naInstallClick\": 1,\n" +
            "\t\t\"naRbClick\": 1,\n" +
            "\t\t\"naTitleClick\": 0,\n" +
            "\t\t\"preload\": 1\n" +
            "\t}, {\n" +
            "\t\t\"apGroup\": \"1\",\n" +
            "\t\t\"apGroupMaximum\": \"999\",\n" +
            "\t\t\"apGroupRequest\": \"1\",\n" +
            "\t\t\"apName\": \"exit\",\n" +
            "\t\t\"enable\": 1,\n" +
            "\t\t\"extraInsert\": 2,\n" +
            "\t\t\"groups\": [{\n" +
            "\t\t\t\"adLocation\": \"1161988514010068_1161992000676386\",\n" +
            "\t\t\t\"adLocation2\": \"\",\n" +
            "\t\t\t\"adLocation3\": \"\",\n" +
            "\t\t\t\"adTypeName\": \"Native\",\n" +
            "\t\t\t\"adTypeName2\": \"\",\n" +
            "\t\t\t\"adTypeName3\": \"\",\n" +
            "\t\t\t\"groupName\": \"exit\",\n" +
            "\t\t\t\"platformName\": \"Facebook\",\n" +
            "\t\t\t\"platformName2\": \"\",\n" +
            "\t\t\t\"platformName3\": \"\",\n" +
            "\t\t\t\"state\": \"1\",\n" +
            "\t\t\t\"state2\": \"0\",\n" +
            "\t\t\t\"state3\": \"0\",\n" +
            "\t\t\t\"type\": \"3\"\n" +
            "\t\t}],\n" +
            "\t\t\"id\": 1998,\n" +
            "\t\t\"naCbExpand\": 0,\n" +
            "\t\t\"naCkgClickSg\": 0,\n" +
            "\t\t\"naDescClick\": 0,\n" +
            "\t\t\"naIconClick\": 0,\n" +
            "\t\t\"naInstallClick\": 1,\n" +
            "\t\t\"naRbClick\": 1,\n" +
            "\t\t\"naTitleClick\": 0,\n" +
            "\t\t\"preload\": 1\n" +
            "\t}, {\n" +
            "\t\t\"apGroup\": \"1\",\n" +
            "\t\t\"apGroupMaximum\": \"999\",\n" +
            "\t\t\"apGroupRequest\": \"1\",\n" +
            "\t\t\"apName\": \"popup\",\n" +
            "\t\t\"enable\": 1,\n" +
            "\t\t\"extraInsert\": 2,\n" +
            "\t\t\"groups\": [{\n" +
            "\t\t\t\"adLocation\": \"1161988514010068_1161991777343075\",\n" +
            "\t\t\t\"adLocation2\": \"\",\n" +
            "\t\t\t\"adLocation3\": \"\",\n" +
            "\t\t\t\"adTypeName\": \"Native\",\n" +
            "\t\t\t\"adTypeName2\": \"\",\n" +
            "\t\t\t\"adTypeName3\": \"\",\n" +
            "\t\t\t\"groupName\": \"popup\",\n" +
            "\t\t\t\"platformName\": \"Facebook\",\n" +
            "\t\t\t\"platformName2\": \"\",\n" +
            "\t\t\t\"platformName3\": \"\",\n" +
            "\t\t\t\"state\": \"1\",\n" +
            "\t\t\t\"state2\": \"0\",\n" +
            "\t\t\t\"state3\": \"0\",\n" +
            "\t\t\t\"type\": \"3\"\n" +
            "\t\t}],\n" +
            "\t\t\"id\": 1997,\n" +
            "\t\t\"naCbExpand\": 0,\n" +
            "\t\t\"naCkgClickSg\": 0,\n" +
            "\t\t\"naDescClick\": 0,\n" +
            "\t\t\"naIconClick\": 0,\n" +
            "\t\t\"naInstallClick\": 1,\n" +
            "\t\t\"naRbClick\": 1,\n" +
            "\t\t\"naTitleClick\": 0,\n" +
            "\t\t\"preload\": 1\n" +
            "\t}, {\n" +
            "\t\t\"apGroup\": \"1\",\n" +
            "\t\t\"apGroupMaximum\": \"999\",\n" +
            "\t\t\"apGroupRequest\": \"1\",\n" +
            "\t\t\"apName\": \"inner\",\n" +
            "\t\t\"enable\": 1,\n" +
            "\t\t\"enableGapTime\": 180,\n" +
            "\t\t\"enableTime\": 4,\n" +
            "\t\t\"extraInsert\": 0,\n" +
            "\t\t\"groups\": [{\n" +
            "\t\t\t\"adLocation\": \"1161988514010068_1161991350676451\",\n" +
            "\t\t\t\"adLocation2\": \"\",\n" +
            "\t\t\t\"adLocation3\": \"\",\n" +
            "\t\t\t\"adTypeName\": \"Native\",\n" +
            "\t\t\t\"adTypeName2\": \"\",\n" +
            "\t\t\t\"adTypeName3\": \"\",\n" +
            "\t\t\t\"groupName\": \"inner\",\n" +
            "\t\t\t\"platformName\": \"Facebook\",\n" +
            "\t\t\t\"platformName2\": \"\",\n" +
            "\t\t\t\"platformName3\": \"\",\n" +
            "\t\t\t\"state\": \"1\",\n" +
            "\t\t\t\"state2\": \"0\",\n" +
            "\t\t\t\"state3\": \"0\",\n" +
            "\t\t\t\"type\": \"3\"\n" +
            "\t\t}],\n" +
            "\t\t\"id\": 1996,\n" +
            "\t\t\"naCbDelayTime\": 0,\n" +
            "\t\t\"naCbExpand\": 1,\n" +
            "\t\t\"naCkgClickSg\": 0,\n" +
            "\t\t\"naDescClick\": 0,\n" +
            "\t\t\"naIconClick\": 0,\n" +
            "\t\t\"naInstallClick\": 1,\n" +
            "\t\t\"naRbClick\": 1,\n" +
            "\t\t\"naRbDelayTime\": 0,\n" +
            "\t\t\"naTitleClick\": 0,\n" +
            "\t\t\"preload\": 1\n" +
            "\t}, {\n" +
            "\t\t\"apGroup\": \"1\",\n" +
            "\t\t\"apGroupMaximum\": \"999\",\n" +
            "\t\t\"apGroupRequest\": \"1\",\n" +
            "\t\t\"apName\": \"outside\",\n" +
            "\t\t\"enable\": 1,\n" +
            "\t\t\"enableGapTime\": 10,\n" +
            "\t\t\"enableTime\": 0,\n" +
            "\t\t\"extraInsert\": 1,\n" +
            "\t\t\"groups\": [{\n" +
            "\t\t\t\"adLocation\": \"1161988514010068_1161990684009851\",\n" +
            "\t\t\t\"adLocation2\": \"83ac2a0a5254d696\",\n" +
            "\t\t\t\"adLocation3\": \"\",\n" +
            "\t\t\t\"adTypeName\": \"Native\",\n" +
            "\t\t\t\"adTypeName2\": \"Interstital\",\n" +
            "\t\t\t\"adTypeName3\": \"\",\n" +
            "\t\t\t\"groupName\": \"outside\",\n" +
            "\t\t\t\"platformName\": \"Facebook\",\n" +
            "\t\t\t\"platformName2\": \"AppLovin\",\n" +
            "\t\t\t\"platformName3\": \"\",\n" +
            "\t\t\t\"state\": \"1\",\n" +
            "\t\t\t\"state2\": \"1\",\n" +
            "\t\t\t\"state3\": \"0\",\n" +
            "\t\t\t\"type\": \"3\"\n" +
            "\t\t}],\n" +
            "\t\t\"id\": 1995,\n" +
            "\t\t\"naCbDelayTime\": 3000,\n" +
            "\t\t\"naCbExpand\": 1,\n" +
            "\t\t\"naCkgClickSg\": 1,\n" +
            "\t\t\"naDescClick\": 0,\n" +
            "\t\t\"naIconClick\": 0,\n" +
            "\t\t\"naInstallClick\": 0,\n" +
            "\t\t\"naRbClick\": 0,\n" +
            "\t\t\"naRbDelayTime\": 0,\n" +
            "\t\t\"naTitleClick\": 1,\n" +
            "\t\t\"preload\": 1\n" +
            "\t}],\n" +
            "\t\"header\": {\n" +
            "\t\t\"appIcon\": \"0\",\n" +
            "\t\t\"H5CanGoback\": \"0\",\n" +
            "\t\t\"H5TopBackBtn\": \"0\",\n" +
            "\t\t\"H5CloseIcon\": \"0\",\n" +
            "\t\t\"Referrer\": \"facebooktraffic\",\n" +
            "\t\t\"useReferrer\": \"0\",\n" +
            "\t\t\"version\": \"23\",\n" +
            "\t\t\"appIconTime\": \"0\",\n" +
            "\t\t\"LocalPushEnable\": \"0\"\n" +
            "\t}\n" +
            "}"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        ConfigTool.getLocalConfig(_Config)
        Logger.d("ad size = ${AdBeanTool.getInstance().beanMap.size}")

        AdmobInitHelper.initAdmobSDK(this)

        val banner_parent = findViewById<LinearLayout>(R.id.banner_parent)
        val native_parent = findViewById<LinearLayout>(R.id.native_parent)

        val admobAd = AdmobAd()

        findViewById<Button>(R.id.bt1).setOnClickListener {
            admobAd.interstitialAd(this)
        }
        findViewById<Button>(R.id.bt2).setOnClickListener {
            admobAd.bannerAd(this, banner_parent)
        }
        findViewById<Button>(R.id.bt3).setOnClickListener {
            admobAd.nativeAd(this, native_parent, true, true)
        }
        findViewById<Button>(R.id.bt4).setOnClickListener {
            admobAd.nativeAd(this, native_parent, true, false)
        }
        findViewById<Button>(R.id.bt5).setOnClickListener {
            admobAd.nativeAd(this, native_parent, false, false)
        }
    }
}

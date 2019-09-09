package com.tb.adsdk.tool

class ActivityLifeTool private constructor() {

    companion object {

        private var activityLifeTool: ActivityLifeTool? = null

        fun getInstance(): ActivityLifeTool {
            if (activityLifeTool == null) {
                @Synchronized
                if (activityLifeTool == null) {
                    activityLifeTool = ActivityLifeTool()
                }

            }
            return activityLifeTool as ActivityLifeTool
        }

        fun detroy() {
            activityLifeTool = null
        }
    }

    private val _ActivityLifeList = mutableListOf<String>()

    fun inActivity(activityName: String) {
        if (!_ActivityLifeList.contains(activityName))
            _ActivityLifeList.add(activityName)
    }

    fun outActivity(activityName: String) {
        if (_ActivityLifeList.contains(activityName))
            _ActivityLifeList.remove(activityName)
    }

    fun outApp(): Boolean = _ActivityLifeList.isEmpty()
}
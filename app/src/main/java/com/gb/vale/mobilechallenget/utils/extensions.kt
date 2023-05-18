package com.gb.vale.mobilechallenget.utils

import android.content.Context
import android.content.res.Resources
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun Context.isFullScreenGestureModeEnabled(): Boolean {
    val defaultNavigationSystem = 0
    return try {
        val resources: Resources = this.resources
        val resourceId: Int = resources.getIdentifier(
            "config_navBarInteractionMode",
            "integer",
            "android"
        )
        val type = if (resourceId > 0) {
            resources.getInteger(resourceId)
        } else defaultNavigationSystem
        type == 2
    } catch (e: Exception) {
        false
    }
}

inline fun <reified T> parseStringGson(jsonString: String): T {
    val jsonData = Gson()
    return jsonData.fromJson(jsonString, object : TypeToken<T>() {}.type)
}
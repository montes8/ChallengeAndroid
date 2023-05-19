package com.gb.vale.mobilechallenget.utils

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import androidx.navigation.NavType
import com.gb.vale.mobilechallenget.model.RecipeModel
import com.gb.vale.mobilechallenget.repository.db.entity.RecipeEntity
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

fun parseStringGson(jsonString: String): RecipeModel {
    val jsonData = Gson()
    return jsonData.fromJson(jsonString, object : TypeToken<RecipeModel>() {}.type)
}

fun parseStringGsonList(jsonString: String): ArrayList<RecipeEntity> {
    val jsonData = Gson()
    return jsonData.fromJson(jsonString, object : TypeToken<ArrayList<RecipeEntity>>() {}.type)
}


class RecipeModelType : NavType<RecipeModel>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): RecipeModel? {
        return bundle.getParcelable(key)
    }
    override fun parseValue(value: String): RecipeModel {
        return Gson().fromJson(value, RecipeModel::class.java)
    }
    override fun put(bundle: Bundle, key: String, value: RecipeModel) {
        bundle.putParcelable(key, value)
    }
}
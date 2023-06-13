package com.gb.vale.mobilechallenget.utils

import android.net.Uri
import com.gb.vale.mobilechallenget.repository.db.entity.RecipeEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.regex.Pattern

fun parseStringGsonList(jsonString: String): List<RecipeEntity> {
    val jsonData = Gson()
    return jsonData.fromJson(jsonString, object : TypeToken<List<RecipeEntity>>() {}.type)
}


fun String.isEmailValid(): Boolean {
    return Pattern.compile(
        "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
    ).matcher(this).matches()
}


fun String.lengthPlus1(): Int {
    return this.length + 1
}


inline fun <reified T> parseFromObjet( value: String): T {
    val jsonData = Gson()
    return jsonData.fromJson(Uri.decode(value), object : TypeToken<T>() {}.type)
}

inline fun <reified T> parseFromString( value: T): String {
    val jsonData = Gson()
    return Uri.encode(jsonData.toJson(value))
}


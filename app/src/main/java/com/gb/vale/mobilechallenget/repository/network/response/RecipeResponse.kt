package com.gb.vale.mobilechallenget.repository.network.response

import com.gb.vale.mobilechallenget.model.RecipeModel
import com.gb.vale.mobilechallenget.utils.EMPTY
import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @SerializedName("id") val id : Int?,
    @SerializedName("title") val title : String?,
    @SerializedName("description") val description : String?,
    @SerializedName("preparation") val preparation : String?,
    @SerializedName("urlImg") val urlImg : String?,
    @SerializedName("institute") val institute : String?,
    @SerializedName("addressInstitute") val addressInstitute : String?,
    @SerializedName("latitude") val latitude : String?,
    @SerializedName("longitude") val longitude : String?
){
    companion object{
        fun loadToRecipes(response : List<RecipeResponse>) = response.map {
            RecipeModel(id = it.id?.toLong()?:0,title = it.title?: EMPTY,
                description = it.description?:EMPTY,preparation= it.preparation?:EMPTY,
                urlImg = it.urlImg?:EMPTY, institute = it.institute?:EMPTY,
              latitude = it.latitude?:"0.0", longitude = it.latitude?:"0.0")
        }
    }
}
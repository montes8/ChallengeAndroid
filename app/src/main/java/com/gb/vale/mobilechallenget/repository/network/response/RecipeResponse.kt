package com.gb.vale.mobilechallenget.repository.network.response

import com.gb.vale.mobilechallenget.model.RecipeModel
import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @SerializedName("id") val id : String?,
    @SerializedName("title") val title : String?,
    @SerializedName("description") val description : String?,
    @SerializedName("urlImg") val urlImg : String?
){
    companion object{
        fun loadToRecipes(response : List<RecipeResponse>) = response.map {
            RecipeModel(id = it.id?:"",title = it.title?:"",
                description = it.description?:"",urlImg = it.id?:"")
        }
    }
}
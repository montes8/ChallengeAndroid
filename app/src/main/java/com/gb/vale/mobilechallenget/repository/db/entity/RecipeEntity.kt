package com.gb.vale.mobilechallenget.repository.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gb.vale.mobilechallenget.model.RecipeModel
import com.gb.vale.mobilechallenget.repository.network.response.RecipeResponse
import com.gb.vale.mobilechallenget.utils.EMPTY

@Entity
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var title : String = EMPTY,
    var description : String = EMPTY,
    var preparation : String = EMPTY,
    var urlImg : String = EMPTY,
    var institute : String = EMPTY,
    var addressInstitute : String = EMPTY,
    var latitude : String = "0.0",
    var longitude : String = "0.0"
){
    fun toRecipe() = RecipeModel(
        id = id?:0L,
        title = title,
        description = description,
        preparation = preparation,
        urlImg = urlImg,
        institute = institute,
        addressInstitute = addressInstitute,
        latitude = latitude,
        longitude = longitude,
    )
    companion object{
        fun toListRecipe(response : List<RecipeEntity>)= response.map {
            RecipeModel(
                id = it.id?:0L,
                title = it.title,
                description = it.description,
                preparation = it.preparation,
                urlImg = it.urlImg,
                institute = it.institute,
                addressInstitute = it.addressInstitute,
                latitude = it.latitude,
                longitude = it.longitude,
            )
        }

        fun toListRecipeResponse(response : List<RecipeEntity>)= response.map {
            RecipeResponse(
                id = it.id?:0L,
                title = it.title,
                description = it.description,
                preparation = it.preparation,
                urlImg = it.urlImg,
                institute = it.institute,
                addressInstitute = it.addressInstitute,
                latitude = it.latitude,
                longitude = it.longitude,
            )
        }
    }
}
package com.gb.vale.mobilechallenget.repository.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gb.vale.mobilechallenget.model.EstablishmentModel
import com.gb.vale.mobilechallenget.utils.EMPTY

@Entity
data class EstablishmentEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var title : String = EMPTY,
    var description : String = EMPTY,
    var urlImg : String = EMPTY,
    var latitude : String = EMPTY,
    var longitude : String = EMPTY
){
    companion object{
        fun toListEstablishment(response : List<EstablishmentEntity>) = response.map {
            EstablishmentModel(
                id = it.id?:0,title = it.title,
                description = it.description,urlImg = it.urlImg,
                latitude = it.latitude,longitude = it.longitude
            )
        }
    }
}
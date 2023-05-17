package com.gb.vale.mobilechallenget.repository.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
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
)
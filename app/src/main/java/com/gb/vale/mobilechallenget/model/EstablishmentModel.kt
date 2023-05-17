package com.gb.vale.mobilechallenget.model

import android.os.Parcelable
import com.gb.vale.mobilechallenget.utils.EMPTY
import kotlinx.parcelize.Parcelize

@Parcelize
data class EstablishmentModel(
    var id : Long = 0 ,
    var title : String = EMPTY,
    var description : String = EMPTY,
    var urlImg : String = EMPTY,
    var latitude : String = EMPTY,
    var longitude : String = EMPTY
):Parcelable
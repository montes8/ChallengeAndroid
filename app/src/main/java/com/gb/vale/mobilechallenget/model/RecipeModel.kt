package com.gb.vale.mobilechallenget.model

import android.os.Parcelable
import com.gb.vale.mobilechallenget.utils.EMPTY
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeModel(
    var id : Long = 0,
    var title : String = EMPTY,
    var description : String = EMPTY,
    var preparation : String = EMPTY,
    var urlImg : String = EMPTY,
    var institute : String = EMPTY,
    var addressInstitute : String = EMPTY,
    var latitude : String = "0.0",
    var longitude : String = "0.0"
):Parcelable
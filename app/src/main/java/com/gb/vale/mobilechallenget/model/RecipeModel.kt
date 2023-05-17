package com.gb.vale.mobilechallenget.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeModel(
    var id : String = "",
    var title : String = "",
    var description : String = "",
    var urlImg : String = ""
):Parcelable
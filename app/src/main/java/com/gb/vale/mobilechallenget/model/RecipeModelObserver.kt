package com.gb.vale.mobilechallenget.model


import com.google.maps.android.compose.MarkerState


class RecipeModelObserver(
    var recipes  : ArrayList<RecipeModel> = arrayListOf(),
    val listMarker : ArrayList<MarkerState> = arrayListOf()
)
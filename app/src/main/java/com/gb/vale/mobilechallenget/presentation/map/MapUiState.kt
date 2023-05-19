package com.gb.vale.mobilechallenget.presentation.map

import com.gb.vale.mobilechallenget.model.RecipeModel

data class MapUiState(
    var recipeModel: List<RecipeModel>  = arrayListOf(),
    val  loadMap : Boolean = false
)

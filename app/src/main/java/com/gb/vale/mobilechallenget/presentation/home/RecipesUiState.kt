package com.gb.vale.mobilechallenget.presentation.home

import com.gb.vale.mobilechallenget.model.RecipeModel
import com.gb.vale.mobilechallenget.utils.EMPTY

data class RecipesUiState(
    var recipes: List<RecipeModel> = emptyList(),
    var recipesFilter: List<RecipeModel> = emptyList(),
    var searchQuery: String = EMPTY,
    var filter: Boolean = false
)

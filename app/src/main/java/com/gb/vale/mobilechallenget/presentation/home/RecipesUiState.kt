package com.gb.vale.mobilechallenget.presentation.home

import com.gb.vale.mobilechallenget.model.RecipeModel

data class RecipesUiState(
    var recipes: List<RecipeModel> = emptyList(),
    var recipesFilter: List<RecipeModel> = emptyList(),
    var searchQuery: String = "",
    var filter: Boolean = false
)

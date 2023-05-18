package com.gb.vale.mobilechallenget.presentation.home

import com.gb.vale.mobilechallenget.model.RecipeModel

sealed class RecipeUiEvent {
    data class NavigateToDetail(val recipes : RecipeModel) : RecipeUiEvent()

    object NavigateToBackHome : RecipeUiEvent()
    object NavigateToMap : RecipeUiEvent()
}
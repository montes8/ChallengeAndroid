package com.gb.vale.mobilechallenget.presentation.home

sealed class RecipeUiEvent {
    object NavigateToDetail : RecipeUiEvent()
    object NavigateToHelps : RecipeUiEvent()
}
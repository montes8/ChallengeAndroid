package com.gb.vale.mobilechallenget.presentation.home

import com.gb.vale.mobilechallenget.model.RecipeModel


sealed class RecipeEvent {
    data class SearchContact(val query: String) : RecipeEvent()

    data class  DetailContact(val recipe : RecipeModel) : RecipeEvent()

    object  BackHomeRecipes : RecipeEvent()

}
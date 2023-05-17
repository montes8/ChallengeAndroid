package com.gb.vale.mobilechallenget.presentation.home


sealed class RecipeEvent {
    data class SearchContact(val query: String) : RecipeEvent()
    object  DetailContact : RecipeEvent()

}
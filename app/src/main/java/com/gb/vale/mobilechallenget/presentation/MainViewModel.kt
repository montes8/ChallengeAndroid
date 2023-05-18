package com.gb.vale.mobilechallenget.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gb.vale.mobilechallenget.model.RecipeModel
import com.gb.vale.mobilechallenget.presentation.home.RecipeEvent
import com.gb.vale.mobilechallenget.presentation.home.RecipeUiEvent
import com.gb.vale.mobilechallenget.presentation.home.RecipesUiState
import com.gb.vale.mobilechallenget.usecases.DataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val dataUseCase: DataUseCase) : ViewModel() {


    var uiState by mutableStateOf(RecipesUiState())
         set


    private val _eventFlow = MutableSharedFlow<RecipeUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()
    init {
        loadRecipes()
    }

    private fun loadRecipes() {
        viewModelScope.launch {
            val response =  dataUseCase.loadRecipes()
            uiState = uiState.copy(recipes = response)
        }
    }

    fun onEvent(event: RecipeEvent) {
        viewModelScope.launch {
            when (event) {
                is RecipeEvent.DetailContact -> {
                    _eventFlow.emit(RecipeUiEvent.NavigateToDetail(event.recipe))
                }
                is RecipeEvent.SearchContact -> {
                    uiState = uiState.copy(searchQuery = event.query)
                }

                is RecipeEvent.BackHomeRecipes -> {
                    _eventFlow.emit(RecipeUiEvent.NavigateToBackHome)
                }
            }
        }
    }

    fun listFilter() {
        uiState = uiState.copy(
            recipesFilter = filterRecipes(uiState.searchQuery, ArrayList(uiState.recipes)))
    }

    private fun filterRecipes(textSearch: String, listModel: ArrayList<RecipeModel>): List<RecipeModel> {
        val list: ArrayList<RecipeModel> = ArrayList()
        list.addAll(filterLetterInitial(textSearch, listModel))
        return list
    }

    private fun filterLetterInitial(textSearch: String, listModel: List<RecipeModel>) =
        listModel.filter { it.title.startsWith(textSearch, true) }

}
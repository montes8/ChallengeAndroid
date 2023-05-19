package com.gb.vale.mobilechallenget.presentation.map

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gb.vale.mobilechallenget.usecases.DataDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val dataDBUseCase: DataDBUseCase
) : ViewModel() {


    var uiStateMap by mutableStateOf(MapUiState())
        set


    init {
        viewModelScope.launch {
            delay(1000)
            loadDetailRecipe()
        }
    }

    private fun loadDetailRecipe() {
        viewModelScope.launch {
            val response = dataDBUseCase.loadRecipes()
            uiStateMap = uiStateMap.copy(recipeModel = response,loadMap = true)
        }
    }


}
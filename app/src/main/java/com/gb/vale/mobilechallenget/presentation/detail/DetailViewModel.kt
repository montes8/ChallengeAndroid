package com.gb.vale.mobilechallenget.presentation.detail

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
class DetailViewModel @Inject constructor(
    private val dataDBUseCase: DataDBUseCase
) : ViewModel() {


    var uiStateDetail by mutableStateOf(DetailUiState())

    var idDetail : Long = 1


    init {
        viewModelScope.launch {
            delay(500)
        loadDetailRecipe(idDetail)
        }
    }

    private fun loadDetailRecipe(id: Long) {
        viewModelScope.launch {
            val response = dataDBUseCase.loadIdRecipes(id)
            uiStateDetail = uiStateDetail.copy(recipeModel = response)
        }
    }


}
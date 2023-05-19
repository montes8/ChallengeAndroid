package com.gb.vale.mobilechallenget.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gb.vale.mobilechallenget.repository.di.IoDispatcher
import com.gb.vale.mobilechallenget.usecases.DataDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val dataDBUseCase: DataDBUseCase, @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    var uiStateDetail by mutableStateOf(DetailUiState())

    var idDetail : Long = 1

    init {
        viewModelScope.launch(ioDispatcher) {
            delay(500)
        loadDetailRecipe(idDetail)
        }
    }

    private fun loadDetailRecipe(id: Long) {
        viewModelScope.launch(ioDispatcher) {
            val response = dataDBUseCase.loadIdRecipes(id)
            uiStateDetail = uiStateDetail.copy(recipeModel = response)
        }
    }
}
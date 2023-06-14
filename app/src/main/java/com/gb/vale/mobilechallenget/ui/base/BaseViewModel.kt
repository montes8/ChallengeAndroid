package com.gb.vale.mobilechallenget.ui.base

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gb.vale.mobilechallenget.repository.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

open class BaseViewModel( @IoDispatcher
                     private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO): ViewModel() {

    var uiStateBase by mutableStateOf(BaseUiState())

    fun execute(loading: Boolean = true,func:suspend ()->Unit){
        viewModelScope.launch(ioDispatcher){
            try {
                uiStateBase = uiStateBase.copy(loading = loading)
                delay(2000)
                func()
                uiStateBase = uiStateBase.copy(loading = false)
            }catch (ex:Exception){
                uiStateBase = uiStateBase.copy(error = true, errorType = ex, loading = false)
            }
        }
    }
}
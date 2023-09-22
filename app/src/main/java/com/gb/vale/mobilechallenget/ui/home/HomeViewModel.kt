package com.gb.vale.mobilechallenget.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gb.vale.mobilechallenget.model.RecipeModel
import com.gb.vale.mobilechallenget.repository.di.IoDispatcher
import com.gb.vale.mobilechallenget.ui.InitUiEvent
import com.gb.vale.mobilechallenget.ui.base.BaseViewModel
import com.gb.vale.mobilechallenget.usecases.DataDBUseCase
import com.gb.vale.mobilechallenget.usecases.DataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val dataUseCase: DataUseCase,private val dataDBUseCase: DataDBUseCase,
                                        @IoDispatcher
                                        private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseViewModel(ioDispatcher) {

    var uiState by mutableStateOf(RecipesUiState())
    var floatingButton by mutableStateOf(true)
    val eventFlow = MutableSharedFlow<InitUiEvent>()

    init {
        execute {
            delay(500)
            loadRecipes()
        }
    }

     fun loadRecipes() {
         execute {
            val response =  dataUseCase.loadRecipes()
            uiState = uiState.copy(recipes = response)
        }
    }

    fun loadInsertDataBase(){
        execute {
             dataDBUseCase.insertData()
        }
    }

    fun onClickMap(){
        execute(false) {
            eventFlow.emit(InitUiEvent.NavigateToMap)
        }
    }

    fun onClickBack(){
        execute(false)  {
            eventFlow.emit(InitUiEvent.NavigateToBack)
        }
    }

    fun onClickDetail(data : String){
        execute(false) {
            eventFlow.emit(InitUiEvent.NavigateToDetail(data))
        }
    }

    fun listFilter() {
        uiState = uiState.copy(
            recipesFilter = filterRecipes(uiState.searchQuery, ArrayList(uiState.recipes)))
    }

    private fun filterRecipes(textSearch: String, listModel: ArrayList<RecipeModel>): List<RecipeModel> {
        val list: ArrayList<RecipeModel> = ArrayList()
        list.addAll(filterLetterInitial(textSearch, listModel))
        listModel.removeAll(filterLetterInitial(textSearch, listModel).toSet())
        list.addAll(filterLetter(textSearch, listModel))
        return list
    }

    private fun filterLetterInitial(textSearch: String, listModel: List<RecipeModel>) =
        listModel.filter { it.title.startsWith(textSearch, true) }

    private fun filterLetter(textSearch: String, listModel: List<RecipeModel>) =
        listModel.filter { it.title.contains(textSearch, true) }

}
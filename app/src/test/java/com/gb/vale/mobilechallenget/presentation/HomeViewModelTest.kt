package com.gb.vale.mobilechallenget.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gb.vale.mobilechallenget.CoroutineTestRule
import com.gb.vale.mobilechallenget.presentation.home.HomeViewModel
import com.gb.vale.mobilechallenget.repository.db.entity.RecipeEntity
import com.gb.vale.mobilechallenget.usecases.DataDBUseCase
import com.gb.vale.mobilechallenget.usecases.DataUseCase
import com.gb.vale.mobilechallenget.utils.listJson
import com.gb.vale.mobilechallenget.utils.parseStringGsonList
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest{

    @Mock
    lateinit var dataUseCase: DataUseCase

    @Mock
    lateinit var dataDBUseCase: DataDBUseCase


    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Test
    fun `get list correct`() = runBlocking{
        val homeViewModel = HomeViewModel(dataUseCase,dataDBUseCase,coroutineTestRule.dispatcher)
        `when`(dataUseCase.loadRecipes()).
        thenReturn(RecipeEntity.toListRecipe(parseStringGsonList(listJson)) )
        homeViewModel.loadRecipes()
        assertEquals(true, homeViewModel.uiState.recipes ==
                RecipeEntity.toListRecipe(parseStringGsonList(listJson)) )
    }

    @Test
    fun `get list incorrect`() = runBlocking{
        val homeViewModel = HomeViewModel(dataUseCase,dataDBUseCase,coroutineTestRule.dispatcher)
        `when`(dataUseCase.loadRecipes()).
        thenReturn(RecipeEntity.toListRecipe(arrayListOf()) )
        homeViewModel.loadRecipes()
        assertEquals(false, homeViewModel.uiState.recipes ==
                RecipeEntity.toListRecipe(parseStringGsonList(listJson)) )
    }
}
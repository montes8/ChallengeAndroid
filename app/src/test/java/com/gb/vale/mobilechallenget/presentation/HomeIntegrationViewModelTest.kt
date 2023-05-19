package com.gb.vale.mobilechallenget.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gb.vale.mobilechallenget.CoroutineTestRule
import com.gb.vale.mobilechallenget.presentation.home.HomeViewModel
import com.gb.vale.mobilechallenget.repository.db.entity.RecipeEntity
import com.gb.vale.mobilechallenget.repository.network.ChallengeService
import com.gb.vale.mobilechallenget.repository.network.api.DataNetwork
import com.gb.vale.mobilechallenget.usecases.DataDBUseCase
import com.gb.vale.mobilechallenget.usecases.DataUseCase
import com.gb.vale.mobilechallenget.utils.listJson
import com.gb.vale.mobilechallenget.utils.listJsonTest
import com.gb.vale.mobilechallenget.utils.parseStringGsonList
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeIntegrationViewModelTest{

    @Mock lateinit var challengeService: ChallengeService
    @Mock private lateinit var dataDBUseCase: DataDBUseCase
    private lateinit var dataUseCase: DataUseCase
     private lateinit var dataNetwork: DataNetwork
    private lateinit var homeViewModel: HomeViewModel


    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun setup() {
         dataNetwork = DataNetwork(challengeService)
        dataUseCase = DataUseCase(dataNetwork)
        homeViewModel = HomeViewModel(dataUseCase, dataDBUseCase, coroutineTestRule.dispatcher)
    }


    @Test
    fun `get list not empty server`() = runBlocking{
        `when`(challengeService.loadRecipes()).
        thenReturn(Response.success(RecipeEntity.toListRecipeResponse(parseStringGsonList(listJson))))
        homeViewModel.loadRecipes()
        assertEquals(true, homeViewModel.uiState.recipes.isNotEmpty())
    }

    @Test
    fun `get list  empty server`() = runBlocking{
        `when`(challengeService.loadRecipes()).
        thenReturn(Response.success(RecipeEntity.toListRecipeResponse(arrayListOf())))
        homeViewModel.loadRecipes()
        assertEquals(true, homeViewModel.uiState.recipes.isEmpty())
    }


    @Test
    fun `get list correct`() = runBlocking{
        val list = RecipeEntity.toListRecipe(parseStringGsonList(listJson))
        `when`(challengeService.loadRecipes()).
        thenReturn(Response.success(RecipeEntity.toListRecipeResponse(parseStringGsonList(listJson))) )
        homeViewModel.loadRecipes()
        assertEquals(list, homeViewModel.uiState.recipes)
    }

    @Test
    fun `get list incorrect`() = runBlocking{
        `when`(challengeService.loadRecipes()).
        thenReturn(Response.success(RecipeEntity.toListRecipeResponse(parseStringGsonList(listJson))))
        homeViewModel.loadRecipes()
        assertEquals(false, homeViewModel.uiState.recipes ==
                RecipeEntity.toListRecipe(parseStringGsonList(listJsonTest)) )
    }
}
package com.gb.vale.mobilechallenget.usecases

import com.gb.vale.mobilechallenget.repository.db.entity.RecipeEntity
import com.gb.vale.mobilechallenget.utils.listJson
import com.gb.vale.mobilechallenget.utils.parseStringGsonList
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class DataDBUseCaseTest{

    @Mock
    lateinit var dataUseCase: DataDBUseCase

    @Test
    fun `validate list correct no empty`() = runBlocking{
        Mockito.`when`(dataUseCase.loadRecipes()).
        thenReturn(RecipeEntity.toListRecipe(parseStringGsonList(listJson)) )
        val result =  dataUseCase.loadRecipes()
        Assert.assertEquals(true,result.isNotEmpty())
    }

    @Test
    fun `validate list incorrect empty`()= runBlocking{
        Mockito.`when`(dataUseCase.loadRecipes()).thenReturn(
            RecipeEntity.toListRecipe(parseStringGsonList(listJson)))
        val result =  dataUseCase.loadRecipes()
        Assert.assertEquals(false, result.isEmpty())
    }

}
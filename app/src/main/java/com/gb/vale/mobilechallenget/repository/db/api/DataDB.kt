package com.gb.vale.mobilechallenget.repository.db.api

import com.gb.vale.mobilechallenget.model.RecipeModel
import com.gb.vale.mobilechallenget.repository.db.dao.RecipeDao
import com.gb.vale.mobilechallenget.repository.db.entity.RecipeEntity
import com.gb.vale.mobilechallenget.usecases.network.IDataDB

import javax.inject.Inject

class DataDB @Inject constructor(private val recipeDao : RecipeDao) : IDataDB {
    override suspend fun insertAll(list: List<RecipeEntity>) {
        recipeDao.insertAll(list)
    }

    override  suspend fun loadRecipes(): List<RecipeModel> {
       val response = recipeDao.getListEntity()
        return RecipeEntity.toListRecipe(response)
    }

    override suspend fun loadRecipesId(id : Long): RecipeModel {
       val response = recipeDao.getIdRecipe(id)
        return response?.toRecipe()?:RecipeModel()
    }

}
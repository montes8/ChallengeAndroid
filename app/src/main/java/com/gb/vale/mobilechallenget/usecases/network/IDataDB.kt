package com.gb.vale.mobilechallenget.usecases.network

import com.gb.vale.mobilechallenget.model.RecipeModel
import com.gb.vale.mobilechallenget.repository.db.entity.RecipeEntity

interface IDataDB {

      suspend fun insertAll(list: List<RecipeEntity>)
      suspend fun loadRecipes( ): List<RecipeModel>

      suspend fun loadRecipesId( id : Long): RecipeModel

}
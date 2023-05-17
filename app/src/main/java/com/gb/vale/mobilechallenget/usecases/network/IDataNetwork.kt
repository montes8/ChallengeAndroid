package com.gb.vale.mobilechallenget.usecases.network

import com.gb.vale.mobilechallenget.model.RecipeModel

interface IDataNetwork {

     suspend fun loadRecipes( ): List<RecipeModel>?

}
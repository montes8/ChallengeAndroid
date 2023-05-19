package com.gb.vale.mobilechallenget.usecases

import com.gb.vale.mobilechallenget.usecases.network.IDataDB
import javax.inject.Inject

class DataDBUseCase @Inject constructor(private val iDataDB : IDataDB
) {
     suspend fun loadRecipes() = iDataDB.loadRecipes()

     suspend fun loadIdRecipes(id : Long) = iDataDB.loadRecipesId(id)

}
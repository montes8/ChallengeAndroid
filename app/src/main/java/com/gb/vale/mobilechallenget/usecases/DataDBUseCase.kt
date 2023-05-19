package com.gb.vale.mobilechallenget.usecases

import com.gb.vale.mobilechallenget.usecases.network.IDataDB
import com.gb.vale.mobilechallenget.utils.listJson
import com.gb.vale.mobilechallenget.utils.parseStringGsonList
import javax.inject.Inject

class DataDBUseCase @Inject constructor(private val iDataDB : IDataDB
) {
     suspend fun loadRecipes() = iDataDB.loadRecipes()

     suspend fun loadIdRecipes(id : Long) = iDataDB.loadRecipesId(id)

     suspend fun insertData() {
          if (iDataDB.loadRecipes().isEmpty())iDataDB.insertAll(parseStringGsonList(listJson))
     }

}
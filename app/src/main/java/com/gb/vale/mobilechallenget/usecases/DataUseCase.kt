package com.gb.vale.mobilechallenget.usecases

import com.gb.vale.mobilechallenget.model.RecipeModel
import com.gb.vale.mobilechallenget.usecases.network.IDataDB
import com.gb.vale.mobilechallenget.usecases.network.IDataNetwork
import com.gb.vale.mobilechallenget.utils.listJson
import com.gb.vale.mobilechallenget.utils.parseStringGsonList
import javax.inject.Inject

class DataUseCase @Inject constructor(private val iDataNetwork : IDataNetwork,private val iDataDb : IDataDB) {
    suspend fun loadRecipes() : List<RecipeModel> {
        if (iDataDb.loadRecipes().isEmpty())iDataDb.insertAll(parseStringGsonList(listJson))
        var list = iDataNetwork.loadRecipes()
        if (list.isEmpty()){ list = iDataDb.loadRecipes()}
        return list
    }
}
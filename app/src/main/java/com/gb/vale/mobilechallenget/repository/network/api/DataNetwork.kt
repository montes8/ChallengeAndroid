package com.gb.vale.mobilechallenget.repository.network.api

import com.gb.vale.mobilechallenget.model.RecipeModel
import com.gb.vale.mobilechallenget.repository.network.ChallengeService
import com.gb.vale.mobilechallenget.repository.network.response.RecipeResponse
import com.gb.vale.mobilechallenget.usecases.network.IDataNetwork
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataNetwork @Inject constructor(private val acmService : ChallengeService) : IDataNetwork {

    override  suspend fun loadRecipes(): List<RecipeModel> {
        var data : List<RecipeModel> = ArrayList()
       val response = acmService.loadRecipes()
        try {
            if (response.isSuccessful){
                data = RecipeResponse.loadToRecipes(response.body()?:ArrayList())
            }
        }catch (e:Exception){
            data = ArrayList()
        }
        return data
    }

}
package com.gb.vale.mobilechallenget.repository.network

import com.gb.vale.mobilechallenget.repository.network.response.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET

interface ChallengeService {
    @GET("api/user/loadRecipes")
     suspend fun loadRecipes(): Response<List<RecipeResponse>>

}
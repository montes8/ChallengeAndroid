package com.gb.vale.mobilechallenget.usecases

import com.gb.vale.mobilechallenget.usecases.network.IDataNetwork
import javax.inject.Inject

class DataUseCase @Inject constructor(private val iDataNetwork : IDataNetwork
) {
    suspend fun loadRecipes() = iDataNetwork.loadRecipes()

}
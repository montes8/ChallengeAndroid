package com.gb.vale.mobilechallenget.usecases

import com.gb.vale.mobilechallenget.usecases.network.IDataDB
import javax.inject.Inject

class DataDBUseCase @Inject constructor(private val iDataDB : IDataDB
) {
     fun loadRecipes() = iDataDB.loadEstablishment()

}
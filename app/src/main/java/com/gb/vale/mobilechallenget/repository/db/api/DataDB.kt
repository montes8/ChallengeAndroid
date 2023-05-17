package com.gb.vale.mobilechallenget.repository.db.api

import com.gb.vale.mobilechallenget.model.EstablishmentModel
import com.gb.vale.mobilechallenget.repository.db.dao.EstablishmentDao
import com.gb.vale.mobilechallenget.repository.db.entity.EstablishmentEntity
import com.gb.vale.mobilechallenget.usecases.network.IDataDB
import javax.inject.Inject

class DataDB @Inject constructor(private val establishmentDao : EstablishmentDao) : IDataDB {

    override  fun loadEstablishment(): List<EstablishmentModel> {
       val response = establishmentDao.getListEntity()
        return EstablishmentEntity.toListEstablishment(response)
    }

}
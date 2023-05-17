package com.gb.vale.mobilechallenget.usecases.network

import com.gb.vale.mobilechallenget.model.EstablishmentModel

interface IDataDB {

      fun loadEstablishment( ): List<EstablishmentModel>

}
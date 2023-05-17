package com.gb.vale.mobilechallenget.repository.db

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.gb.vale.mobilechallenget.repository.db.dao.EstablishmentDao
import com.gb.vale.mobilechallenget.repository.db.entity.EstablishmentEntity
import javax.inject.Provider
import kotlin.concurrent.thread

class DefaultBDCallback(private val provider: Provider<EstablishmentDao>) :  RoomDatabase.Callback(){

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        thread(start = true) {

            val list = ArrayList<EstablishmentEntity>()

            list.add(EstablishmentEntity(title = "Establecimiento 01", description = "Lunes a Domingo de 8 am a 6pm",
                urlImg = "https://i.ibb.co/bvbTssK/unnamed.jpg", latitude = "-11,99405732", longitude = "-77.06241231"))

            list.add(EstablishmentEntity(title = "Establecimiento 02", description = "Lunes a Domingo de 8 am a 6pm",
                urlImg = "https://i.ibb.co/bvbTssK/unnamed.jpg", latitude = " -11.99041933", longitude = "-77.06290144"))

            list.add(EstablishmentEntity(title = "Establecimiento 03", description = "Lunes a Domingo de 8 am a 6pm",
                urlImg = "https://i.ibb.co/bvbTssK/unnamed.jpg", latitude = "-12.00681565", longitude = "-77.05884285"))

            list.add(EstablishmentEntity(title = "Establecimiento 04", description = "Lunes a Domingo de 8 am a 6pm",
                urlImg = "https://i.ibb.co/bvbTssK/unnamed.jpg", latitude = "-12.05688609", longitude = "-77.03773475"))

            list.add(EstablishmentEntity(title = "Establecimiento 05", description = "Lunes a Domingo de 8 am a 6pm",
                urlImg = "https://i.ibb.co/bvbTssK/unnamed.jpg", latitude = "-12.06631014", longitude = "-77.04746379"))

            provider.get().insertAll(list)
        }
    }

}
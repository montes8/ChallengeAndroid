package com.gb.vale.mobilechallenget.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gb.vale.mobilechallenget.repository.db.dao.EstablishmentDao
import com.gb.vale.mobilechallenget.repository.db.entity.EstablishmentEntity

@Database(entities = [EstablishmentEntity::class],version = 1, exportSchema = false)
abstract class ChallengeDataBase : RoomDatabase(){

    abstract fun establishmentDao() : EstablishmentDao

}
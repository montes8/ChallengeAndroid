package com.gb.vale.mobilechallenget.repository.db.dao

import androidx.room.*
import com.gb.vale.mobilechallenget.repository.db.entity.EstablishmentEntity

@Dao
interface EstablishmentDao {

    @Query("select * from EstablishmentEntity")
    fun getListEntity(): List<EstablishmentEntity>


    @Insert
    fun insertAll(list : List<EstablishmentEntity>): List<Long>?

}
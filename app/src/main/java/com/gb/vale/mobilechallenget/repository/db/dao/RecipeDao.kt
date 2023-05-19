package com.gb.vale.mobilechallenget.repository.db.dao

import androidx.room.*
import com.gb.vale.mobilechallenget.repository.db.entity.RecipeEntity

@Dao
interface RecipeDao {

    @Query("select * from RecipeEntity")
    suspend fun getListEntity(): List<RecipeEntity>

    @Query("select * from RecipeEntity where id = :id")
    suspend fun getIdRecipe(id:Long):RecipeEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list : List<RecipeEntity>): List<Long>?

}
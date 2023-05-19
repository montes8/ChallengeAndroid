package com.gb.vale.mobilechallenget.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gb.vale.mobilechallenget.repository.db.dao.RecipeDao
import com.gb.vale.mobilechallenget.repository.db.entity.RecipeEntity

@Database(entities = [RecipeEntity::class],version = 1, exportSchema = false)
abstract class ChallengeDataBase : RoomDatabase(){

    abstract fun recipeDao() : RecipeDao

}
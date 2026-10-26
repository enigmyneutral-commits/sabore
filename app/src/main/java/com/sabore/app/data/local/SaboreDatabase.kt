package com.sabore.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sabore.app.data.local.dao.RecipeDao
import com.sabore.app.data.local.dao.UserDao
import com.sabore.app.data.local.entities.*

@Database(
    entities = [
        UserEntity::class,
        RecipeEntity::class,
        IngredientEntity::class,
        StepEntity::class,
        FavoriteEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class SaboreDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_NAME = "sabore_database"
    }
}

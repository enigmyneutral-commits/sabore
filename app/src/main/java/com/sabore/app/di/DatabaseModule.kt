package com.sabore.app.di

import android.content.Context
import androidx.room.Room
import com.sabore.app.data.local.SaboreDatabase
import com.sabore.app.data.local.dao.RecipeDao
import com.sabore.app.data.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideSaboreDatabase(@ApplicationContext context: Context): SaboreDatabase {
        return Room.databaseBuilder(
            context,
            SaboreDatabase::class.java,
            SaboreDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideRecipeDao(database: SaboreDatabase): RecipeDao {
        return database.recipeDao()
    }

    @Provides
    fun provideUserDao(database: SaboreDatabase): UserDao {
        return database.userDao()
    }
}

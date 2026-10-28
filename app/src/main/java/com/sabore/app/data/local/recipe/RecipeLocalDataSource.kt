package com.sabore.app.data.local

import com.sabore.app.data.local.dao.RecipeDao
import com.sabore.app.data.local.entities.RecipeEntity
import com.sabore.app.data.local.entities.IngredientEntity
import com.sabore.app.data.local.entities.StepEntity
import com.sabore.app.data.local.relations.RecipeWithDetails
import kotlinx.coroutines.flow.Flow

class RecipeLocalDataSource(private val recipeDao: RecipeDao) {

    suspend fun insertRecipe(recipe: RecipeEntity) = recipeDao.insertRecipe(recipe)

    suspend fun insertIngredients(ingredients: List<IngredientEntity>) = recipeDao.insertIngredients(ingredients)

    suspend fun insertSteps(steps: List<StepEntity>) = recipeDao.insertSteps(steps)

    suspend fun getRecipeById(recipeId: String): RecipeWithDetails? = recipeDao.getRecipeById(recipeId)

    suspend fun deleteRecipe(recipe: RecipeEntity) = recipeDao.deleteRecipe(recipe)

    fun getPublicRecipes(): Flow<List<RecipeWithDetails>> = recipeDao.getPublicRecipes()

    fun getUserRecipes(userId: String): Flow<List<RecipeWithDetails>> = recipeDao.getUserRecipes(userId)

    fun getFavoriteRecipes(userId: String): Flow<List<RecipeWithDetails>> = recipeDao.getFavoriteRecipes(userId)

    fun isFavorite(userId: String, recipeId: String): Flow<Boolean> = recipeDao.isFavorite(userId, recipeId)

    suspend fun insertFavorite(userId: String, recipeId: String) {
        recipeDao.insertFavorite(com.sabore.app.data.local.entities.FavoriteEntity(userId, recipeId))
    }

    suspend fun deleteFavorite(userId: String, recipeId: String) {
        recipeDao.deleteFavorite(userId, recipeId)
    }
}

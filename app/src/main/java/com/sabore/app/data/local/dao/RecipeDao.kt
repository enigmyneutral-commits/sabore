package com.sabore.app.data.local.dao

import androidx.room.*
import com.sabore.app.data.local.entities.FavoriteEntity
import com.sabore.app.data.local.entities.IngredientEntity
import com.sabore.app.data.local.entities.RecipeEntity
import com.sabore.app.data.local.entities.StepEntity
import com.sabore.app.data.local.relations.RecipeWithDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Transaction
    @Query("SELECT * FROM recipes WHERE visibility = 'PUBLIC' ORDER BY createdAt DESC")
    fun getPublicRecipes(): Flow<List<RecipeWithDetails>>

    @Transaction
    @Query("SELECT * FROM recipes WHERE ownerId = :userId ORDER BY createdAt DESC")
    fun getUserRecipes(userId: String): Flow<List<RecipeWithDetails>>

    @Transaction
    @Query("SELECT * FROM recipes WHERE id = :recipeId")
    suspend fun getRecipeById(recipeId: String): RecipeWithDetails?

    @Transaction
    @Query("""
        SELECT r.* FROM recipes r 
        INNER JOIN favorites f ON r.id = f.recipeId 
        WHERE f.userId = :userId
    """)
    fun getFavoriteRecipes(userId: String): Flow<List<RecipeWithDetails>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: RecipeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredients(ingredients: List<IngredientEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSteps(steps: List<StepEntity>)

    @Query("DELETE FROM ingredients WHERE recipeId = :recipeId")
    suspend fun deleteIngredientsForRecipe(recipeId: String)

    @Query("DELETE FROM steps WHERE recipeId = :recipeId")
    suspend fun deleteStepsForRecipe(recipeId: String)

    @Delete
    suspend fun deleteRecipe(recipe: RecipeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteEntity)

    @Query("DELETE FROM favorites WHERE userId = :userId AND recipeId = :recipeId")
    suspend fun deleteFavorite(userId: String, recipeId: String)

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE userId = :userId AND recipeId = :recipeId)")
    fun isFavorite(userId: String, recipeId: String): Flow<Boolean>
}

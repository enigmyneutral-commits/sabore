package com.sabore.app.data.repository

import com.sabore.app.data.local.RecipeLocalDataSource
import com.sabore.app.data.local.entities.RecipeEntity
import com.sabore.app.data.local.entities.IngredientEntity
import com.sabore.app.data.local.entities.StepEntity
import com.sabore.app.data.local.relations.RecipeWithDetails
import com.sabore.app.data.model.Recipe
import com.sabore.app.data.model.RecipeCreateRequest
import com.sabore.app.data.network.api.RecipesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepository @Inject constructor(
    private val recipesApi: RecipesApi,
    private val localDataSource: RecipeLocalDataSource
) {

    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> = _recipes.stateIn(
        scope = CoroutineScope(Dispatchers.Default),
        started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun getPublicRecipes(category: String? = null, query: String? = null, difficulty: String? = null,
                         maxTime: Int? = null, page: Int? = null, limit: Int? = null) {
        CoroutineScope(Dispatchers.Default).launch {
            try {
                val response = recipesApi.getPublicRecipes(category, query, difficulty, maxTime, page, limit)
                if (response.isSuccessful && response.body() != null) {
                    _recipes.value = response.body()!!
                }
            } catch (e: Exception) {
                // Fallback ou tratamento de erro de rede
            }
        }
    }

    suspend fun createRecipe(request: RecipeCreateRequest): Recipe? {
        return try {
            val response = recipesApi.createRecipe(request)
            if (response.isSuccessful && response.body() != null) {
                val created = response.body()!!
                val recipeEntity = RecipeEntity(
                    id = created.id,
                    ownerId = created.ownerId ?: "user_1",
                    title = created.title,
                    description = created.description ?: "",
                    category = created.category ?: "Geral",
                    visibility = created.visibility,
                    prepTimeMinutes = created.prepTimeMinutes,
                    cookTimeMinutes = created.cookTimeMinutes,
                    servings = created.servings,
                    difficulty = created.difficulty,
                    imageUrl = created.imageUrl,
                    authorName = created.authorName ?: "Chef Saborê",
                    createdAt = System.currentTimeMillis(),
                    updatedAt = System.currentTimeMillis()
                )
                localDataSource.insertRecipe(recipeEntity)
                created
            } else {
                null
            }
        } catch (e: Exception) {
            val fallbackId = "local_${System.currentTimeMillis()}"
            val recipeEntity = RecipeEntity(
                id = fallbackId,
                ownerId = "user_1",
                title = request.title,
                description = request.description ?: "",
                category = request.category ?: "Geral",
                visibility = request.visibility,
                prepTimeMinutes = request.prepTimeMinutes,
                cookTimeMinutes = request.cookTimeMinutes,
                servings = request.servings,
                difficulty = request.difficulty,
                imageUrl = request.imageUrl,
                authorName = "Chef Saborê",
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )
            localDataSource.insertRecipe(recipeEntity)
            Recipe(
                id = fallbackId,
                ownerId = "user_1",
                title = request.title,
                description = request.description,
                category = request.category,
                visibility = request.visibility,
                prepTimeMinutes = request.prepTimeMinutes,
                cookTimeMinutes = request.cookTimeMinutes,
                servings = request.servings,
                difficulty = request.difficulty,
                imageUrl = request.imageUrl,
                createdAt = java.time.Instant.now().toString(),
                updatedAt = java.time.Instant.now().toString(),
                authorName = "Chef Saborê"
            )
        }
    }

    suspend fun saveRecipeLocally(recipeEntity: RecipeEntity,
                                  ingredients: List<IngredientEntity>,
                                  steps: List<StepEntity>) {
        localDataSource.insertRecipe(recipeEntity)
        localDataSource.insertIngredients(ingredients)
        localDataSource.insertSteps(steps)
    }

    fun getUserRecipesFlow(userId: String): Flow<List<RecipeWithDetails>> =
        localDataSource.getUserRecipes(userId)

    fun getPublicRecipesFlow(): Flow<List<RecipeWithDetails>> =
        localDataSource.getPublicRecipes()
}

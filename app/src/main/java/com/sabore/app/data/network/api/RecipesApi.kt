package com.sabore.app.data.network.api

import com.sabore.app.data.model.*
import retrofit2.Response
import retrofit2.http.*

interface RecipesApi {
    @GET("api/v1/recipes/public")
    suspend fun getPublicRecipes(
        @Query("category") category: String?,
        @Query("query") query: String?,
        @Query("difficulty") difficulty: String?,
        @Query("maxTime") maxTime: Int?,
        @Query("page") page: Int?,
        @Query("limit") limit: Int?
    ): Response<List<Recipe>>

    @GET("api/v1/recipes/{id}")
    suspend fun getRecipeById(@Path("id") id: String): Response<Recipe>

    @POST("api/v1/recipes")
    suspend fun createRecipe(@Body request: RecipeCreateRequest): Response<Recipe>

    @PATCH("api/v1/recipes/{id}")
    suspend fun updateRecipe(@Path("id") id: String, @Body request: RecipeCreateRequest): Response<Recipe>

    @POST("api/v1/recipes/{id}/publish")
    suspend fun publishRecipe(@Path("id") id: String): Response<Recipe>

    @POST("api/v1/recipes/{id}/share")
    suspend fun shareRecipe(@Path("id") id: String): Response<Map<String, String>>
}

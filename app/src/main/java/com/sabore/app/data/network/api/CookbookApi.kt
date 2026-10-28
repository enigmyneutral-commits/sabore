package com.sabore.app.data.network.api

import com.sabore.app.data.model.*
import retrofit2.Response
import retrofit2.http.*

interface CookbookApi {
    @GET("api/v1/cookbooks")
    suspend fun getCookbooks(): Response<List<Cookbook>>

    @POST("api/v1/cookbooks")
    suspend fun createCookbook(@Body request: Map<String, String>): Response<Cookbook>

    @GET("api/v1/favorites")
    suspend fun getFavorites(): Response<List<FavoriteRecipe>>

    @POST("api/v1/favorites")
    suspend fun addFavorite(@Body request: Map<String, String>): Response<Unit>

    @DELETE("api/v1/favorites/{recipeId}")
    suspend fun removeFavorite(@Path("recipeId") recipeId: String): Response<Unit>

    @GET("api/v1/history")
    suspend fun getCookingHistory(): Response<List<CookingHistoryEntry>>

    @POST("api/v1/history")
    suspend fun recordHistory(@Body request: Map<String, String>): Response<CookingHistoryEntry>

    @POST("api/v1/cookbooks/export")
    suspend fun exportPdf(): Response<Map<String, String>>
}

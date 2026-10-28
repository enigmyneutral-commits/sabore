package com.sabore.app.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

data class UserSyncRequest(
    val id: String,
    val email: String,
    val displayName: String?,
    val avatarUrl: String?
)

data class Recipe(
    val id: String,
    val ownerId: String?,
    val title: String,
    val description: String?,
    val category: String?,
    val visibility: String = "PRIVATE",
    val prepTimeMinutes: Int = 0,
    val cookTimeMinutes: Int = 0,
    val servings: Int = 1,
    val difficulty: String = "easy",
    val imageUrl: String?,
    val createdAt: String,
    val updatedAt: String
)

data class RecipeCreateRequest(
    val title: String,
    val description: String?,
    val category: String?,
    val visibility: String,
    val prepTimeMinutes: Int,
    val cookTimeMinutes: Int,
    val servings: Int,
    val difficulty: String,
    val imageUrl: String?
)

data class PixRequest(
    val userId: String
)

data class PixResponse(
    val txid: String?,
    val qrcode: String?,
    val imagemQrcode: String?,
    val valor: Any?,
    val status: String?
)

data class CertStatusResponse(
    val status: String?,
    val ambiente: String?
)

data class ChatRequest(
    val message: String
)

data class ChatResponse(
    val reply: String
)

interface SaboreApi {
    @POST("api/v1/auth/sync")
    suspend fun syncUser(@Body body: UserSyncRequest): Response<Unit>

    @GET("api/v1/recipes/public")
    suspend fun getPublicRecipes(): Response<List<Recipe>>

    @POST("api/v1/recipes")
    suspend fun createRecipe(@Body body: RecipeCreateRequest): Response<Recipe>

    @POST("api/v1/billing/pix")
    suspend fun createPixCharge(@Body body: PixRequest): Response<PixResponse>

    @GET("api/v1/billing/cert-status")
    suspend fun getCertStatus(): Response<CertStatusResponse>

    @POST("api/v1/assistant/chat")
    suspend fun chat(@Body body: ChatRequest): Response<ChatResponse>
}

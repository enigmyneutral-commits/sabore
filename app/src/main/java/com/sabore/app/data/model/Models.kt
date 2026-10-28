package com.sabore.app.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") val id: String,
    @SerializedName("email") val email: String,
    @SerializedName("displayName") val displayName: String?,
    @SerializedName("avatarUrl") val avatarUrl: String?,
    @SerializedName("isPro") val isPro: Boolean = false,
    @SerializedName("createdAt") val createdAt: String
)

data class AuthGoogleRequest(
    @SerializedName("idToken") val idToken: String,
    @SerializedName("accessToken") val accessToken: String? = null
)

data class AuthResponse(
    @SerializedName("accessToken") val accessToken: String,
    @SerializedName("refreshToken") val refreshToken: String,
    @SerializedName("user") val user: User,
    @SerializedName("isNewUser") val isNewUser: Boolean
)

data class RefreshTokenRequest(
    @SerializedName("refreshToken") val refreshToken: String
)

data class RefreshTokenResponse(
    @SerializedName("accessToken") val accessToken: String,
    @SerializedName("refreshToken") val refreshToken: String
)

data class SubscriptionStatusResponse(
    @SerializedName("tier") val tier: String, // "free" | "pro"
    @SerializedName("status") val status: String,
    @SerializedName("expiryTime") val expiryTime: String?
)

data class Recipe(
    @SerializedName("id") val id: String,
    @SerializedName("ownerId") val ownerId: String?,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String?,
    @SerializedName("category") val category: String?,
    @SerializedName("visibility") val visibility: String = "PRIVATE", // draft, private, shared, public, under_review, archived
    @SerializedName("prepTimeMinutes") val prepTimeMinutes: Int = 0,
    @SerializedName("cookTimeMinutes") val cookTimeMinutes: Int = 0,
    @SerializedName("servings") val servings: Int = 1,
    @SerializedName("difficulty") val difficulty: String = "easy", // easy, medium, hard
    @SerializedName("imageUrl") val imageUrl: String?,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("updatedAt") val updatedAt: String,
    @SerializedName("authorName") val authorName: String? = "Chef Saborê"
)

data class RecipeCreateRequest(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String?,
    @SerializedName("category") val category: String?,
    @SerializedName("visibility") val visibility: String,
    @SerializedName("prepTimeMinutes") val prepTimeMinutes: Int,
    @SerializedName("cookTimeMinutes") val cookTimeMinutes: Int,
    @SerializedName("servings") val servings: Int,
    @SerializedName("difficulty") val difficulty: String,
    @SerializedName("imageUrl") val imageUrl: String?
)

data class Cookbook(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String?,
    @SerializedName("recipeCount") val recipeCount: Int
)

data class FavoriteRecipe(
    @SerializedName("recipeId") val recipeId: String,
    @SerializedName("recipe") val recipe: Recipe?,
    @SerializedName("createdAt") val createdAt: String
)

data class CookingHistoryEntry(
    @SerializedName("id") val id: String,
    @SerializedName("recipeId") val recipeId: String,
    @SerializedName("cookedAt") val cookedAt: String,
    @SerializedName("notes") val notes: String?
)

data class AIRecipeSuggestionRequest(
    @SerializedName("ingredients") val ingredients: List<String>
)

data class AIRecipeSuggestionResponse(
    @SerializedName("suggestions") val suggestions: List<String>,
    @SerializedName("reply") val reply: String
)

data class AIChatRequest(
    @SerializedName("message") val message: String
)

data class AIChatResponse(
    @SerializedName("reply") val reply: String
)

data class PixPaymentResponse(
    @SerializedName("txid") val txid: String,
    @SerializedName("qrcode") val qrcode: String,
    @SerializedName("imagemQrcode") val imagemQrcode: String,
    @SerializedName("pixCopiaECola") val pixCopiaECola: String?,
    @SerializedName("valor") val valor: Any?,
    @SerializedName("status") val status: String
)

data class ErrorReportRequest(
    @SerializedName("message") val message: String,
    @SerializedName("type") val type: String,
    @SerializedName("stackTrace") val stackTrace: String?,
    @SerializedName("extra") val extra: String?,
    @SerializedName("platform") val platform: String = "android",
    @SerializedName("appVersion") val appVersion: String? = null
)

data class ErrorReportResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String?
)

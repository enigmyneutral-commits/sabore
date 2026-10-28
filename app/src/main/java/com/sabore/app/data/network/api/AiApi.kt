package com.sabore.app.data.network.api

import com.sabore.app.data.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AiApi {
    @POST("api/v1/ai/conversations")
    suspend fun createConversation(): Response<Map<String, String>>

    @POST("api/v1/ai/messages")
    suspend fun sendMessage(@Body request: AIChatRequest): Response<AIChatResponse>

    @POST("api/v1/ai/suggest-recipes")
    suspend fun suggestRecipes(@Body request: AIRecipeSuggestionRequest): Response<AIRecipeSuggestionResponse>

    @POST("api/v1/ai/substitute")
    suspend fun substituteIngredient(@Body request: Map<String, String>): Response<Map<String, String>>

    @POST("api/v1/ai/adjust-portions")
    suspend fun adjustPortions(@Body request: Map<String, Any>): Response<Map<String, Any>>

    @POST("api/v1/ai/cooking-help")
    suspend fun cookingHelp(@Body request: AIChatRequest): Response<AIChatResponse>
}

package com.sabore.app.data.network.api

import com.sabore.app.data.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {
    @POST("api/v1/auth/google")
    suspend fun googleLogin(@Body request: AuthGoogleRequest): Response<AuthResponse>

    @GET("api/v1/auth/me")
    suspend fun getCurrentUser(): Response<User>

    @POST("api/v1/auth/refresh")
    suspend fun refreshToken(@Body request: RefreshTokenRequest): Response<RefreshTokenResponse>

    @GET("api/v1/auth/status")
    suspend fun getSubscriptionStatus(): Response<SubscriptionStatusResponse>
}

package com.sabore.app.data.network.api

import com.sabore.app.data.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface SubscriptionsApi {
    @GET("api/v1/subscriptions/status")
    suspend fun getSubscriptionStatus(): Response<SubscriptionStatusResponse>

    @POST("api/v1/payments/pix")
    suspend fun createPixPayment(): Response<PixPaymentResponse>

    @GET("api/v1/subscriptions/plans")
    suspend fun getPlans(): Response<List<Map<String, Any>>>
}

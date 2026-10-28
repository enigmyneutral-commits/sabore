package com.sabore.app.data.network.efi

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.PUT

interface EfiPayApiService {

    @FormUrlEncoded
    @POST("/oauth/token")
    suspend fun getAccessToken(
        @Field("grant_type") grantType: String = "client_credentials"
    ): Response<EfiTokenResponse>

    @POST("/v2/cob")
    suspend fun createPixCharge(
        @Body request: EfiChargeRequest
    ): Response<EfiChargeResponse>
}

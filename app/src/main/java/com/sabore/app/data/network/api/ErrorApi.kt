package com.sabore.app.data.network.api

import com.sabore.app.data.model.ErrorReportRequest
import com.sabore.app.data.model.ErrorReportResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Contrato do endpoint de reporte de erros do app.
 * Toda função de rede da app deve ter seu contrato (interface Retrofit) declarado aqui.
 */
interface ErrorApi {
    @POST("api/v1/errors/report")
    suspend fun reportError(@Body body: ErrorReportRequest): Response<ErrorReportResponse>
}

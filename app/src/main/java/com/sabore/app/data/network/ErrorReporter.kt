package com.sabore.app.data.network

import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.util.concurrent.TimeUnit

/**
 * Canal de comunicação de reporte de erro.
 * Envia exceções capturadas para a API do backend
 * (POST https://api.dmgrecords.com.br/api/v1/errors/report) de forma assíncrona
 * e nunca lança exceção de dentro deste repórter, para não causar novo crash.
 */
object ErrorReporter {
    private const val BASE_URL = "https://api.dmgrecords.com.br"
    private const val ENDPOINT = "/api/v1/errors/report"
    private val JSON: MediaType = "application/json; charset=utf-8".toMediaType()

    private val client = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .build()

    /**
     * Reporta um erro para a API. Roda em uma thread daemon própria.
     */
    fun report(throwable: Throwable, extra: String? = null) {
        val thread = Thread {
            try {
                val stackTrace = throwable.stackTrace.joinToString("\n") { it.toString() }
                val typeName = throwable::class.simpleName ?: "Unknown"
                val payload = buildString {
                    append("{")
                    append("\"message\":\"${escape(throwable.message ?: typeName)}\"")
                    append(",\"type\":\"${escape(typeName)}\"")
                    append(",\"stackTrace\":\"${escape(stackTrace)}\"")
                    if (extra != null) append(",\"extra\":\"${escape(extra)}\"")
                    append("}")
                }
                val body = RequestBody.create(JSON, payload)
                val request = Request.Builder()
                    .url("$BASE_URL$ENDPOINT")
                    .post(body)
                    .header("Content-Type", "application/json")
                    .build()
                client.newCall(request).execute()
            } catch (_: Exception) {
                // Falha ao reportar: falha silenciosa, nunca propaga exceção.
            }
        }
        thread.isDaemon = true
        thread.start()
    }

    private fun escape(s: String): String =
        s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r")
}

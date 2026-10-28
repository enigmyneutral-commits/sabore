package com.sabore.app

import android.app.Application
import android.os.Process
import com.google.android.gms.ads.MobileAds
import com.sabore.app.data.network.ErrorReporter
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SaboreApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Google Mobile Ads SDK for Free Tier banner ads
        MobileAds.initialize(this) {}

        // Canal de reporte de erros: captura exceções não tratadas, envia para a API
        // e evita fechamento silencioso sem registro do erro.
        Thread.setDefaultUncaughtExceptionHandler { _, throwable ->
            ErrorReporter.report(throwable, "uncaught_exception")
            // Aguarda brevemente para permitir o envio do relatório antes de encerrar
            try { Thread.sleep(1500) } catch (_: InterruptedException) { Thread.currentThread().interrupt() }
            Process.killProcess(Process.myPid())
        }
    }
}

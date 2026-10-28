package com.sabore.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.sabore.app.ui.navigation.SaboreNavGraph
import com.sabore.app.ui.theme.SaboreTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Enable edge-to-edge rendering for modern displays (e.g. Redmi Note 14, Android 14+)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            SaboreTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    SaboreNavGraph(navController = navController)
                }
            }
        }
    }
}

// ⬤ PONTO DE LEITURA/REVISÃO CONCLUÍDA — sabore-app (MainActivity)
// Acesso e leitura completa de /run/media/deevo-financeira/AI_KNOWLEDGE/sabore/sabore-app
// Realizada em 28/10/2026. App Android (Kotlin + Jetpack Compose + Hilt + Room + Firebase + AdMob + Efí Pay).

package com.sabore.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sabore.app.ui.navigation.Screen
import com.sabore.app.ui.theme.PrimaryGreen
import com.sabore.app.ui.theme.SaboreIcon
import com.sabore.app.ui.theme.SaboreIcons
import com.sabore.app.ui.theme.WarmOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController
) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Meu Perfil & Configurações", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        SaboreIcon(SaboreIcons.ArrowBack, "Voltar")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
                    .background(PrimaryGreen.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "C",
                    style = MaterialTheme.typography.headlineMedium,
                    color = PrimaryGreen,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Cozinheiro(a) Saborê",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "cozinheiro@sabore.app",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate(Screen.ProSubscription.route) },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = WarmOrange.copy(alpha = 0.1f))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        SaboreIcon(SaboreIcons.Star, "Estrela", tintColor = WarmOrange)
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text("Saborê Pro", fontWeight = FontWeight.Bold, color = WarmOrange)
                            Text("Remova anúncios por R$ 4,99/mês", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                    Button(
                        onClick = { navController.navigate(Screen.ProSubscription.route) },
                        colors = ButtonDefaults.buttonColors(containerColor = WarmOrange)
                    ) {
                        Text("Ver Plano")
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            ProfileMenuItem(icon = SaboreIcons.SmartToy, title = "Assistente Culinário (Chatbot)") {
                navController.navigate(Screen.AssistantChat.route)
            }
            ProfileMenuItem(icon = SaboreIcons.Email, title = "Notificações") {
                navController.navigate(Screen.Notifications.route)
            }
            ProfileMenuItem(icon = SaboreIcons.Help, title = "Central de Ajuda e FAQ") {
                navController.navigate(Screen.Help.route)
            }

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedButton(
                onClick = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
            ) {
                SaboreIcon(SaboreIcons.ExitToApp, "Sair")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Sair da Conta")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "© 2026 DEEVO Soluções Financeiras LTDA\nCNPJ: 63.187.175/0001-70\nTodos os direitos reservados.",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}

@Composable
fun ProfileMenuItem(icon: String, title: String, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick),
        color = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SaboreIcon(icon, title, tintColor = PrimaryGreen)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = title, fontWeight = FontWeight.Medium, modifier = Modifier.weight(1f))
            SaboreIcon(SaboreIcons.ChevronRight, "Avançar", tintColor = Color.Gray)
        }
    }
}

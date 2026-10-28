package com.sabore.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sabore.app.ui.navigation.Screen
import com.sabore.app.ui.theme.PrimaryGreen
import com.sabore.app.ui.theme.SaboreIcon
import com.sabore.app.ui.theme.SaboreIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCookbookScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Meu Livro de Receitas", fontWeight = FontWeight.Bold, color = PrimaryGreen) }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.AddRecipe.route) },
                containerColor = PrimaryGreen,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                SaboreIcon(SaboreIcons.Add, "Adicionar Receita")
            }
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(Screen.Home.route) },
                    icon = { SaboreIcon(SaboreIcons.Home, "Início") },
                    label = { Text("Início") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(Screen.Explore.route) },
                    icon = { SaboreIcon(SaboreIcons.Search, "Explorar") },
                    label = { Text("Explorar") }
                )
                NavigationBarItem(
                    selected = true,
                    onClick = { },
                    icon = { SaboreIcon(SaboreIcons.Book, "Meu Livro", tintColor = PrimaryGreen) },
                    label = { Text("Meu Livro") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(Screen.Community.route) },
                    icon = { SaboreIcon(SaboreIcons.People, "Comunidade") },
                    label = { Text("Comunidade") }
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Seu livro está vazio",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Cadastre sua primeira receita privada ou salve da comunidade.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { navController.navigate(Screen.AddRecipe.route) },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
                ) {
                    SaboreIcon(SaboreIcons.Add, "Adicionar Receita")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Adicionar Receita")
                }
            }
        }
    }
}

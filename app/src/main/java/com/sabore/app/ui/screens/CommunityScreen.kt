package com.sabore.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sabore.app.ui.components.EmptyState
import com.sabore.app.ui.components.ErrorState
import com.sabore.app.ui.components.LoadingState
import com.sabore.app.ui.components.RecipeCard
import com.sabore.app.ui.navigation.Screen
import com.sabore.app.ui.theme.PrimaryGreen
import com.sabore.app.ui.theme.SaboreIcon
import com.sabore.app.ui.theme.SaboreIcons
import com.sabore.app.ui.viewmodel.RecipeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityScreen(
    navController: NavController
) {
    val recipeViewModel: RecipeViewModel = hiltViewModel()

    val recipes by recipeViewModel.recipes.collectAsState()
    val isLoading by recipeViewModel.isLoading.collectAsState()
    val error by recipeViewModel.error.collectAsState()

    LaunchedEffect(Unit) {
        recipeViewModel.loadPublicRecipes()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Comunidade Saborê", fontWeight = FontWeight.Bold, color = PrimaryGreen) },
                actions = {
                    IconButton(onClick = { recipeViewModel.loadPublicRecipes() }) {
                        SaboreIcon(SaboreIcons.Refresh, "Atualizar")
                    }
                }
            )
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
                    selected = true,
                    onClick = { },
                    icon = { SaboreIcon(SaboreIcons.People, "Comunidade", tintColor = PrimaryGreen) },
                    label = { Text("Comunidade") }
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                isLoading && recipes.isEmpty() -> {
                    LoadingState(message = "Buscando receitas públicas da comunidade...")
                }
                error != null && recipes.isEmpty() -> {
                    ErrorState(
                        errorMessage = error ?: "Erro ao carregar receitas",
                        onRetry = { recipeViewModel.loadPublicRecipes() }
                    )
                }
                recipes.isEmpty() -> {
                    EmptyState(
                        title = "Nenhuma receita na comunidade",
                        subtitle = "Seja o primeiro a compartilhar sua receita especial com outros chefs!",
                        icon = SaboreIcons.People
                    )
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(recipes) { recipe ->
                            RecipeCard(
                                recipe = recipe,
                                onClick = {
                                    navController.navigate(Screen.RecipeDetail.createRoute(recipe.id))
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

package com.sabore.app.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sabore.app.ui.components.CategoryCard
import com.sabore.app.ui.components.RecipeCard
import com.sabore.app.ui.navigation.Screen
import com.sabore.app.ui.theme.PrimaryGreen
import com.sabore.app.ui.theme.SaboreIcon
import com.sabore.app.ui.theme.SaboreIcons
import com.sabore.app.ui.viewmodel.RecipeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(
    navController: NavController
) {
    val recipeViewModel: RecipeViewModel = hiltViewModel()

    val categories = listOf(
        "Bolos e tortas", "Doces e sobremesas", "Confeitaria", "Pães e panificação",
        "Massas e pizzas", "Salgados e petiscos", "Carnes", "Aves",
        "Peixes e frutos do mar", "Comida brasileira", "Cozinha regional", "Gastronomia internacional",
        "Comida italiana", "Comida francesa", "Comida japonesa", "Comida mexicana",
        "Comida árabe", "Sopas e caldos", "Saladas e acompanhamentos", "Café da manhã",
        "Almoço e jantar", "Marmitas e congelados", "Receitas de forno", "Receitas para festas",
        "Receitas econômicas", "Receitas vegetarianas", "Receitas veganas", "Sem glúten",
        "Sem lactose", "Bebidas", "Conservas e fermentação", "Molhos e temperos",
        "Receitas para vender", "Tradições de família"
    )

    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var searchQuery by remember { mutableStateOf("") }
    var showFilterDialog by remember { mutableStateOf(false) }
    var selectedMaxTime by remember { mutableStateOf(60) }
    var selectedDifficulty by remember { mutableStateOf("Todas") }

    val recipes by recipeViewModel.recipes.collectAsState()
    val isLoading by recipeViewModel.isLoading.collectAsState()

    LaunchedEffect(selectedCategory) {
        recipeViewModel.loadPublicRecipes(category = selectedCategory)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Explorar Categorias", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        SaboreIcon(SaboreIcons.ArrowBack, "Voltar")
                    }
                },
                actions = {
                    IconButton(onClick = { showFilterDialog = true }) {
                        SaboreIcon(SaboreIcons.ListAlt, "Filtros")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Buscar receitas, ingredientes...") },
                    leadingIcon = { SaboreIcon(SaboreIcons.Search, null) },
                    trailingIcon = {
                        IconButton(onClick = { showFilterDialog = true }) {
                            SaboreIcon(SaboreIcons.ListAlt, "Filtros")
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = selectedCategory?.let { "Filtrado por: $it" } ?: "Categorias Gastronômicas",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    if (selectedCategory != null) {
                        TextButton(onClick = { selectedCategory = null }) {
                            Text("Limpar", color = PrimaryGreen)
                        }
                    }
                }
            }

            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    categories.chunked(2).forEach { rowCategories ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            rowCategories.forEach { category ->
                                Box(modifier = Modifier.weight(1f)) {
                                    CategoryCard(
                                        categoryName = category,
                                        isSelected = selectedCategory == category,
                                        onClick = {
                                            selectedCategory = if (selectedCategory == category) null else category
                                        }
                                    )
                                }
                            }
                            if (rowCategories.size == 1) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = selectedCategory?.let { "Receitas em '$it'" } ?: "Todas as Receitas",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            if (isLoading) {
                item {
                    Box(modifier = Modifier.fillMaxWidth().padding(32.dp), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = PrimaryGreen)
                    }
                }
            } else if (recipes.isEmpty()) {
                item {
                    Box(modifier = Modifier.fillMaxWidth().padding(32.dp), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Nenhuma receita encontrada.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                }
            } else {
                val filteredRecipes = recipes.filter { recipe ->
                    val matchesSearch = searchQuery.isBlank() ||
                            recipe.title.contains(searchQuery, ignoreCase = true) ||
                            (recipe.description?.contains(searchQuery, ignoreCase = true) == true)
                    val matchesTime = (recipe.prepTimeMinutes + recipe.cookTimeMinutes) <= selectedMaxTime
                    matchesSearch && matchesTime
                }

                items(filteredRecipes.size) { index ->
                    val recipe = filteredRecipes[index]
                    RecipeCard(
                        recipe = recipe,
                        onClick = {
                            navController.navigate(Screen.RecipeDetail.createRoute(recipe.id))
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }

    if (showFilterDialog) {
        AlertDialog(
            onDismissRequest = { showFilterDialog = false },
            title = { Text("Filtrar Receitas", fontWeight = FontWeight.Bold) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("Tempo máximo de preparo: $selectedMaxTime min")
                    Slider(
                        value = selectedMaxTime.toFloat(),
                        onValueChange = { selectedMaxTime = it.toInt() },
                        valueRange = 10f..120f,
                        steps = 10,
                        colors = SliderDefaults.colors(thumbColor = PrimaryGreen, activeTrackColor = PrimaryGreen)
                    )
                    Text("Dificuldade", fontWeight = FontWeight.Bold)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        listOf("Todas", "Fácil", "Média", "Difícil").forEach { diff ->
                            FilterChip(
                                selected = selectedDifficulty == diff,
                                onClick = { selectedDifficulty = diff },
                                label = { Text(diff) }
                            )
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = { showFilterDialog = false },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
                ) {
                    Text("Aplicar Filtros")
                }
            },
            dismissButton = {
                TextButton(onClick = { showFilterDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}

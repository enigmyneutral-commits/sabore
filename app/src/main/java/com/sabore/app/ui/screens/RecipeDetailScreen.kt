package com.sabore.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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
import com.sabore.app.ui.theme.PrimaryGreen
import com.sabore.app.ui.theme.SaboreIcon
import com.sabore.app.ui.theme.SaboreIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(
    recipeId: String,
    navController: NavController,
    onStartCooking: (String) -> Unit,
    onNavigateBack: () -> Unit
) {
    val scrollState = rememberScrollState()
    var servings by remember { mutableStateOf(4) }
    var isFollowingChef by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalhes da Receita") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        SaboreIcon(SaboreIcons.ArrowBack, "Voltar")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Save to cookbook */ }) {
                        SaboreIcon(SaboreIcons.Bookmark, "Salvar")
                    }
                    IconButton(onClick = { /* Share recipe */ }) {
                        SaboreIcon(SaboreIcons.Share, "Compartilhar")
                    }
                }
            )
        },
        bottomBar = {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                tonalElevation = 8.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(
                        onClick = { /* Save action */ },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        SaboreIcon(SaboreIcons.Bookmark, null, tintColor = PrimaryGreen)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Salvar", color = PrimaryGreen, fontWeight = FontWeight.Bold)
                    }

                    Button(
                        onClick = { onStartCooking(recipeId) },
                        modifier = Modifier
                            .weight(1.5f)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        SaboreIcon(SaboreIcons.PlayArrow, null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Modo de preparo", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(PrimaryGreen.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Text("Bolo de Chocolate Fofinho", style = MaterialTheme.typography.titleMedium, color = PrimaryGreen, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Bolo de Chocolate Fofinho",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Author & Follow row
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(PrimaryGreen),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("AP", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text("Ana Paula", fontWeight = FontWeight.Bold)
                            Text("⭐ 4.9 (1.2k avaliações)", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                        }
                    }
                    Button(
                        onClick = { isFollowingChef = !isFollowingChef },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isFollowingChef) MaterialTheme.colorScheme.surface else PrimaryGreen
                        ),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Text(
                            text = if (isFollowingChef) "Seguindo" else "Seguir",
                            color = if (isFollowingChef) PrimaryGreen else Color.White
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                InfoBadge(label = "Preparo", value = "50 min")
                InfoBadge(label = "Forno", value = "40 min")
                InfoBadge(label = "Porções", value = "$servings")
                InfoBadge(label = "Dificuldade", value = "Fácil")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Ingredientes",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                // Portion adjusters
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { if (servings > 1) servings-- }) {
                        Text("➖", fontWeight = FontWeight.Bold)
                    }
                    Text("$servings porções", fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 4.dp))
                    IconButton(onClick = { servings++ }) {
                        Text("➕", fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            val baseIngredients = listOf(
                "3 ovos",
                "1 xícara de açúcar",
                "1/2 xícara de óleo",
                "1 xícara de leite",
                "2 xícaras de farinha de trigo",
                "1 xícara de chocolate em pó",
                "1 colher de sopa de fermento"
            )

            baseIngredients.forEach { ingredient ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = false, onCheckedChange = {})
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = ingredient, style = MaterialTheme.typography.bodyLarge)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Modo de Preparo",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            val steps = listOf(
                "Bata os ovos, o açúcar e o óleo.",
                "Adicione o leite e a farinha.",
                "Misture o chocolate e o fermento.",
                "Asse em forno pré-aquecido a 180°C por 40 minutos."
            )

            steps.forEachIndexed { index, step ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = PrimaryGreen.copy(alpha = 0.1f),
                        modifier = Modifier.size(28.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(text = "${index + 1}", color = PrimaryGreen, fontWeight = FontWeight.Bold)
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = step,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun InfoBadge(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        Text(text = value, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
    }
}

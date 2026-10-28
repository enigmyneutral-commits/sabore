package com.sabore.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sabore.app.data.model.RecipeCreateRequest
import com.sabore.app.ui.theme.PrimaryGreen
import com.sabore.app.ui.theme.SaboreIcon
import com.sabore.app.ui.theme.SaboreIcons
import com.sabore.app.ui.viewmodel.RecipeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRecipeScreen(
    onRecipeSaved: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val recipeViewModel: RecipeViewModel = hiltViewModel()

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("Bolos e tortas") }
    var ingredients by remember { mutableStateOf("") }
    var steps by remember { mutableStateOf("") }
    var isPrivate by remember { mutableStateOf(true) }
    var validationError by remember { mutableStateOf<String?>(null) }

    val scrollState = rememberScrollState()
    val isLoading by recipeViewModel.isLoading.collectAsState()
    val error by recipeViewModel.error.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cadastrar Receita", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
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
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val displayError = validationError ?: error
            displayError?.let { msg ->
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.errorContainer
                ) {
                    Text(
                        text = msg,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onErrorContainer,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }

            Text(
                text = "Cadastre sua receita para guardar no seu livro ou compartilhar na comunidade Saborê.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )

            OutlinedTextField(
                value = title,
                onValueChange = { title = it; validationError = null },
                label = { Text("Nome da Receita *") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descrição Breve") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            OutlinedTextField(
                value = category,
                onValueChange = { category = it },
                label = { Text("Categoria *") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            OutlinedTextField(
                value = ingredients,
                onValueChange = { ingredients = it; validationError = null },
                label = { Text("Ingredientes (um por linha) *") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                shape = RoundedCornerShape(12.dp)
            )

            OutlinedTextField(
                value = steps,
                onValueChange = { steps = it; validationError = null },
                label = { Text("Modo de Preparo (passo a passo) *") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                shape = RoundedCornerShape(12.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Manter Privada", fontWeight = FontWeight.Bold)
                    Text("Somente você poderá ver esta receita no seu livro", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                }
                Switch(
                    checked = isPrivate,
                    onCheckedChange = { isPrivate = it }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    when {
                        title.isBlank() -> validationError = "Informe o nome da receita."
                        category.isBlank() -> validationError = "Informe a categoria da receita."
                        ingredients.isBlank() -> validationError = "Informe ao menos um ingrediente."
                        steps.isBlank() -> validationError = "Informe o modo de preparo."
                        else -> {
                            validationError = null
                            recipeViewModel.createRecipe(
                                RecipeCreateRequest(
                                    title = title,
                                    description = description.takeIf { it.isNotBlank() },
                                    category = category,
                                    visibility = if (isPrivate) "PRIVATE" else "PUBLIC",
                                    prepTimeMinutes = 15,
                                    cookTimeMinutes = 30,
                                    servings = 4,
                                    difficulty = "Média",
                                    imageUrl = null
                                )
                            ) { created ->
                                if (created != null) {
                                    onRecipeSaved()
                                }
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                enabled = !isLoading,
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
                shape = RoundedCornerShape(12.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                } else {
                    Text("Salvar Receita", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
                }
            }
        }
    }
}

package com.sabore.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sabore.app.ui.theme.PrimaryGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpScreen(
    navController: NavController
) {
    val faqs = listOf(
        "Como cadastrar uma receita" to "No seu Livro Pessoal, toque no botão '+' para abrir o formulário de cadastro. Sua receita será salva como privada por padrão.",
        "Como publicar uma receita na comunidade" to "Acesse sua receita privada, revise o conteúdo e selecione 'Publicar na comunidade'. Confirme a ação para torná-la pública.",
        "Como gerenciar o Saborê Pro" to "Acesse seu perfil e selecione 'Saborê Pro' para assinar e remover anúncios por R$ 4,99/mês.",
        "Privacidade e LGPD" to "Suas receitas privadas são visíveis apenas por você. Você pode solicitar exportação ou exclusão de dados a qualquer momento nas configurações."
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Central de Ajuda", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
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
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    text = "Perguntas Frequentes (FAQ)",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryGreen
                )
            }
            items(faqs) { (question, answer) ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = question, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = answer, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
                    }
                }
            }
        }
    }
}

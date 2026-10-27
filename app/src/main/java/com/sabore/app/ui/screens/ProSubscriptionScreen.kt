package com.sabore.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sabore.app.data.network.efi.EfiPayRepository
import com.sabore.app.ui.theme.PrimaryGreen
import com.sabore.app.ui.theme.WarmOrange
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProSubscriptionScreen(
    onNavigateBack: () -> Unit
) {
    var selectedMethod by remember { mutableStateOf("pix") } // "pix", "pix_scheduled", "card"
    var isLoading by remember { mutableStateOf(false) }
    var paymentSuccessMessage by remember { mutableStateOf<String?>(null) }
    
    // Card fields
    var cardNumber by remember { mutableStateOf("") }
    var cardCvv by remember { mutableStateOf("") }
    var cardExpiry by remember { mutableStateOf("") }
    var cardHolder by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val efiRepository = remember { EfiPayRepository() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Saborê Pro - Checkout") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
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
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = WarmOrange,
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Assinatura Saborê Pro",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = "R$ 4,99 / mês • Sem anúncios",
                style = MaterialTheme.typography.bodyLarge,
                color = WarmOrange,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Payment Method Selector
            Text(text = "Escolha a forma de pagamento:", fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Start))
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = selectedMethod == "pix",
                    onClick = { selectedMethod = "pix" },
                    label = { Text("Pix") },
                    modifier = Modifier.weight(1f)
                )
                FilterChip(
                    selected = selectedMethod == "pix_scheduled",
                    onClick = { selectedMethod = "pix_scheduled" },
                    label = { Text("Pix Agendado") },
                    modifier = Modifier.weight(1f)
                )
                FilterChip(
                    selected = selectedMethod == "card",
                    onClick = { selectedMethod = "card" },
                    label = { Text("Cartão") },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (selectedMethod == "card") {
                OutlinedTextField(
                    value = cardNumber,
                    onValueChange = { cardNumber = it },
                    label = { Text("Número do Cartão") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedTextField(
                        value = cardExpiry,
                        onValueChange = { cardExpiry = it },
                        label = { Text("Validade (MM/AA)") },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = cardCvv,
                        onValueChange = { cardCvv = it },
                        label = { Text("CVV") },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    value = cardHolder,
                    onValueChange = { cardHolder = it },
                    label = { Text("Nome do Titular") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )
            } else {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = PrimaryGreen.copy(alpha = 0.1f)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(Icons.Default.QrCode, contentDescription = null, tint = PrimaryGreen, modifier = Modifier.size(48.dp))
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = if (selectedMethod == "pix") "Cobrança Pix Imediata (Efí Pay)" else "Cobrança Pix com Vencimento / Agendado (Efí Pay)",
                            fontWeight = FontWeight.Bold,
                            color = PrimaryGreen,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("O QR Code e o código Pix Copia e Cola serão gerados instantaneamente.", style = MaterialTheme.typography.bodySmall, textAlign = TextAlign.Center)
                    }
                }
            }

            if (paymentSuccessMessage != null) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = paymentSuccessMessage!!,
                    color = PrimaryGreen,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    isLoading = true
                    paymentSuccessMessage = null
                    coroutineScope.launch {
                        val result = when (selectedMethod) {
                            "pix" -> efiRepository.generateProSubscriptionPix()
                            "pix_scheduled" -> efiRepository.generateScheduledPixSubscription("2026-12-31")
                            "card" -> efiRepository.processCreditCardSubscription(cardNumber, cardCvv, "12", "28", cardHolder)
                            else -> efiRepository.generateProSubscriptionPix()
                        }
                        isLoading = false
                        if (result.isSuccess) {
                            paymentSuccessMessage = "Assinatura Saborê Pro criada com sucesso! Benefícios ativados."
                        } else {
                            paymentSuccessMessage = "Erro ao processar pagamento: ${result.exceptionOrNull()?.message}"
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
                shape = RoundedCornerShape(12.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.size(24.dp))
                } else {
                    Icon(Icons.Default.CreditCard, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Concluir Assinatura (R$ 4,99/mês)",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

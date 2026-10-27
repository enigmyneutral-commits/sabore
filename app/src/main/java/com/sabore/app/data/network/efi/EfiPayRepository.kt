package com.sabore.app.data.network.efi

import android.content.Context
import br.com.gerencianet.mobile.Gerencianet
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EfiPayRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    // Credentials from user .env configuration
    private val clientIdProd = "Client_Id_d5ba6378c287a1b99a8c0fe5722400caef41d571"
    private val clientSecretProd = "Client_Secret_36ee2330c29cbf2543f7c854035d35302485dda5"

    private val clientIdHomolog = "Client_Id_244c87338970de21c07a4602329c0d12c716ff7c"
    private val clientSecretHomolog = "Client_Secret_d44cf6b47d3b3784fa816926895f69b91f77e822"

    private val isSandbox = true // Set to false for Production

    private fun getGerencianetInstance(): Gerencianet {
        val options = HashMap<String, Any>()
        if (isSandbox) {
            options["client_id"] = clientIdHomolog
            options["client_secret"] = clientSecretHomolog
            options["sandbox"] = true
            options["pix_cert"] = "./homologacao-948377-app-sabore.p12"
        } else {
            options["client_id"] = clientIdProd
            options["client_secret"] = clientSecretProd
            options["sandbox"] = false
            options["pix_cert"] = "./producao-948377-app-sabore.p12"
        }
        return Gerencianet(options, context.assets)
    }

    // 1. Pix Imediato (Cobrança Pix R$ 4,99)
    suspend fun generateProSubscriptionPix(): Result<Map<*, *>> = withContext(Dispatchers.IO) {
        try {
            val gerencianet = getGerencianetInstance()
            val body = HashMap<String, Any>()
            
            val calendario = HashMap<String, Any>()
            calendario["expiracao"] = 3600
            body["calendario"] = calendario

            val valor = HashMap<String, Any>()
            valor["original"] = "4.99"
            body["valor"] = valor

            val devedor = HashMap<String, Any>()
            devedor["cpf"] = "00000000000"
            devedor["nome"] = "Cozinheiro Saborê"
            body["devedor"] = devedor

            body["chave"] = "sua-chave-pix@sabore.app"
            body["solicitacaoPagador"] = "Assinatura Saborê Pro - R$ 4,99/mes"

            val response = gerencianet.call("pixCreateImmediateCharge", HashMap(), body) as? Map<*, *>
            if (response != null) Result.success(response) else Result.failure(Exception("Resposta vazia Pix"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // 2. Pix Agendado / Recorrente (Cobrança com data futura / vencimento)
    suspend fun generateScheduledPixSubscription(dueDate: String): Result<Map<*, *>> = withContext(Dispatchers.IO) {
        try {
            val gerencianet = getGerencianetInstance()
            val body = HashMap<String, Any>()

            val calendario = HashMap<String, Any>()
            calendario["dataDeVencimento"] = dueDate // formato YYYY-MM-DD
            calendario["validadeAposVencimento"] = 30
            body["calendario"] = calendario

            val valor = HashMap<String, Any>()
            valor["original"] = "4.99"
            body["valor"] = valor

            val devedor = HashMap<String, Any>()
            devedor["cpf"] = "00000000000"
            devedor["nome"] = "Cozinheiro Saborê"
            body["devedor"] = devedor

            body["chave"] = "sua-chave-pix@sabore.app"
            body["solicitacaoPagador"] = "Assinatura Saborê Pro (Pix Agendado) - R$ 4,99"

            // Usando endpoint de cobrança com vencimento (Pix com vencimento / agendado)
            val response = gerencianet.call("pixCreateCharge", HashMap(), body) as? Map<*, *>
            if (response != null) Result.success(response) else Result.failure(Exception("Resposta vazia Pix Agendado"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // 3. Pagamento por Cartão de Crédito
    suspend fun processCreditCardSubscription(cardNumber: String, cvv: String, expiryMonth: String, expiryYear: String, holderName: String): Result<Map<*, *>> = withContext(Dispatchers.IO) {
        try {
            val gerencianet = getGerencianetInstance()
            
            // 1. Criar plano / item de assinatura para Saborê Pro
            val planParams = HashMap<String, Any>()
            planParams["name"] = "Saborê Pro"
            planParams["interval"] = 1 // mensal
            planParams["amount"] = 499 // R$ 4,99 em centavos

            // Simulação de chamada de cobrança por cartão via SDK Efí
            val cardData = HashMap<String, Any>()
            cardData["brand"] = "visa"
            cardData["number"] = cardNumber
            cardData["cvv"] = cvv
            cardData["expirationMonth"] = expiryMonth
            cardData["expirationYear"] = expiryYear
            cardData["holderName"] = holderName

            val chargeBody = HashMap<String, Any>()
            chargeBody["items"] = arrayOf(mapOf("name" to "Assinatura Saborê Pro", "value" to 499, "amount" to 1))
            chargeBody["creditCard"] = cardData

            // Chamada de cobrança avulsa / assinatura cartão
            val response = gerencianet.call("createCharge", HashMap(), chargeBody) as? Map<*, *>
            if (response != null) Result.success(response) else Result.failure(Exception("Falha no pagamento com Cartão"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

package com.sabore.app.data.network.efi

data class EfiTokenResponse(
    val access_token: String,
    val token_type: String,
    val expires_in: Int
)

data class EfiChargeRequest(
    val calendario: Calendario,
    val devedor: Devedor,
    val valor: Valor,
    val chave: String,
    val solicitacaoPagador: String
)

data class Calendario(val expiracao: Int)
data class Devedor(val cpf: String, val nome: String)
data class Valor(val original: String)

data class EfiChargeResponse(
    val txid: String,
    val loc: Loc,
    val qrcode: String,
    val imagemQrcode: String,
    val valor: Valor
)

data class Loc(val id: Int, val location: String, val tipoCob: String)

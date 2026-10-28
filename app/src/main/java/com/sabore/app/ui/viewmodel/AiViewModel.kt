package com.sabore.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sabore.app.data.network.api.AiApi
import com.sabore.app.data.model.AIChatRequest
import com.sabore.app.data.model.AIChatResponse
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel

data class ChatMessage(
    val text: String,
    val isUser: Boolean,
    val timestamp: Long = System.currentTimeMillis()
)

@HiltViewModel
class AiViewModel @Inject constructor(
    private val aiApi: AiApi
) : ViewModel() {

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    init {
        // Mensagem de boas-vindas do assistente
        _messages.value = listOf(
            ChatMessage("Olá! Sou o assistente culinário do Saborê. Pergunte-me qualquer coisa sobre receitas, ingredientes ou modo de preparo!", isUser = false)
        )
    }

    fun sendMessage(message: String) {
        if (message.isBlank()) return

        viewModelScope.launch {
            // Adicionar mensagem do usuário
            val userMessage = ChatMessage(text = message, isUser = true)
            _messages.value = _messages.value + userMessage

            _isLoading.value = true
            _error.value = null

            try {
                val response = aiApi.sendMessage(AIChatRequest(message = message))
                if (response.isSuccessful && response.body() != null) {
                    val reply = response.body()!!.reply
                    _messages.value = _messages.value + ChatMessage(text = reply, isUser = false)
                } else {
                    _error.value = "Erro ao obter resposta do assistente"
                    _messages.value = _messages.value + ChatMessage(
                        text = "Desculpe, não consegui processar sua pergunta no momento. Tente novamente.",
                        isUser = false
                    )
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Erro ao conectar com o assistente"
                _messages.value = _messages.value + ChatMessage(
                    text = "Desculpe, houve um erro de conexão. Verifique sua internet.",
                    isUser = false
                )
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearConversation() {
        _messages.value = listOf(
            ChatMessage("Olá! Sou o assistente culinário do Saborê. Pergunte-me qualquer coisa sobre receitas, ingredientes ou modo de preparo!", isUser = false)
        )
        _error.value = null
    }
}

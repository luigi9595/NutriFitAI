package com.example.nutrifitai.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutrifitai.data.api.ApiClient
import com.example.nutrifitai.data.api.ChatMessage
import com.example.nutrifitai.data.api.ChatRequest
import com.example.nutrifitai.data.models.Message
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> = _messages.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val systemPrompt = """Sei NutriFit AI, un assistente esperto in nutrizione e fitness. 
    Il tuo compito è aiutare gli utenti con consigli personalizzati su dieta, alimentazione sana e allenamento fisico.
    Fornisci risposte precise, pratiche e basate su evidenze scientifiche.
    Usa un tono amichevole ma professionale. Rispondi sempre in italiano."""
    
    init {
        // Nessun messaggio iniziale per evitare problemi con l'alternanza
        _messages.value = emptyList()
    }
    
    fun clearMessages() {
        _messages.value = emptyList()
    }
    
    fun sendMessage(userMessage: String) {
        viewModelScope.launch {
            // Aggiungi messaggio utente
            val userMsg = Message(content = userMessage, isUser = true)
            _messages.value = _messages.value + userMsg
            
            _isLoading.value = true
            
            try {
                // Costruisci la conversazione per l'API in modo semplice
                val apiMessages = mutableListOf<ChatMessage>()
                
                // Aggiungi il system prompt come primo messaggio
                apiMessages.add(ChatMessage(role = "system", content = systemPrompt))
                
                // Aggiungi gli ultimi messaggi della conversazione (escluso l'ultimo che abbiamo già aggiunto)
                val recentMessages = _messages.value.dropLast(1).takeLast(6)
                recentMessages.forEach { msg ->
                    apiMessages.add(
                        ChatMessage(
                            role = if (msg.isUser) "user" else "assistant",
                            content = msg.content
                        )
                    )
                }
                
                // Aggiungi il messaggio corrente dell'utente
                apiMessages.add(ChatMessage(role = "user", content = userMessage))
                
                // Invia richiesta a LM Studio
                val response = ApiClient.lmStudioApi.sendMessage(
                    ChatRequest(messages = apiMessages)
                )
                
                // Estrai risposta
                val aiResponse = response.choices.firstOrNull()?.message?.content
                    ?: "Mi dispiace, non sono riuscito a elaborare la tua richiesta."
                
                // Aggiungi risposta AI
                val aiMsg = Message(content = aiResponse, isUser = false)
                _messages.value = _messages.value + aiMsg
                
            } catch (e: Exception) {
                // Gestione errori con dettagli
                val errorDetails = when {
                    e.message?.contains("timeout", ignoreCase = true) == true -> 
                        "Timeout nella connessione. Il server potrebbe essere lento."
                    e.message?.contains("Unable to resolve host", ignoreCase = true) == true -> 
                        "Impossibile raggiungere il server. Verifica la connessione."
                    e.message?.contains("failed to connect", ignoreCase = true) == true -> 
                        "Connessione fallita. Il server potrebbe essere offline."
                    else -> "Errore: ${e.message ?: "Errore sconosciuto"}"
                }
                
                val errorMsg = Message(
                    content = errorDetails,
                    isUser = false
                )
                _messages.value = _messages.value + errorMsg
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
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
        // Messaggio di benvenuto
        _messages.value = listOf(
            Message(
                content = "Ciao! Sono NutriFit AI, il tuo assistente personale per nutrizione e fitness. Come posso aiutarti oggi?",
                isUser = false
            )
        )
    }
    
    fun sendMessage(userMessage: String) {
        viewModelScope.launch {
            // Aggiungi messaggio utente
            val userMsg = Message(content = userMessage, isUser = true)
            _messages.value = _messages.value + userMsg
            
            _isLoading.value = true
            
            try {
                // Costruisci la conversazione per l'API
                val apiMessages = mutableListOf(
                    ChatMessage(role = "system", content = systemPrompt)
                )
                
                // Aggiungi ultimi messaggi per contesto (max 10)
                _messages.value.takeLast(10).forEach { msg ->
                    apiMessages.add(
                        ChatMessage(
                            role = if (msg.isUser) "user" else "assistant",
                            content = msg.content
                        )
                    )
                }
                
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
                // Gestione errori
                val errorMsg = Message(
                    content = "Errore di connessione. Assicurati che LM Studio sia in esecuzione su localhost:1234",
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
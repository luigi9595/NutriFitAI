package com.example.nutrifitai.data.api

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Headers

interface LMStudioApi {
    @Headers("Content-Type: application/json")
    @POST("v1/chat/completions")
    suspend fun sendMessage(@Body request: ChatRequest): ChatResponse
}

data class ChatRequest(
    val model: String = "google/gemma-3-27b",
    val messages: List<ChatMessage>,
    val temperature: Double = 0.7,
    val max_tokens: Int = 500,  // Ridotto per risposte più veloci
    val stream: Boolean = false
)

data class ChatMessage(
    val role: String,
    val content: String
)

data class ChatResponse(
    val id: String,
    val choices: List<Choice>,
    val created: Long,
    val model: String
)

data class Choice(
    val index: Int,
    val message: ChatMessage,
    val finish_reason: String?
)
package com.example.nutrifitai.data.api

import com.example.nutrifitai.utils.Constants

// Placeholder per futura integrazione Supabase
object SupabaseClient {
    const val URL = Constants.SUPABASE_URL
    const val ANON_KEY = Constants.SUPABASE_ANON_KEY
    
    // TODO: Implementare client Supabase quando necessario
    // Per ora manteniamo solo le costanti per future integrazioni
    
    fun isConfigured(): Boolean {
        return URL.isNotEmpty() && ANON_KEY.isNotEmpty()
    }
}
package com.example.nutrifitai.data.api

import com.example.nutrifitai.utils.Constants

// Placeholder per futura integrazione Stripe
object StripeClient {
    const val PUBLISHABLE_KEY = Constants.STRIPE_PUBLISHABLE_KEY
    
    // TODO: Implementare integrazione Stripe quando necessario
    // Per ora manteniamo solo la chiave per future integrazioni
    
    // Prodotti Stripe configurati:
    // - NutriFitAI Pro (ID: prod_SWQnlrkzpjbyfh)
    // - NutriFitAI Elite (ID: prod_SWQnXjYrQlyPgo)
    
    // Prezzi configurati:
    // - Pro Monthly: €9.99 (ID: price_1RbNwTHqxg0D6seKQTW1ie8J)
    // - Pro Yearly: €99.99 (ID: price_1RbZNOHqxg0D6seKuWI48C6y)
    // - Elite Monthly: €19.99 (ID: price_1RbOLRHqxg0D6seKQmKy4TAo)
    // - Elite Yearly: €199.99 (ID: price_1Rba5QHqxg0D6seKLpSBGmZV)
    
    fun isConfigured(): Boolean {
        return PUBLISHABLE_KEY.isNotEmpty()
    }
}
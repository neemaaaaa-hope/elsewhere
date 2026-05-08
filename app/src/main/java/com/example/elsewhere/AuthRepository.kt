package com.example.elsewhere

import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import com.example.elsewhere.data.SupabaseClientInstance

object AuthRepository {

    private val client = SupabaseClientInstance.client

    suspend fun signUp(email: String, password: String) {
        client.auth.signUpWith(Email) {
            this.email = email
            this.password = password
        }
    }

    suspend fun signIn(email: String, password: String) {
        client.auth.signInWith(Email) {
            this.email = email
            this.password = password
        }
    }

    suspend fun signOut() {
        client.auth.signOut()
    }

    fun isLoggedIn(): Boolean {
        return client.auth.currentUserOrNull() != null
    }
}


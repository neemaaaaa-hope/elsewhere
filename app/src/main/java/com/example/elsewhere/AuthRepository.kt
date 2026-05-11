package com.example.elsewhere

import com.example.elsewhere.data.SupabaseClientInstance
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email

object AuthRepository {

    private val client = SupabaseClientInstance.client

    suspend fun signUp(email: String, password: String): Result<Unit> {
        return try {
            client.auth.signUpWith(Email) {
                this.email = email
                this.password = password
            }

            // force session check
            val session = client.auth.currentSessionOrNull()

            if (session == null) {
                return Result.failure(Exception("Signup failed: no session created"))
            }

            Result.success(Unit)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun signIn(email: String, password: String): Result<Unit> {
        return try {
            client.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }

            val session = client.auth.currentSessionOrNull()

            if (session == null) {
                return Result.failure(Exception("Login failed: no session"))
            }

            Result.success(Unit)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun signOut() {
        client.auth.signOut()
    }

    fun getCurrentUserEmail(): String? {
        return client.auth.currentSessionOrNull()?.user?.email
    }

    suspend fun signInWithGoogle() {
        // Stub for demo purposes
    }
}
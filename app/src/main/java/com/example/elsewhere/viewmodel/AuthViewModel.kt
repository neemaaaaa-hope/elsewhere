package com.example.elsewhere.viewmodel



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elsewhere.data.SupabaseClientInstance
import io.github.jan.supabase.auth.auth
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val client = SupabaseClientInstance.client

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            client.auth.signUpWith(io.github.jan.supabase.auth.providers.builtin.Email) {
                this.email = email
                this.password = password
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            client.auth.signInWith(io.github.jan.supabase.auth.providers.builtin.Email) {
                this.email = email
                this.password = password
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            client.auth.signOut()
        }
    }
}
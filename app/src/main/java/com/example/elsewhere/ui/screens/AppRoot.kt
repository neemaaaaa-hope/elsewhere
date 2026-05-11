package com.example.elsewhere.ui.screens

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.elsewhere.AuthRepository
import kotlinx.coroutines.launch

@Composable
fun AppRoot() {

    var screen by rememberSaveable { mutableStateOf("splash") }
    var selectedTab by rememberSaveable { mutableStateOf("home") }

    when (screen) {

        // ─────────────────────────────
        // SPLASH
        // ─────────────────────────────
        "splash" -> SplashScreen(
            onSplashFinished = { screen = "onboarding" }
        )

        // ─────────────────────────────
        // ONBOARDING
        // ─────────────────────────────
        "onboarding" -> OnboardingScreen(
            onContinue = { screen = "login" }
        )

        // ─────────────────────────────
        // LOGIN (SAFE + GOOGLE STUB)
        // ─────────────────────────────
        "login" -> LoginScreen(
            onGoRegister = { screen = "register" },
            onLogin = {
                selectedTab = "home"
                screen = "home"
            },
            onGoogleLogin = {
                // SAFE DEMO BEHAVIOUR (no backend dependency risk)
                selectedTab = "home"
                screen = "home"
            }
        )

        // ─────────────────────────────
        // REGISTER (REAL AUTH FLOW)
        // ─────────────────────────────
        "register" -> RegisterScreen(
            onGoLogin = { screen = "login" },
            onRegisterSuccess = {
                selectedTab = "home"
                screen = "home"
            }
        )

        // ─────────────────────────────
        // HOME
        // ─────────────────────────────
        "home" -> {
            val scope = rememberCoroutineScope()
            HomeScreen(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it },
                onLogout = {
                    scope.launch {
                        AuthRepository.signOut()
                        selectedTab = "home"
                        screen = "login"
                    }
                }
            )
        }
    }
}
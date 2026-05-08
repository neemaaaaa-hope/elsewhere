package com.example.elsewhere.ui.screens

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun AppRoot() {

    var screen by rememberSaveable { mutableStateOf("splash") }
    var selectedTab by rememberSaveable { mutableStateOf("home") }

    when (screen) {

        "splash" -> SplashScreen(
            onSplashFinished = { screen = "onboarding" }
        )

        "onboarding" -> OnboardingScreen(
            onContinue = { screen = "login" }
        )

        "login" -> LoginScreen(
            onGoRegister = { screen = "register" },
            onLogin = { screen = "home" }
        )

        "register" -> RegisterScreen(
            onGoLogin = { screen = "login" },
            onRegisterSuccess = { screen = "home" }
        )

        "home" -> HomeScreen(
            selectedTab = selectedTab,
            onTabSelected = { selectedTab = it },
            onLogout = { screen = "login" }
        )
    }
}

package com.example.elsewhere

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.elsewhere.ui.screens.AppRoot
import com.example.elsewhere.ui.theme.ElsewhereTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ElsewhereTheme {
                AppRoot()
            }
        }
    }
}
package com.example.elsewhere.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit
) {

    var startAnimation by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0.85f,
        animationSpec = tween(900, easing = EaseOutCubic),
        label = "scale"
    )

    LaunchedEffect(Unit) {
        startAnimation = true
        delay(1600)
        onSplashFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFFAFB),
                        Color(0xFFF6F1FF),
                        Color(0xFFFFFDFC)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {

        // ambient glow (same language as rest of app)
        Box(
            modifier = Modifier
                .size(300.dp)
                .offset(x = 120.dp, y = (-80).dp)
                .background(
                    Color(0xFFC7C0E8).copy(alpha = 0.25f),
                    CircleShape
                )
        )

        Box(
            modifier = Modifier
                .size(260.dp)
                .offset(x = (-100).dp, y = 500.dp)
                .background(
                    Color(0xFFD8A7B1).copy(alpha = 0.20f),
                    CircleShape
                )
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.scale(scale)
        ) {

            Text(
                text = "elsewhere",
                fontSize = 46.sp,
                fontWeight = FontWeight.ExtraLight,
                letterSpacing = 4.sp,
                color = Color(0xFF3F363A)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "find your calm, anywhere",
                color = Color(0xFF8A8588),
                fontSize = 14.sp
            )
        }
    }
}
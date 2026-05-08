
package com.example.elsewhere.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun OnboardingScreen(
    onContinue: () -> Unit
) {

    var show1 by remember { mutableStateOf(false) }
    var show2 by remember { mutableStateOf(false) }
    var show3 by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(200)
        show1 = true
        delay(200)
        show2 = true
        delay(200)
        show3 = true
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
            )
    ) {

        // ambient glow
        Box(
            modifier = Modifier
                .size(280.dp)
                .offset(x = 140.dp, y = (-60).dp)
                .background(Color(0xFFC7C0E8).copy(alpha = 0.22f), CircleShape)
        )

        Box(
            modifier = Modifier
                .size(240.dp)
                .offset(x = (-100).dp, y = 650.dp)
                .background(Color(0xFFD8A7B1).copy(alpha = 0.18f), CircleShape)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "elsewhere",
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraLight,
                letterSpacing = 3.sp,
                color = Color(0xFF3F363A)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "a place to reset, reconnect, and discover",
                fontSize = 15.sp,
                color = Color(0xFF8A8588)
            )

            Spacer(modifier = Modifier.height(36.dp))

            AnimatedVisibility(
                visible = show1,
                enter = fadeIn(animationSpec = tween(600))
            ) {
                FeatureRow("✨", "discover new spaces")
            }

            Spacer(modifier = Modifier.height(10.dp))

            AnimatedVisibility(
                visible = show2,
                enter = fadeIn(animationSpec = tween(600))
            ) {
                FeatureRow("🤝", "connect with people")
            }

            Spacer(modifier = Modifier.height(10.dp))

            AnimatedVisibility(
                visible = show3,
                enter = fadeIn(animationSpec = tween(600))
            ) {
                FeatureRow("🌿", "find your calm")
            }

            Spacer(modifier = Modifier.height(42.dp))

            Button(
                onClick = onContinue,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD8A7B1)
                )
            ) {
                Text("let's get started")
            }
        }
    }
}

@Composable
fun FeatureRow(icon: String, text: String) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.65f),
                        Color.White.copy(alpha = 0.35f)
                    )
                ),
                shape = RoundedCornerShape(22.dp)
            )
            .padding(vertical = 14.dp, horizontal = 18.dp)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            Text(
                text = icon,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = text,
                color = Color(0xFF4E4549),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
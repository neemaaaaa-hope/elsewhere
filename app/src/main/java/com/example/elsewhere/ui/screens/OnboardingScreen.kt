package com.example.elsewhere.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OnboardingScreen(
    onContinue: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFFFFFAFB),
                        Color(0xFFF6F1FF),
                        Color(0xFFFFFDFC)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {

        // soft aesthetic glow
        Box(
            modifier = Modifier
                .size(300.dp)
                .offset(x = 140.dp, y = (-120).dp)
                .background(Color(0xFFD8A7B1).copy(alpha = 0.15f), CircleShape)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "elsewhere",
                fontSize = 42.sp,
                fontWeight = FontWeight.ExtraLight,
                letterSpacing = 3.sp,
                color = Color(0xFF3F363A)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "discover hidden gems for calm, study & escape",
                fontSize = 14.sp,
                color = Color(0xFF8A8588),
                modifier = Modifier.padding(horizontal = 40.dp),
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = onContinue,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD8A7B1)
                ),
                shape = MaterialTheme.shapes.large
            ) {
                Text("start exploring")
            }
        }
    }
}
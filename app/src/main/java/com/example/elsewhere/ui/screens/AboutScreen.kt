package com.example.elsewhere.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AboutScreen() {
    val scrollState = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()) {
        // ambient glow
        Box(
            modifier = Modifier
                .size(300.dp)
                .offset(x = (-100).dp, y = 500.dp)
                .background(Color(0xFFD8A7B1).copy(alpha = 0.1f), CircleShape)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            Text(
                text = "about elsewhere",
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraLight,
                color = Color(0xFF3F363A),
                letterSpacing = 2.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White.copy(alpha = 0.5f), RoundedCornerShape(28.dp))
                    .padding(24.dp)
            ) {
                Column {
                    Text(
                        text = "our mission",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Light,
                        color = Color(0xFFD8A7B1)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "in an increasingly loud world, 'elsewhere' is designed to help you find silence. we curate and discover calm spaces—sanctuaries where you can think, breathe, and just be.",
                        fontSize = 15.sp,
                        color = Color(0xFF3F363A),
                        lineHeight = 24.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White.copy(alpha = 0.5f), RoundedCornerShape(28.dp))
                    .padding(24.dp)
            ) {
                Column {
                    Text(
                        text = "the philosophy",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Light,
                        color = Color(0xFFC7C0E8)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "we believe that environment shapes the mind. by connecting you with quiet cafes, hidden gardens, and peaceful libraries, we aim to promote focus and mental well-being.",
                        fontSize = 15.sp,
                        color = Color(0xFF3F363A),
                        lineHeight = 24.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "designed with ♡ for seekers of calm.",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 13.sp,
                color = Color(0xFF8A8588)
            )

            Text(
                text = "version 1.0.0",
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                textAlign = TextAlign.Center,
                fontSize = 11.sp,
                color = Color(0xFFCFC7D8)
            )
            
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

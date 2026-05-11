package com.example.elsewhere.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.elsewhere.AuthRepository

@Composable
fun ProfileScreen(
    onLogout: () -> Unit
) {
    val email = AuthRepository.getCurrentUserEmail() ?: "guest@elsewhere.com"

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
            )
    ) {
        // ambient glow
        Box(
            modifier = Modifier
                .size(300.dp)
                .offset(x = (-100).dp, y = (-50).dp)
                .background(Color(0xFFD8A7B1).copy(alpha = 0.12f), CircleShape)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            // Profile Avatar Placeholder
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(
                        Brush.linearGradient(listOf(Color(0xFFD8A7B1), Color(0xFFC7C0E8))),
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = email.take(1).uppercase(),
                    fontSize = 48.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Light
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "your sanctuary",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraLight,
                color = Color(0xFF3F363A),
                letterSpacing = 2.sp
            )

            Text(
                text = email,
                fontSize = 16.sp,
                color = Color(0xFF8A8588)
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Stats or Info Cards
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ProfileStatCard(label = "Found", value = "12", modifier = Modifier.weight(1f))
                ProfileStatCard(label = "Calm Hours", value = "24", modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(16.dp))

            ProfileStatCard(label = "Current Streak", value = "5 days", modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onLogout,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(18.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFFD8A7B1)
                ),
                elevation = ButtonDefaults.buttonElevation(2.dp)
            ) {
                Text("sign out", fontWeight = FontWeight.Medium)
            }
            
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun ProfileStatCard(label: String, value: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.6f)
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = value, fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color(0xFF3F363A))
            Text(text = label, fontSize = 12.sp, color = Color(0xFF8A8588))
        }
    }
}

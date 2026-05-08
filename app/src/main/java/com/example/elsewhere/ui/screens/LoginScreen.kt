package com.example.elsewhere.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(
    onGoRegister: () -> Unit,
    onLogin: () -> Unit
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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

        // ambient glow blobs (no flowers, more modern)
        Box(
            modifier = Modifier
                .size(260.dp)
                .offset(x = 160.dp, y = (-40).dp)
                .background(
                    Color(0xFFD8A7B1).copy(alpha = 0.20f),
                    CircleShape
                )
        )

        Box(
            modifier = Modifier
                .size(220.dp)
                .offset(x = (-80).dp, y = 600.dp)
                .background(
                    Color(0xFFC7C0E8).copy(alpha = 0.20f),
                    CircleShape
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 28.dp),
            verticalArrangement = Arrangement.Center
        ) {

            // ✨ BRAND TITLE (UPGRADED "ELSEWHERE")
            Text(
                text = "elsewhere",
                fontSize = 44.sp,
                fontWeight = FontWeight.ExtraLight,
                letterSpacing = 4.sp,
                color = Color(0xFF3F363A)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "find your calm, anywhere",
                color = Color(0xFF8A8588),
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(38.dp))

            // ✨ GLASS CARD (NO REAL BLUR, BUT DEPTH EFFECT)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 18.dp,
                        shape = RoundedCornerShape(32.dp),
                        ambientColor = Color(0xFFD8A7B1).copy(alpha = 0.15f)
                    )
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.White.copy(alpha = 0.55f),
                                Color.White.copy(alpha = 0.35f)
                            )
                        ),
                        shape = RoundedCornerShape(32.dp)
                    )
                    .padding(22.dp)
            ) {

                Column {

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("email") },
                        singleLine = true,
                        shape = RoundedCornerShape(18.dp),
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFD8A7B1),
                            unfocusedBorderColor = Color(0xFFCFC7D8),
                            focusedContainerColor = Color.White.copy(alpha = 0.6f),
                            unfocusedContainerColor = Color.White.copy(alpha = 0.4f)
                        )
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("password") },
                        singleLine = true,
                        shape = RoundedCornerShape(18.dp),
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFD8A7B1),
                            unfocusedBorderColor = Color(0xFFCFC7D8),
                            focusedContainerColor = Color.White.copy(alpha = 0.6f),
                            unfocusedContainerColor = Color.White.copy(alpha = 0.4f)
                        )
                    )

                    Spacer(modifier = Modifier.height(22.dp))

                    Button(
                        onClick = onLogin,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFD8A7B1)
                        )
                    ) {
                        Text("login")
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(
                onClick = onGoRegister,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "create account",
                    color = Color(0xFF7F7880)
                )
            }
        }
    }
}
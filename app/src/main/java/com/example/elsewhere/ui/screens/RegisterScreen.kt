package com.example.elsewhere.ui.screens

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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.elsewhere.AuthRepository
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    onGoLogin: () -> Unit,
    onRegisterSuccess: () -> Unit
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorText by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

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

        // soft glow backgrounds (consistent with LoginScreen)
        Box(
            modifier = Modifier
                .size(260.dp)
                .offset(x = (-80).dp, y = (-60).dp)
                .background(Color(0xFFC7C0E8).copy(alpha = 0.18f), CircleShape)
        )

        Box(
            modifier = Modifier
                .size(220.dp)
                .offset(x = 180.dp, y = 600.dp)
                .background(Color(0xFFD8A7B1).copy(alpha = 0.18f), CircleShape)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 28.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "elsewhere",
                fontSize = 44.sp,
                fontWeight = FontWeight.ExtraLight,
                letterSpacing = 4.sp,
                color = Color(0xFF3F363A)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "begin your journey to calm",
                color = Color(0xFF8A8588),
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(34.dp))

            // CARD
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color.White.copy(alpha = 0.55f),
                                Color.White.copy(alpha = 0.35f)
                            )
                        ),
                        shape = RoundedCornerShape(28.dp)
                    )
                    .padding(22.dp)
            ) {

                Column {
                    Text(
                        text = "create account",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Light,
                        color = Color(0xFF3F363A),
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                            errorText = ""
                        },
                        label = { Text("email") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFD8A7B1),
                            unfocusedBorderColor = Color(0xFFCFC7D8)
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                            errorText = ""
                        },
                        label = { Text("password") },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFD8A7B1),
                            unfocusedBorderColor = Color(0xFFCFC7D8)
                        )
                    )

                    if (errorText.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = errorText,
                            color = Color.Red,
                            fontSize = 12.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // REGISTER BUTTON
                    Button(
                        onClick = {
                            scope.launch {
                                loading = true
                                val result = AuthRepository.signUp(email, password)
                                loading = false

                                if (result.isSuccess) {
                                    onRegisterSuccess()
                                } else {
                                    errorText = "failed to create account"
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFD8A7B1)
                        )
                    ) {
                        if (loading) {
                            CircularProgressIndicator(
                                color = Color.White,
                                strokeWidth = 2.dp,
                                modifier = Modifier.size(18.dp)
                            )
                        } else {
                            Text("sign up")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            TextButton(
                onClick = onGoLogin,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "already have an account? login",
                    color = Color(0xFF7F7880)
                )
            }
        }
    }
}

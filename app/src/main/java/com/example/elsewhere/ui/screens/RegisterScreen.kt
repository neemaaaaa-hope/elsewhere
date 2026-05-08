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
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

import com.example.elsewhere.data.SupabaseClientInstance
//import io.github.jan.supabase.gotrue.auth
//import io.github.jan.supabase.gotrue.providers.builtin.Email

@Composable
fun RegisterScreen(
    onGoLogin: () -> Unit,
    onRegisterSuccess: () -> Unit
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }
    var errorText by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

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

        // ambient glows
        Box(
            modifier = Modifier
                .size(260.dp)
                .offset(x = 160.dp, y = (-50).dp)
                .background(
                    Color(0xFFC7C0E8).copy(alpha = 0.22f),
                    CircleShape
                )
        )

        Box(
            modifier = Modifier
                .size(240.dp)
                .offset(x = (-100).dp, y = 620.dp)
                .background(
                    Color(0xFFD8A7B1).copy(alpha = 0.18f),
                    CircleShape
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 28.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "create account",
                fontSize = 34.sp,
                fontWeight = FontWeight.ExtraLight,
                letterSpacing = 2.sp,
                color = Color(0xFF3F363A)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "join elsewhere and start exploring",
                fontSize = 14.sp,
                color = Color(0xFF8A8588)
            )

            Spacer(modifier = Modifier.height(38.dp))

            // glass card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
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
                        onValueChange = {
                            email = it
                            errorText = ""
                        },
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
                        onValueChange = {
                            password = it
                            errorText = ""
                        },
                        label = { Text("password") },
                        visualTransformation = PasswordVisualTransformation(),
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

                    if (errorText.isNotEmpty()) {

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = errorText,
                            color = Color.Red,
                            fontSize = 12.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(22.dp))

                    Button(
                        onClick = {

                            if (email.isBlank() || password.isBlank()) {
                                errorText = "please fill in all fields"
                                return@Button
                            }

                            loading = true

                            scope.launch {

                                try {

                                    val client = SupabaseClientInstance.client
                                    loading = false
                                    onRegisterSuccess()

                                } catch (e: Exception) {

                                    loading = false
                                    errorText = e.message ?: "something went wrong"
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFD8A7B1)
                        )
                    ) {

                        if (loading) {

                            CircularProgressIndicator(
                                color = Color.White,
                                strokeWidth = 2.dp,
                                modifier = Modifier.size(20.dp)
                            )

                        } else {

                            Text("sign up")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

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
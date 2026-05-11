package com.example.elsewhere.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    selectedTab: String,
    onTabSelected: (String) -> Unit,
    onLogout: () -> Unit
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
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(modifier = Modifier.weight(1f)) {
                when (selectedTab) {
                    "home" -> HomeContent()
                    "explore" -> ExploreScreen()
                    "about" -> AboutScreen()
                    "profile" -> ProfileScreen(onLogout = onLogout)
                    else -> HomeContent()
                }
            }

            // minimal nav
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 22.dp, vertical = 16.dp)
                    .background(Color.White.copy(alpha = 0.8f), RoundedCornerShape(24.dp))
                    .padding(14.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "🏠 home",
                    color = if (selectedTab == "home") Color(0xFFD8A7B1) else Color.Gray,
                    modifier = Modifier.clickable { onTabSelected("home") }
                )
                Text(
                    "🗺 explore",
                    color = if (selectedTab == "explore") Color(0xFFD8A7B1) else Color.Gray,
                    modifier = Modifier.clickable { onTabSelected("explore") }
                )
                Text(
                    "✨ about",
                    color = if (selectedTab == "about") Color(0xFFD8A7B1) else Color.Gray,
                    modifier = Modifier.clickable { onTabSelected("about") }
                )
                Text(
                    "👤 profile",
                    color = if (selectedTab == "profile") Color(0xFFD8A7B1) else Color.Gray,
                    modifier = Modifier.clickable { onTabSelected("profile") }
                )
            }
        }
    }
}

@Composable
fun HomeContent() {
    val places = listOf(
        "Sankara Lounge — quiet luxury workspace",
        "Novotel Café — calm study coffee spot",
        "Ibis Styles Rooftop — sunset reset zone",
        "Trademark Hotel — aesthetic lobby work area"
    )

    var selectedPlace by remember { mutableStateOf<String?>(null) }

    val scale by animateFloatAsState(
        targetValue = if (selectedPlace != null) 1.02f else 1f,
        animationSpec = tween(500),
        label = ""
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // ambient glow
        Box(
            modifier = Modifier
                .size(360.dp)
                .offset(x = 120.dp, y = (-140).dp)
                .background(Color(0xFFC7C0E8).copy(alpha = 0.15f), CircleShape)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(22.dp)
        ) {

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "elsewhere",
                fontSize = 34.sp,
                fontWeight = FontWeight.ExtraLight,
                color = Color(0xFF3F363A),
                letterSpacing = 2.sp
            )

            Text(
                text = "discover calm places around you",
                fontSize = 14.sp,
                color = Color(0xFF8A8588)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 🌍 MAIN HERO
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .scale(scale)
                    .shadow(24.dp, RoundedCornerShape(28.dp))
                    .background(
                        Brush.linearGradient(
                            listOf(Color(0xFFD8A7B1), Color(0xFFC7C0E8))
                        ),
                        RoundedCornerShape(28.dp)
                    )
                    .padding(18.dp)
            ) {

                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxSize()
                ) {

                    Text(
                        "WESTLANDS DISCOVERY MAP",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Text(
                        "tap a place below to mark it as found 🌸",
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 13.sp
                    )

                    Text(
                        "📍 live area: Nairobi — Westlands",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "curated hidden gems",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF3F363A)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // 🌿 INTERACTIVE LIST
            places.forEach { place ->

                val isSelected = selectedPlace == place

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable {
                            selectedPlace = place
                        },
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(
                        containerColor =
                        if (isSelected)
                            Color(0xFFD8A7B1).copy(alpha = 0.2f)
                        else
                            Color.White.copy(alpha = 0.75f)
                    )
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            if (isSelected) "🌸" else "🌿",
                            fontSize = 18.sp
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Column(modifier = Modifier.weight(1f)) {

                            Text(
                                text = place,
                                fontSize = 14.sp,
                                color = Color(0xFF3F363A)
                            )

                            Text(
                                text = if (isSelected) "marked as found" else "tap to discover",
                                fontSize = 12.sp,
                                color = Color(0xFF8A8588)
                            )
                        }
                    }
                }
            }
        }
    }
}
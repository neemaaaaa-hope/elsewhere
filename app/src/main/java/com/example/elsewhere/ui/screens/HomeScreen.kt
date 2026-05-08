package com.example.elsewhere.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
                    colors = listOf(
                        Color(0xFFFFFAFB),
                        Color(0xFFF6F1FF),
                        Color(0xFFFFFDFC)
                    )
                )
            )
    ) {

        // 🌿 background glow
        Box(
            modifier = Modifier
                .size(340.dp)
                .offset(x = 160.dp, y = (-100).dp)
                .background(Color(0xFFC7C0E8).copy(alpha = 0.20f), CircleShape)
        )

        Box(
            modifier = Modifier
                .size(300.dp)
                .offset(x = (-120).dp, y = 720.dp)
                .background(Color(0xFFD8A7B1).copy(alpha = 0.16f), CircleShape)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "good evening",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraLight,
                color = Color(0xFF3F363A)
            )

            Text(
                text = "welcome to elsewhere",
                fontSize = 14.sp,
                color = Color(0xFF8A8588)
            )

            Spacer(modifier = Modifier.height(28.dp))

            // 🌸 HERO CARD (STAR OF SCREEN)
            HeroCard()

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                MiniCard("explore")
                MiniCard("saved")
            }

            Spacer(modifier = Modifier.height(12.dp))

            MiniCard("daily reset")

            Spacer(modifier = Modifier.weight(1f))

            BottomNav(
                selectedTab = selectedTab,
                onTabSelected = onTabSelected,
                onLogout = onLogout
            )
        }
    }
}

@Composable
fun HeroCard() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFD8A7B1).copy(alpha = 0.55f),
                        Color(0xFFC7C0E8).copy(alpha = 0.45f)
                    )
                ),
                shape = RoundedCornerShape(30.dp)
            )
            .padding(18.dp)
    ) {

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {

            Text(
                text = "your calm space today",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )

            Text(
                text = "tap into places, people & moments that match your mood",
                fontSize = 13.sp,
                color = Color.White.copy(alpha = 0.9f)
            )
        }
    }
}

@Composable
fun MiniCard(text: String) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(
                Color.White.copy(alpha = 0.65f),
                RoundedCornerShape(24.dp)
            )
            .padding(16.dp),
        contentAlignment = Alignment.CenterStart
    ) {

        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF4E4549)
        )
    }
}

@Composable
fun BottomNav(
    selectedTab: String,
    onTabSelected: (String) -> Unit,
    onLogout: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White.copy(alpha = 0.65f), RoundedCornerShape(26.dp))
            .padding(vertical = 12.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        EmojiNavItem(
            emoji = "🏠",
            label = "home",
            selected = selectedTab == "home",
            onClick = { onTabSelected("home") }
        )

        EmojiNavItem(
            emoji = "🔍",
            label = "explore",
            selected = selectedTab == "explore",
            onClick = { onTabSelected("explore") }
        )

        EmojiNavItem(
            emoji = "👤",
            label = "profile",
            selected = selectedTab == "profile",
            onClick = { onTabSelected("profile") }
        )

        TextButton(onClick = onLogout) {
            Text(
                text = "exit",
                color = Color(0xFFD8A7B1),
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun EmojiNavItem(
    emoji: String,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(4.dp)
    ) {

        TextButton(onClick = onClick) {
            Text(text = emoji, fontSize = 18.sp)
        }

        Text(
            text = label,
            fontSize = 11.sp,
            color = if (selected) Color(0xFF4E4549) else Color(0xFF8A8588),
            fontWeight = if (selected) FontWeight.Medium else FontWeight.Normal
        )
    }
}
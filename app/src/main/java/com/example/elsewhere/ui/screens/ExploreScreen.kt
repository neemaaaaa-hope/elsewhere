package com.example.elsewhere.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.platform.LocalContext
import com.example.elsewhere.utils.MapUtils

data class Sanctuary(
    val name: String,
    val distance: String,
    val tag: String,
    val category: String
)

@Composable
fun ExploreScreen() {
    val categories = listOf("All", "Coffee", "Libraries", "Parks", "Workspaces", "Zen Zones")
    var selectedCategory by remember { mutableStateOf("All") }
    var searchQuery by remember { mutableStateOf("") }

    val allSanctuaries = listOf(
        Sanctuary("The Secret Garden", "1.2 km", "✨ Peaceful", "Parks"),
        Sanctuary("Silent Library Annex", "0.5 km", "✨ Peaceful", "Libraries"),
        Sanctuary("Cloud 9 Rooftop", "2.1 km", "☁️ Breezy", "Workspaces"),
        Sanctuary("Bean & Quiet", "0.8 km", "☕ Zen", "Coffee"),
        Sanctuary("The Reading Nook", "1.5 km", "📚 Cozy", "Libraries"),
        Sanctuary("Urban Forest", "3.0 km", "🌿 Fresh", "Parks"),
        Sanctuary("Focus Hub", "0.2 km", "💻 Intense", "Workspaces"),
        Sanctuary("Zen Garden Cafe", "1.1 km", "🍵 Calm", "Zen Zones")
    )

    val filteredSanctuaries = allSanctuaries.filter {
        (selectedCategory == "All" || it.category == selectedCategory) &&
                (searchQuery.isEmpty() || it.name.contains(searchQuery, ignoreCase = true))
    }
    
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        // ambient glow (consistent with other screens)
        Box(
            modifier = Modifier
                .size(320.dp)
                .offset(x = 160.dp, y = 400.dp)
                .background(Color(0xFFC7C0E8).copy(alpha = 0.12f), CircleShape)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 22.dp)
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "explore",
                fontSize = 34.sp,
                fontWeight = FontWeight.ExtraLight,
                color = Color(0xFF3F363A),
                letterSpacing = 2.sp
            )

            Text(
                text = "find your perfect sanctuary",
                fontSize = 14.sp,
                color = Color(0xFF8A8588)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Interactive Search Bar
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("search for calm places...", color = Color(0xFF8A8588), fontSize = 14.sp) },
                leadingIcon = { 
                    Box(modifier = Modifier.padding(start = 12.dp)) {
                        Text("🔍", fontSize = 16.sp)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White.copy(alpha = 0.6f),
                    unfocusedContainerColor = Color.White.copy(alpha = 0.6f),
                    disabledContainerColor = Color.White.copy(alpha = 0.6f),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color(0xFFD8A7B1)
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Categories
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 8.dp)
            ) {
                items(categories) { category ->
                    val isSelected = selectedCategory == category
                    Box(
                        modifier = Modifier
                            .background(
                                if (isSelected) Color(0xFFD8A7B1) else Color.White.copy(alpha = 0.5f),
                                RoundedCornerShape(16.dp)
                            )
                            .clickable { selectedCategory = category }
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = category,
                            color = if (isSelected) Color.White else Color(0xFF3F363A),
                            fontSize = 13.sp,
                            fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = if (searchQuery.isNotEmpty()) "results for '$searchQuery'" else "trending nearby",
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                color = Color(0xFF3F363A)
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            if (filteredSanctuaries.isEmpty()) {
                Box(modifier = Modifier.fillMaxWidth().padding(20.dp), contentAlignment = Alignment.Center) {
                    Text("no sanctuaries found here 🌸", color = Color.Gray, fontSize = 14.sp)
                }
            } else {
                filteredSanctuaries.forEach { sanctuary ->
                    ExploreListItem(sanctuary.name, sanctuary.distance, sanctuary.tag) {
                        MapUtils.openInGoogleMaps(context, sanctuary.name)
                    }
                }
            }
        }
    }
}

@Composable
fun ExploreListItem(name: String, distance: String, tag: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.5f))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = name, fontSize = 15.sp, color = Color(0xFF3F363A), fontWeight = FontWeight.Medium)
                Text(text = "$distance — $tag", fontSize = 12.sp, color = Color(0xFF8A8588))
            }
            Text("📍", fontSize = 18.sp)
        }
    }
}

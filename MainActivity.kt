package com.mactive.premium

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Orange = Color(0xFFFF7A1A)
private val Ink = Color(0xFF101014)
private val categories = listOf("Todos", "Sport", "Ultra", "Luxury", "AMOLED", "Minimal", "Fitness", "Photo", "Digital", "Classic", "Adventure")
data class WatchFace(val id: Int, val name: String, val category: String, val accent: Color)
private val accents = listOf(Color(0xFFFF7A1A), Color(0xFF9CFF3B), Color(0xFF36B9FF), Color(0xFFFF4D80), Color(0xFFFFD36A), Color(0xFFB89BFF), Color(0xFF63F3D1))
private val faces = (1..100).map { i ->
    val category = categories[1 + (i - 1) % (categories.size - 1)]
    WatchFace(i, "$category ${i.toString().padStart(3, '0')}", category, accents[(i - 1) % accents.size])
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState); setContent { PremiumApp() } }
}

@Composable
private fun PremiumApp() {
    var category by remember { mutableStateOf("Todos") }
    var selected by remember { mutableStateOf<WatchFace?>(null) }
    var favoriteIds by remember { mutableStateOf(setOf<Int>()) }
    MaterialTheme(colorScheme = darkColorScheme(primary = Orange, background = Ink, surface = Color(0xFF1B1B22))) {
        Column(Modifier.fillMaxSize().background(Ink).padding(horizontal = 18.dp)) {
            Spacer(Modifier.height(28.dp))
            Text(if (selected == null) "MActivePro" else "Detalhes do mostrador", color = Color.White, fontSize = 27.sp, fontWeight = FontWeight.Bold)
            Text(if (selected == null) "Watch Face Studio • 100 designs originais" else "Pré-visualização • não instalado", color = Color.LightGray, fontSize = 13.sp)
            Spacer(Modifier.height(20.dp))
            if (selected != null) {
                val face = selected!!
                WatchFacePreview(face, Modifier.fillMaxWidth().height(330.dp))
                Spacer(Modifier.height(16.dp))
                Text(face.name, color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text("${face.category} • Modelo demonstrativo", color = Color.LightGray)
                Spacer(Modifier.height(18.dp))
                Button(onClick = { favoriteIds = if (face.id in favoriteIds) favoriteIds - face.id else favoriteIds + face.id }, modifier = Modifier.fillMaxWidth()) { Text(if (face.id in favoriteIds) "Remover dos favoritos" else "Adicionar aos favoritos") }
                Spacer(Modifier.height(8.dp))
                Button(onClick = {}, enabled = false, modifier = Modifier.fillMaxWidth()) { Text("Instalar no relógio — aguardando compatibilidade") }
                TextButton(onClick = { selected = null }) { Text("Voltar à galeria") }
            } else {
                Text("Explore os mostradores", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(12.dp))
                androidx.compose.foundation.lazy.LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(categories.size) { index ->
                        val name = categories[index]
                        FilterChip(selected = category == name, onClick = { category = name }, label = { Text(name) })
                    }
                }
                Spacer(Modifier.height(12.dp))
                LazyVerticalGrid(columns = GridCells.Fixed(2), horizontalArrangement = Arrangement.spacedBy(12.dp), verticalArrangement = Arrangement.spacedBy(12.dp), contentPadding = PaddingValues(bottom = 30.dp)) {
                    items(faces.filter { category == "Todos" || it.category == category }) { face ->
                        Column(Modifier.background(Color(0xFF1D1D24), RoundedCornerShape(20.dp)).clickable { selected = face }.padding(10.dp)) {
                            WatchFacePreview(face, Modifier.fillMaxWidth().height(150.dp))
                            Spacer(Modifier.height(8.dp))
                            Text(face.name, color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                            Text(face.category, color = Color.Gray, fontSize = 12.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun WatchFacePreview(face: WatchFace, modifier: Modifier = Modifier) {
    Box(modifier, contentAlignment = Alignment.Center) {
        Box(Modifier.fillMaxHeight(0.91f).aspectRatio(0.91f).background(Color.Black, RoundedCornerShape(42.dp)), contentAlignment = Alignment.Center) {
            Canvas(Modifier.fillMaxSize().padding(13.dp)) {
                val r = size.minDimension * 0.43f
                drawCircle(face.accent.copy(alpha = 0.28f), radius = r, style = Stroke(width = 8.dp.toPx()))
                drawArc(face.accent, startAngle = -90f, sweepAngle = 235f + face.id % 90, useCenter = false, topLeft = Offset(center.x-r, center.y-r), size = androidx.compose.ui.geometry.Size(r*2,r*2), style = Stroke(width = 8.dp.toPx()))
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("10:08", color = face.accent, fontSize = if (face.id % 3 == 0) 40.sp else 34.sp, fontWeight = FontWeight.Black)
                Text("QUI 29 OUT", color = Color.White, fontSize = 10.sp, letterSpacing = 1.sp)
                Spacer(Modifier.height(12.dp))
                Text("●  8.420 passos", color = face.accent, fontSize = 10.sp)
            }
        }
    }
}

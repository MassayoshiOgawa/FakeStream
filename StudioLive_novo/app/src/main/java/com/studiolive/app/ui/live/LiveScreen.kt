package com.studiolive.app.ui.live

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.FlipCameraAndroid
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MicOff
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.studiolive.app.ui.theme.BottomBarBlack
import com.studiolive.app.ui.theme.BuyButtonBg
import com.studiolive.app.ui.theme.BuyButtonText
import com.studiolive.app.ui.theme.CommentInputOutline
import com.studiolive.app.ui.theme.CounterBg
import com.studiolive.app.ui.theme.LiveRed
import com.studiolive.app.ui.theme.OverlayScrim
import com.studiolive.app.viewmodel.MainViewModel

@Composable
fun LiveScreen(vm: MainViewModel, onExit: () -> Unit) {
    val state by vm.uiState.collectAsState()
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB7B6AF))
    ) {
        // Camera preview area (placeholder)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 6.dp, start = 6.dp, end = 6.dp, bottom = 81.dp)
                .background(Color(0xFFC3C2BB))
        )
        
        // Top bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 14.dp, top = 14.dp, end = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF8B7355))
            )
            Spacer(modifier = Modifier.width(9.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = state.displayName,
                        color = Color.White,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(" ▾", color = Color.White, fontSize = 10.sp)
                }
            }
            // Live badge
            Box(
                modifier = Modifier
                    .height(28.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(LiveRed)
                    .padding(horizontal = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("AO VIVO", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.width(8.dp))
            // Viewer counter
            Box(
                modifier = Modifier
                    .height(24.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(CounterBg)
                    .padding(horizontal = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.People, contentDescription = null, tint = Color.White, modifier = Modifier.size(12.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(formatCount(state.viewerCount), color = Color.White, fontSize = 9.sp)
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text("✕", color = Color.White, fontSize = 18.sp, modifier = Modifier.clickable { onExit() })
        }
        
        // Right side action rail (4 icons)
        Column(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 14.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(19.dp)
        ) {
            Icon(
                imageVector = if (state.isMicMuted) Icons.Filled.MicOff else Icons.Filled.Mic,
                contentDescription = "Microfone",
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { vm.toggleMic() }
            )
            Icon(
                imageVector = Icons.Filled.Videocam,
                contentDescription = "Câmera",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            Icon(
                imageVector = Icons.Filled.SwapHoriz,
                contentDescription = "Alternar câmera",
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { vm.toggleCamera() }
            )
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Efeitos",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
        
        // Bottom bar
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            // Comments overlay area (like image 2 style)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(OverlayScrim)
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 10.dp, end = 80.dp, bottom = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    CommentRow(name = "dark_emeralds", message = "Good Morning!!")
                    CommentRow(name = "hey_sarah2002", message = "bought a badge")
                    CommentRow(name = "photosbyem", message = "Getting ready to start my day!")
                    CommentRow(name = "travis_shreds18", message = "So happy to catch this LIVE!")
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Buy a badge to support youngzee", color = Color.White, fontSize = 9.sp)
                        Spacer(modifier = Modifier.weight(1f))
                        Box(
                            modifier = Modifier
                                .height(20.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(BuyButtonBg)
                                .padding(horizontal = 12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Comprar", color = BuyButtonText, fontSize = 9.sp, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }
            
            // Input bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BottomBarBlack)
                    .padding(horizontal = 10.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(36.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .border(1.dp, CommentInputOutline, RoundedCornerShape(18.dp))
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text("Adicione um comentário.", color = Color(0xFFBDBDBD), fontSize = 13.sp)
                }
                Spacer(modifier = Modifier.width(12.dp))
                Icon(Icons.Filled.Videocam, contentDescription = null, tint = Color.White, modifier = Modifier.size(22.dp))
                Spacer(modifier = Modifier.width(14.dp))
                Icon(Icons.Filled.People, contentDescription = null, tint = Color.White, modifier = Modifier.size(22.dp))
                Spacer(modifier = Modifier.width(14.dp))
                Icon(Icons.Filled.HelpOutline, contentDescription = null, tint = Color.White, modifier = Modifier.size(22.dp))
                Spacer(modifier = Modifier.width(14.dp))
                Icon(Icons.Filled.Send, contentDescription = null, tint = Color.White, modifier = Modifier.size(22.dp))
            }
        }
    }
}

@Composable
fun CommentRow(name: String, message: String) {
    Row(verticalAlignment = Alignment.Top) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .background(Color(0xFF6B6B6B))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(name, color = Color(0xFFD0D0D0), fontSize = 9.sp)
            Text(message, color = Color.White, fontSize = 9.sp)
        }
    }
}

fun formatCount(count: Int): String {
    return if (count >= 1000) {
        val k = count / 1000.0
        String.format("%.1fk", k)
    } else {
        count.toString()
    }
}

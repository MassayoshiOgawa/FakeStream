package com.studiolive.app.ui.live.overlay

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.RemoveRedEye
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.studiolive.app.ui.theme.BadgePurple
import com.studiolive.app.ui.theme.BuyBg
import com.studiolive.app.ui.theme.HeartOrange
import com.studiolive.app.ui.theme.HeartRed
import com.studiolive.app.ui.theme.LiveMagenta
import com.studiolive.app.ui.theme.PlaceholderGray
import com.studiolive.app.ui.theme.ViewerBg
import com.studiolive.app.ui.theme.White

@Composable
fun InstagramLiveOverlay(
    viewerCount: Int,
    elapsedSeconds: Long,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val screenWidthDp = with(LocalDensity.current) { maxWidth.toPx() / density }
        val scale = LiveOverlayScale.scale(screenWidthDp)
        
        val avatarSize = (60f * scale).dp
        val headerTopPadding = (50f * scale).dp
        val sidePadding = (12f * scale).dp
        val commentBottomPadding = (160f * scale).dp
        val heartBottomPadding = (180f * scale).dp
        val buyBarBottomPadding = (110f * scale).dp
        val bottomBarBottomPadding = (20f * scale).dp
        val bottomBarHeight = (70f * scale).dp
        
        Box(modifier = Modifier.fillMaxSize()) {
            // Header - 100% fiel ao spec IMAGEM 2, responsivo para Motorola G84 5G
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = headerTopPadding, start = sidePadding, end = sidePadding),
                verticalAlignment = Alignment.CenterVertically
            ) {
            Box(
                modifier = Modifier
                    .size(avatarSize)
                    .clip(CircleShape)
                    .background(Color(0xFF3C3C3C))
            )
            Spacer(modifier = Modifier.width((15f * scale).dp))
            Text(
                text = "youngezee",
                color = White,
                fontSize = (14f * scale).sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = " ▾",
                color = White,
                fontSize = (12f * scale).sp
            )
            Spacer(modifier = Modifier.weight(1f))
            // LIVE badge
            Box(
                modifier = Modifier
                    .background(LiveMagenta, RoundedCornerShape((7f * scale).dp))
                    .padding(horizontal = (14f * scale).dp, vertical = (6f * scale).dp)
            ) {
                Text(
                    text = "LIVE",
                    color = White,
                    fontSize = (12f * scale).sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.width((16f * scale).dp))
            // Viewer count
            Row(
                modifier = Modifier
                    .background(ViewerBg, RoundedCornerShape((8f * scale).dp))
                    .padding(horizontal = (16f * scale).dp, vertical = (6f * scale).dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.RemoveRedEye,
                    contentDescription = null,
                    tint = White,
                    modifier = Modifier.size((14f * scale).dp)
                )
                Spacer(modifier = Modifier.width((6f * scale).dp))
                Text(
                    text = if (viewerCount >= 1000) "${String.format("%.1f", viewerCount / 1000.0)}k" else viewerCount.toString(),
                    color = White,
                    fontSize = (13f * scale).sp,
                    fontWeight = FontWeight.Normal
                )
            }
            Spacer(modifier = Modifier.width((25f * scale).dp))
            IconButton(onClick = onClose, modifier = Modifier.size((35f * scale).dp)) {
                Icon(Icons.Default.Close, contentDescription = "Fechar", tint = White, modifier = Modifier.size((20f * scale).dp))
            }
        }

        // Comments area
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = sidePadding, bottom = commentBottomPadding)
                .width((300f * scale).dp)
        ) {
            CommentItem("lil_lapislazuli", "joined", isEvent = true, scale = scale)
            CommentItem("dark_emeralds", "Good Morning!!!", scale = scale)
            CommentItem("hey_sarah2002", "bought a badge", isBadgeEvent = true, badgeCount = 2, scale = scale)
            CommentItem("photosbyean", "Getting ready to start my day!", hasHeartBadge = true, scale = scale)
            CommentItem("travis_shreds18", "So happy to catch this LIVE!", isBadgeEvent = true, badgeCount = 3, scale = scale)
        }

        // Hearts column
        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = sidePadding, bottom = heartBottomPadding)
                .fillMaxHeight(0.3f),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            HeartItem(color = HeartRed, scale = scale)
            Spacer(modifier = Modifier.height((8f * scale).dp))
            HeartItem(color = White.copy(alpha = 0.7f), scale = scale)
            Spacer(modifier = Modifier.height((8f * scale).dp))
            HeartItem(color = White, scale = scale)
        }

        // Buy badge bar
        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .padding(start = sidePadding, end = sidePadding, bottom = buyBarBottomPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Buy a badge to support youngezee",
                color = White,
                fontSize = (13f * scale).sp,
                modifier = Modifier.weight(1f)
            )
            Box(
                modifier = Modifier
                    .background(BuyBg, RoundedCornerShape((50f * scale).dp))
                    .padding(horizontal = (24f * scale).dp, vertical = (10f * scale).dp)
            ) {
                Text(
                    text = "Buy",
                    color = White,
                    fontSize = (13f * scale).sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        // Bottom bar
        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .background(Color.Black)
                .padding(start = sidePadding, end = sidePadding, bottom = bottomBarBottomPadding)
                .height(bottomBarHeight),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .height((50f * scale).dp)
                    .border(1.dp, White.copy(alpha = 0.6f), RoundedCornerShape((50f * scale).dp))
                    .padding(horizontal = (16f * scale).dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Comment",
                    color = PlaceholderGray,
                    fontSize = (14f * scale).sp
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "•••", color = PlaceholderGray, fontSize = (14f * scale).sp)
            }
            Spacer(modifier = Modifier.width((12f * scale).dp))
            Icon(Icons.Default.QuestionMark, contentDescription = null, tint = White, modifier = Modifier.size((28f * scale).dp))
            Spacer(modifier = Modifier.width((16f * scale).dp))
            Icon(Icons.Default.Send, contentDescription = null, tint = White, modifier = Modifier.size((28f * scale).dp))
            Spacer(modifier = Modifier.width((16f * scale).dp))
            Icon(Icons.Outlined.FavoriteBorder, contentDescription = null, tint = White, modifier = Modifier.size((28f * scale).dp))
        }
        }
    }
    }

@Composable
fun CommentItem(
    username: String,
    message: String,
    isEvent: Boolean = false,
    isBadgeEvent: Boolean = false,
    badgeCount: Int = 0,
    hasHeartBadge: Boolean = false,
    scale: Float = 1f
) {
    Row(
        modifier = Modifier.padding(vertical = (6f * scale).dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size((40f * scale).dp)
                .clip(CircleShape)
                .background(Color(0xFF4C4C4C))
        )
        Spacer(modifier = Modifier.width((10f * scale).dp))
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = username,
                    color = White,
                    fontSize = (14f * scale).sp,
                    fontWeight = FontWeight.SemiBold
                )
                if (hasHeartBadge) {
                    Spacer(modifier = Modifier.width((6f * scale).dp))
                    Box(
                        modifier = Modifier
                            .background(HeartOrange, RoundedCornerShape((4f * scale).dp))
                            .padding(horizontal = (4f * scale).dp, vertical = (2f * scale).dp)
                    ) {
                        Text(text = "♥", color = White, fontSize = (10f * scale).sp)
                    }
                }
                if (isBadgeEvent) {
                    Spacer(modifier = Modifier.width((6f * scale).dp))
                    Box(
                        modifier = Modifier
                            .background(if (badgeCount >= 3) BadgePurple else LiveMagenta, RoundedCornerShape((12f * scale).dp))
                            .padding(horizontal = (8f * scale).dp, vertical = (2f * scale).dp)
                    ) {
                        Text(
                            text = "♥".repeat(badgeCount),
                            color = White,
                            fontSize = (10f * scale).sp
                        )
                    }
                }
            }
            if (!isEvent) {
                Text(
                    text = message,
                    color = White,
                    fontSize = (14f * scale).sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            } else {
                Text(
                    text = message,
                    color = White.copy(alpha = 0.7f),
                    fontSize = (14f * scale).sp
                )
            }
        }
    }
}

@Composable
fun HeartItem(color: Color, scale: Float = 1f) {
    Icon(
        imageVector = Icons.Filled.Favorite,
        contentDescription = null,
        tint = color,
        modifier = Modifier.size((32f * scale).dp)
    )
}

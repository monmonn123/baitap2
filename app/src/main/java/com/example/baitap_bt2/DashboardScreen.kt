package com.example.baitap_bt2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- Dữ liệu mẫu cho các mục menu ---
data class MenuItem(val title: String, val iconRes: Int)

val topMenuItems = listOf(
    MenuItem("Video Call", R.drawable.ic_videocall), // Thay thế bằng icon của bạn
    MenuItem("Notification", R.drawable.ic_notification),
    MenuItem("Voice Call", R.drawable.ic_voicecall)
)

val bottomMenuItems = listOf(
    MenuItem("Inbox", R.drawable.ic_inbox),
    MenuItem("Map", R.drawable.ic_map),
    MenuItem("Chat", R.drawable.ic_chat),
    MenuItem("Report", R.drawable.ic_report),
    MenuItem("Calendar", R.drawable.ic_calendar),
    MenuItem("Tips", R.drawable.ic_tips),
    MenuItem("Settings", R.drawable.ic_settings),
    MenuItem("Other", R.drawable.ic_other)
)

// --- Composable chính cho màn hình ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F0F0)) // Màu nền xám nhạt
    ) {
        // Phần Header
        HeaderSection()

        // Phần Body
        BodySection()
    }
}

// --- Các thành phần con ---

@Composable
private fun HeaderSection() {
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFF5E69B7), Color(0xFFF09874))
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)
            .background(
                brush = gradientBrush,
                shape = RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp)
            )
            .padding(top = 40.dp, start = 24.dp, end = 24.dp)
    ) {
        Column {
            // Hàng "Xin chào" và Avatar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = "Xin chào", color = Color.White.copy(alpha = 0.8f), fontSize = 16.sp)
                    Text(text = "Hồng Phước", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }
                Image(
                    painter = painterResource(id = R.drawable.ic_avatar), // <-- Thay avatar của bạn
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(Color.White),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Các nút chức năng nhanh
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    topMenuItems.forEach { item ->
                        FeatureButton(menuItem = item)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BodySection() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        // Thanh tìm kiếm
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            shape = RoundedCornerShape(25.dp),
            trailingIcon = {
                Icon(
                    tint = Color.Unspecified,
                    painter = painterResource(id = R.drawable.ic_search), // <-- Thay icon tìm kiếm
                    contentDescription = "Search",
                    modifier = Modifier
                        .background(Color(0xFFE0E0E0), CircleShape)
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor  = colorResource(R.color.white),
                unfocusedContainerColor  = colorResource(R.color.white),
                focusedIndicatorColor = colorResource(id = R.color.blue),
                unfocusedIndicatorColor = Color.Gray,
                cursorColor = colorResource(id = R.color.blue)
            )
        )

        // Banner nâng cấp tài khoản
        UpgradeAccountBanner()

        Spacer(modifier = Modifier.height(20.dp))

        // Lưới các chức năng chính
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            contentPadding = PaddingValues(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(bottomMenuItems) { item ->
                FeatureButton(menuItem = item, useCard = true)
            }
        }
    }
}

@Composable
private fun FeatureButton(menuItem: MenuItem, useCard: Boolean = false) {
    val content = @Composable {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = menuItem.iconRes),
                contentDescription = menuItem.title,
                modifier = Modifier.size(if (useCard) 28.dp else 40.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = menuItem.title, fontSize = 12.sp, textAlign = TextAlign.Center)
        }
    }

    if (useCard) {
        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.aspectRatio(1f) // Đảm bảo các ô vuông
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                content()
            }
        }
    } else {
        content()
    }
}

@Composable
private fun UpgradeAccountBanner() {
    val gradientBrush = Brush.horizontalGradient(
        colors = listOf(Color(0xFF5E69B7).copy(alpha = 0.8f), Color(0xFFF09874).copy(alpha = 0.8f))
    )
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(gradientBrush)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "To Get Unlimited",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "Upgrade Your Account",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
            Image(
                painter = painterResource(id = R.drawable.ic_upgrade_banner), // <-- Thay ảnh banner
                contentDescription = "Upgrade Banner",
                modifier = Modifier.size(80.dp)
            )
        }
    }
}

// --- Preview để xem trước ---
@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    DashboardScreen()
}

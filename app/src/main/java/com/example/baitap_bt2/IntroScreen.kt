package com.example.baitap_bt2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// BƯỚC 1: Sửa đổi chữ ký hàm để nhận NavController
@Composable
fun IntroScreen(navController: NavController) {

    // Box làm layout gốc để xếp chồng các phần tử lên nhau
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // 1. Hình nền
        Image(
            painter = painterResource(id = R.drawable.background_intro),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // Đảm bảo ảnh nền lấp đầy màn hình
        )

        // Column để sắp xếp nội dung theo chiều dọc
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp), // Thêm padding ngang
            horizontalAlignment = Alignment.CenterHorizontally, // Căn giữa theo chiều ngang
        ) {
            // 2. Tiêu đề "Food.Dr"
            Text(
                text = "Food.Dr",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.blue),
                modifier = Modifier
                    .fillMaxWidth() // Chiếm hết chiều rộng
                    .padding(top = 100.dp), // Khoảng cách từ đỉnh
                textAlign = TextAlign.Center // Căn giữa chữ
            )

            // 3. Logo
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(250.dp) // Kích thước logo
                    .padding(top = 30.dp) // Khoảng cách từ tiêu đề
            )

            // 4. Phụ đề "Thức ăn nhanh"
            Text(
                text = "Thức ăn nhanh",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.blue),
                modifier = Modifier.padding(top = 20.dp)
            )

            // Spacer để đẩy nút "Bắt đầu" xuống dưới
            Spacer(modifier = Modifier.weight(1f))

            // 5. Nút "Bắt đầu"
            Button(
                onClick = {
                    // BƯỚC 2: Sử dụng NavController để điều hướng
                    // Chuyển đến màn hình Login khi nhấn nút
                    navController.navigate(Screen.Login.route)
                },
                modifier = Modifier
                    .fillMaxWidth() // Chiếm hết chiều rộng
                    .padding(bottom = 80.dp), // Khoảng cách từ đáy
                shape = RoundedCornerShape(24.dp), // Bo tròn góc
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.white), // Màu nền nút
                    contentColor = colorResource(id = R.color.orange)   // Màu chữ
                )
            ) {
                Text(
                    text = "Bắt đầu",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(vertical = 8.dp) // Thêm padding cho chữ
                )
            }
        }
    }
}

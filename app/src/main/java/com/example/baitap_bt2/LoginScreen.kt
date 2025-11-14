package com.example.baitap_bt2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Dùng Box để xếp chồng ảnh nền và nội dung
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.back_ground)) // Màu nền chính của màn hình
    ) {
        // Ảnh nền trang trí ở phía trên
        Image(
            painter = painterResource(id = R.drawable.bg_login),
            contentDescription = "Login Background",
            modifier = Modifier
                .fillMaxWidth()
                // CHIỀU CAO LINH HOẠT: chiếm 40% màn hình
                .fillMaxHeight(0.4f),
            contentScale = ContentScale.Crop
        )

        // Column chứa toàn bộ nội dung, cho phép cuộn khi cần
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // SỬA LỖI 1: Sử dụng Spacer để đẩy nội dung xuống dưới, thay vì padding cứng
            Spacer(modifier = Modifier.fillMaxHeight(0.35f))

            // Tiêu đề "Welcome Back"
            Text(
                text = "Welcome\nBack",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.orange),
                textAlign = TextAlign.Start,
                lineHeight = 45.sp,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            // SỬA LỖI 2: Dùng TextField thay cho OutlinedTextField
            // Email
            TextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Email") },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_email),
                        contentDescription = "Email Icon"
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = TextFieldDefaults.colors(
                    // Làm cho nền của TextField trong suốt để hợp với màu nền chung
                    focusedIndicatorColor = colorResource(id = R.color.orange),
                    unfocusedIndicatorColor = Color.Gray,
                    cursorColor = colorResource(id = R.color.orange),
                    focusedContainerColor=colorResource(R.color.back_ground),
                    unfocusedContainerColor=colorResource(R.color.back_ground)
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password
            TextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Password") },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_password),
                        contentDescription = "Password Icon"
                    )
                },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = colorResource(id = R.color.orange),
                    unfocusedIndicatorColor = Color.Gray,
                    cursorColor = colorResource(id = R.color.orange),
                    focusedContainerColor=colorResource(R.color.back_ground),
                    unfocusedContainerColor=colorResource(R.color.back_ground)
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Nút Login hình tròn
            Button(
                onClick = { navController.navigate(Screen.HomeScreen.route)},
                modifier = Modifier
                    .size(64.dp)
                    .align(Alignment.End), // Căn phải
                shape = CircleShape,
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.orange)
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow),
                    contentDescription = "Login Button",
                    // SỬA LỖI Ở ĐÂY: Thêm tint để đặt màu cho icon
                    tint = Color.Unspecified
                )
            }

            // SỬA LỖI 3: Dùng Spacer với weight để đẩy các phần tử còn lại xuống đáy

            // Các nút đăng nhập bằng mạng xã hội
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly, // Dàn đều các nút
                verticalAlignment = Alignment.CenterVertically
            ) {
                SocialLoginButton(
                    text = "Facebook",
                    icon = R.drawable.ic_facebook,
                    onClick = { /* Login with Facebook */ }
                )
                SocialLoginButton(
                    text = "Google",
                    icon = R.drawable.ic_google,
                    onClick = { /* Login with Google */ }
                )
            }

            // Link to Register
            val annotatedText = buildAnnotatedString {
                append("Are you new user? ")
                pushStringAnnotation(tag = "REGISTER", annotation = "REGISTER")
                withStyle(style = SpanStyle(color = colorResource(id = R.color.orange), fontWeight = FontWeight.Bold)) {
                    append("Register")
                }
                pop()
            }

            ClickableText(
                text = annotatedText,
                onClick = { offset ->
                    annotatedText.getStringAnnotations(tag = "REGISTER", start = offset, end = offset)
                        .firstOrNull()?.let {
                            navController.navigate(Screen.Register.route)
                        }
                },
                modifier = Modifier.padding(vertical = 0.dp)
            )
        }
    }
}

@Composable
private fun SocialLoginButton(text: String, icon: Int, onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.size(width = 140.dp, height = 50.dp),
        shape = RoundedCornerShape(12.dp),
        border = ButtonDefaults.outlinedButtonBorder.copy(width = 1.dp) // làm viền mảnh hơn
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(painter = painterResource(id = icon), contentDescription = "$text icon", modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(text)
        }
    }
}

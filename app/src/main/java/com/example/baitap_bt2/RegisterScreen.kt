package com.example.baitap_bt2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
fun RegisterScreen(navController: NavController) {
    var name by remember { mutableStateOf("Hồng Phước") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Dùng Box để xếp chồng ảnh nền và nội dung
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.back_ground)) // Màu nền chính
    ) {
        // Ảnh nền trang trí ở phía trên
        Image(
            painter = painterResource(id = R.drawable.bg_register),
            contentDescription = "Register Background",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f), // Chiếm 40% chiều cao màn hình
            contentScale = ContentScale.Crop
        )

        // Column chứa toàn bộ nội dung
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Spacer để đẩy nội dung xuống dưới ảnh nền
            Spacer(modifier = Modifier.fillMaxHeight(0.42f))

            // Tiêu đề "Create Account"
            Text(
                text = "Create\nAccount",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.blue), // Màu xanh dương đậm
                textAlign = TextAlign.Start,
                lineHeight = 45.sp,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Name
            TextField(
                value = name,
                onValueChange = { name = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Name") },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_person), // <-- Icon user
                        contentDescription = "Name Icon"
                    )
                },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor  = colorResource(R.color.back_ground),
                    unfocusedContainerColor  = colorResource(R.color.back_ground),
                    focusedIndicatorColor = colorResource(id = R.color.blue),
                    unfocusedIndicatorColor = Color.Gray,
                    cursorColor = colorResource(id = R.color.blue)
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

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
                    focusedContainerColor  = colorResource(R.color.back_ground),
                    unfocusedContainerColor  = colorResource(R.color.back_ground),
                    focusedIndicatorColor = colorResource(id = R.color.blue),
                    unfocusedIndicatorColor = Color.Gray,
                    cursorColor = colorResource(id = R.color.blue)
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
                    focusedContainerColor  = colorResource(R.color.back_ground),
                    unfocusedContainerColor  = colorResource(R.color.back_ground),
                    focusedIndicatorColor = colorResource(id = R.color.blue),
                    unfocusedIndicatorColor = Color.Gray,
                    cursorColor = colorResource(id = R.color.blue)
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Nút Register hình tròn
            Button(
                onClick = { navController.navigate(Screen.Login.route) },
                modifier = Modifier
                    .size(64.dp)
                    .align(Alignment.End), // Căn phải
                shape = CircleShape,
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.blue) // Nền màu xanh dương
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow2),
                    contentDescription = "Register Button",
                    tint = Color.Unspecified
                )
            }

            // Spacer để đẩy link "Forgot password" xuống đáy
            Spacer(modifier = Modifier.weight(1f))

            // Link to Recover Password
            val annotatedText = buildAnnotatedString {
                append("Forget your password? ")
                pushStringAnnotation(tag = "RECOVERY", annotation = "RECOVERY")
                withStyle(style = SpanStyle(color = colorResource(id = R.color.blue), fontWeight = FontWeight.Bold)) {
                    append("Recovery it")
                }
                pop()
            }

            ClickableText(
                text = annotatedText,
                onClick = { offset ->
                    annotatedText.getStringAnnotations(tag = "RECOVERY", start = offset, end = offset)
                        .firstOrNull()?.let {
                            // Điều hướng đến màn hình khôi phục mật khẩu nếu có
                            // navController.navigate(Screen.RecoveryScreen.route)
                        }
                },
                modifier = Modifier.padding(bottom = 0.dp) // Khoảng cách với đáy màn hình
            )
        }
    }
}

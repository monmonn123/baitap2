package com.example.baitap_bt2

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.*
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Thiết lập theme cho ứng dụng (tùy chọn nhưng nên có)
            // Ví dụ: MaterialTheme { ... }
            // Nếu bạn chưa có theme, có thể bọc trong Surface
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                // BƯỚC 3: Gọi hàm Navigation() ở đây
                Navigation()
            }
        }
    }
}
package com.example.baitap21;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout constraintLayout;
    private final Random random = new Random();
    private int[] backgrounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideSystemBars();
        setContentView(R.layout.activity_main);

        constraintLayout = findViewById(R.id.main);
        SwitchCompat changeBackgroundSwitch = findViewById(R.id.change_background_switch);

        backgrounds = new int[]{
                R.drawable.troi, R.drawable.anime, R.drawable.doraemon
        };

        // Đặt hình nền ngẫu nhiên khi khởi động
        changeRandomBackground();

        // Xử lý sự kiện gạt công tắc
        changeBackgroundSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                changeRandomBackground();
            }
        });
    }

    private void changeRandomBackground() {
        if (backgrounds.length > 0) {
            int randomBackground = backgrounds[random.nextInt(backgrounds.length)];
            constraintLayout.setBackgroundResource(randomBackground);
        }
    }

    private void hideSystemBars() {
        WindowInsetsControllerCompat windowInsetsController =
                ViewCompat.getWindowInsetsController(getWindow().getDecorView());
        if (windowInsetsController != null) {
            windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());
            windowInsetsController.setSystemBarsBehavior(
                    WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }
}

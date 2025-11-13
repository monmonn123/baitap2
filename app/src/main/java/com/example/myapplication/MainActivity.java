package com.example.myapplication;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {

    ImageView img1;
    Button img2;
    Switch sw;
    CheckBox ck1;
    RadioGroup radioGroup;
    ConstraintLayout bg;
    ProgressBar progressBar;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các view
        img1 = (ImageView) findViewById(R.id.imageView1);
        img2 = (Button) findViewById(R.id.imageButton1);
        sw = (Switch) findViewById(R.id.switch1);
        ck1 = (CheckBox) findViewById(R.id.checkBox);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
        bg = (ConstraintLayout) findViewById(R.id.constraintLayout1);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        // Ban đầu, vô hiệu hóa RadioGroup
        setRadioGroupEnabled(false);

        // Sự kiện cho nút ON - Điều khiển ProgressBar
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tự đếm progress
                CountDownTimer countDownTimer = new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        int current = progressBar.getProgress();
                        //chạy đều đều >100 quay về 0
                        if (current >= progressBar.getMax()) {
                            current = 0;
                        }
                        progressBar.setProgress(current + 10); //thiết lập progress
                    }

                    @Override
                    public void onFinish() {
                        Toast.makeText(MainActivity.this, "Hết giờ", Toast.LENGTH_LONG).show();
                    }
                };
                countDownTimer.start();
            }
        });

        // Sự kiện cho Switch Wifi
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainActivity.this, "Wifi đang bật", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Wifi đang tắt", Toast.LENGTH_LONG).show();
                }
            }
        });

        // Sự kiện cho CheckBox - Công tắc chính
        ck1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Khi CheckBox được bật, kích hoạt RadioGroup và đổi nền theo lựa chọn hiện tại
                    setRadioGroupEnabled(true);
                    updateBackgroundFromRadioGroup();
                } else {
                    // Khi CheckBox bị tắt, vô hiệu hóa RadioGroup và quay về nền gốc
                    setRadioGroupEnabled(false);
                    bg.setBackgroundResource(R.drawable.bg);
                }
            }
        });

        // Sự kiện cho RadioGroup - Chỉ hoạt động khi CheckBox được bật
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (ck1.isChecked()) {
                    updateBackgroundFromRadioGroup();
                }
            }
        });

        //Sự kiện cho SeekBar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //progress: giá trị của seekbar
                Log.d("AAA", "Giá trị:" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d("AAA", "Start");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("AAA", "Stop");
            }
        });
    }

    // Hiển thị Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Bắt sự kiện cho Menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menuSetting) {
            Toast.makeText(this, "Bạn chọn Setting", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.menuShare) {
            Toast.makeText(this, "Bạn chọn Share", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.menuLogout) {
            Toast.makeText(this, "Bạn chọn Logout", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    // Hàm để thay đổi nền dựa trên lựa chọn của RadioGroup
    private void updateBackgroundFromRadioGroup() {
        int checkedId = radioGroup.getCheckedRadioButtonId();
        if (checkedId == R.id.radioButton) {
            bg.setBackgroundResource(R.drawable.bg3);
        } else if (checkedId == R.id.radioButton2) {
            bg.setBackgroundResource(R.drawable.bg4);
        }
    }

    // Hàm để bật/tắt toàn bộ RadioGroup
    private void setRadioGroupEnabled(boolean enabled) {
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            radioGroup.getChildAt(i).setEnabled(enabled);
        }
    }
}
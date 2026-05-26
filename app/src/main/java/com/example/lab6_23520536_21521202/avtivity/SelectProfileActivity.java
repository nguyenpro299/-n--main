package com.example.lab6_23520536_21521202.avtivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab6_23520536_21521202.R;
import com.google.android.material.button.MaterialButton;

public class SelectProfileActivity extends AppCompatActivity {

    private boolean isProfileSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_profile);

        TextView btnBack = findViewById(R.id.btnBack);
        LinearLayout btnCreateProfile = findViewById(R.id.btnCreateProfile);
        LinearLayout cardProfile1 = findViewById(R.id.cardProfile1);
        MaterialButton btnConfirmProfile = findViewById(R.id.btnConfirmProfile);

        // Nút quay lại
        btnBack.setOnClickListener(v -> finish());

        // Bấm Tạo hồ sơ mới
        btnCreateProfile.setOnClickListener(v -> {
            Toast.makeText(this, "Mở trang điền thông tin tạo hồ sơ...", Toast.LENGTH_SHORT).show();
            // Nơi này sau sẽ nối sang CreateProfileActivity
        });

        // Bấm Chọn hồ sơ có sẵn
        cardProfile1.setOnClickListener(v -> {
            isProfileSelected = true;
            // Đổi màu nền xanh nhạt để người dùng biết là đã chọn thẻ này
            cardProfile1.setBackgroundColor(Color.parseColor("#E8F5E9"));
            Toast.makeText(this, "Đã chọn hồ sơ: Nguyễn Văn A", Toast.LENGTH_SHORT).show();
        });

        // Bấm Nút Tiếp Tục ở dưới đáy
        btnConfirmProfile.setOnClickListener(v -> {
            if (!isProfileSelected) {
                Toast.makeText(this, "Vui lòng chọn một hồ sơ để tiếp tục!", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "Chuyển sang màn hình Thanh Toán...", Toast.LENGTH_SHORT).show();
            // Chuyển sang màn hình Thanh Toán
            Intent intent = new Intent(SelectProfileActivity.this, PaymentActivity.class);
            startActivity(intent);
        });
    }
}
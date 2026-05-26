package com.example.lab6_23520536_21521202.avtivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab6_23520536_21521202.R;
import com.google.android.material.button.MaterialButton;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        TextView btnBack = findViewById(R.id.btnBack);
        MaterialButton btnPaymentDone = findViewById(R.id.btnPaymentDone);

        // Nút quay lại
        btnBack.setOnClickListener(v -> finish());

        // Bấm Đã Thanh Toán (Kết thúc luồng đặt khám)
        btnPaymentDone.setOnClickListener(v -> {
            Toast.makeText(this, "Thanh toán thành công! Đã lưu vào lịch sử.", Toast.LENGTH_LONG).show();

            // Đưa người dùng quay hẳn về Màn hình chính (MainActivity)
            // Xóa toàn bộ các trang trước đó (chọn khoa, chọn giờ...) để không bấm Back lại được
            Intent intent = new Intent(PaymentActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}
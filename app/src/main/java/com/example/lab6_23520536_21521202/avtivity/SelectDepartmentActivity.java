package com.example.lab6_23520536_21521202.avtivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab6_23520536_21521202.R;

public class SelectDepartmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_department);

        TextView btnBack = findViewById(R.id.btnBack);
        LinearLayout cardKhoaNoi = findViewById(R.id.cardKhoaNoi);
        LinearLayout cardKhoaNgoai = findViewById(R.id.cardKhoaNgoai);
        LinearLayout cardChanThuong = findViewById(R.id.cardChanThuong);
        LinearLayout cardUngBuou = findViewById(R.id.cardUngBuou);

        // Nút quay lại
        btnBack.setOnClickListener(v -> finish());

        // Bấm chọn Chấn thương chỉnh hình
        cardChanThuong.setOnClickListener(v -> {
            android.content.Intent intent = new android.content.Intent(SelectDepartmentActivity.this, SelectDateTimeActivity.class);
            startActivity(intent);
        });

        cardKhoaNoi.setOnClickListener(v -> Toast.makeText(this, "Bạn chọn Khoa Nội", Toast.LENGTH_SHORT).show());
        cardKhoaNgoai.setOnClickListener(v -> Toast.makeText(this, "Bạn chọn Khoa Ngoại", Toast.LENGTH_SHORT).show());
        cardUngBuou.setOnClickListener(v -> Toast.makeText(this, "Bạn chọn Ung bướu", Toast.LENGTH_SHORT).show());
    }
}
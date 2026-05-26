package com.example.lab6_23520536_21521202.avtivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
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

        // Gắn sự kiện: Bấm vào khoa nào thì truyền tên khoa đó qua hàm navigateToNextScreen
        cardKhoaNoi.setOnClickListener(v -> navigateToNextScreen("Khoa Nội"));
        cardKhoaNgoai.setOnClickListener(v -> navigateToNextScreen("Khoa Ngoại"));
        cardChanThuong.setOnClickListener(v -> navigateToNextScreen("Chấn thương chỉnh hình"));
        cardUngBuou.setOnClickListener(v -> navigateToNextScreen("Ung bướu"));
    }

    // Hàm nhận tên khoa và đóng gói vào Intent để gửi sang màn hình tiếp theo
    private void navigateToNextScreen(String deptName) {
        Intent intent = new Intent(SelectDepartmentActivity.this, SelectDateTimeActivity.class);
        // Đóng gói tên khoa vào để trang sau nhận được
        intent.putExtra("DEPARTMENT_KEY", deptName);
        startActivity(intent);
    }
}
package com.example.lab6_23520536_21521202.avtivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab6_23520536_21521202.R;
import com.google.android.material.button.MaterialButton;

public class SelectDateTimeActivity extends AppCompatActivity {

    private String selectedDate = "";
    private String selectedTime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_date_time);

        TextView btnBack = findViewById(R.id.btnBack);
        CalendarView calendarView = findViewById(R.id.calendarView);
        MaterialButton btnConfirm = findViewById(R.id.btnConfirmDateTime);

        // Các nút giờ
        MaterialButton[] timeButtons = {
                findViewById(R.id.btnTime1), findViewById(R.id.btnTime2), findViewById(R.id.btnTime3),
                findViewById(R.id.btnTime4), findViewById(R.id.btnTime5), findViewById(R.id.btnTime6)
        };

        btnBack.setOnClickListener(v -> finish());

        // Lấy ngày người dùng chọn trên lịch
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
        });

        // Xử lý khi bấm chọn 1 khung giờ
        for (MaterialButton btn : timeButtons) {
            btn.setOnClickListener(v -> {
                selectedTime = btn.getText().toString();
                Toast.makeText(this, "Bạn chọn giờ: " + selectedTime, Toast.LENGTH_SHORT).show();
                // (Tùy chọn) Đổi màu nút để biết là đang chọn
            });
        }

        // Bấm Xác nhận
        btnConfirm.setOnClickListener(v -> {
            if (selectedDate.isEmpty()) {
                Toast.makeText(this, "Vui lòng chọn ngày khám (Bấm vào lịch)!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (selectedTime.isEmpty()) {
                Toast.makeText(this, "Vui lòng chọn giờ khám!", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "Đã chọn: " + selectedTime + " ngày " + selectedDate, Toast.LENGTH_LONG).show();
            // Chuyển sang màn hình Chọn Hồ Sơ
            android.content.Intent intent = new android.content.Intent(SelectDateTimeActivity.this, SelectProfileActivity.class);
            startActivity(intent);
        });
    }
}
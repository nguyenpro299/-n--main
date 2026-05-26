package com.example.lab6_23520536_21521202.avtivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab6_23520536_21521202.R;
import com.google.android.material.button.MaterialButton;
import java.util.Calendar;

public class SelectDateTimeActivity extends AppCompatActivity {

    private String selectedDate = "";
    private String selectedTime = "";
    private String department = ""; // Biến mới để lưu tên khoa

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_date_time);

        // NHẬN TÊN KHOA TỪ MÀN HÌNH TRƯỚC GỬI SANG
        department = getIntent().getStringExtra("DEPARTMENT_KEY");

        TextView btnBack = findViewById(R.id.btnBack);
        CalendarView calendarView = findViewById(R.id.calendarView);
        MaterialButton btnConfirm = findViewById(R.id.btnConfirmDateTime);

        MaterialButton[] timeButtons = {
                findViewById(R.id.btnTime1), findViewById(R.id.btnTime2), findViewById(R.id.btnTime3),
                findViewById(R.id.btnTime4), findViewById(R.id.btnTime5), findViewById(R.id.btnTime6)
        };

        btnBack.setOnClickListener(v -> finish());

        // XỬ LÝ CHỌN NGÀY
        Calendar calendar = Calendar.getInstance();
        selectedDate = calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR);

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
        });

        // XỬ LÝ CHỌN GIỜ
        for (MaterialButton btn : timeButtons) {
            btn.setOnClickListener(v -> {
                selectedTime = btn.getText().toString();
                for (MaterialButton b : timeButtons) {
                    b.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
                    b.setTextColor(Color.parseColor("#666666"));
                    b.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#CCCCCC")));
                    b.setStrokeWidth(2);
                }
                btn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#388E3C")));
                btn.setTextColor(Color.WHITE);
                btn.setStrokeWidth(0);
            });
        }

        // XÁC NHẬN -> ĐÓNG GÓI DỮ LIỆU GỬI SANG MÀN HÌNH HỒ SƠ
        btnConfirm.setOnClickListener(v -> {
            if (selectedDate.isEmpty()) {
                Toast.makeText(this, "Vui lòng chọn ngày khám!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (selectedTime.isEmpty()) {
                Toast.makeText(this, "Vui lòng chọn giờ khám!", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(SelectDateTimeActivity.this, SelectProfileActivity.class);
            // ĐÓNG GÓI DỮ LIỆU ĐỂ GỬI ĐI
            intent.putExtra("DEPARTMENT_KEY", department); // Truyền tiếp tên khoa
            intent.putExtra("DATE_KEY", selectedDate);
            intent.putExtra("TIME_KEY", selectedTime);
            startActivity(intent);
        });
    }
}
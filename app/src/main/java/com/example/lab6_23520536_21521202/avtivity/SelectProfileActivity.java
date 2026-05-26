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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SelectProfileActivity extends AppCompatActivity {

    private boolean isProfileSelected = false;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    // Biến lưu dữ liệu nhận từ màn hình trước
    private String department, date, time;
    private String patientName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_profile);

        // NHẬN DỮ LIỆU TỪ MÀN HÌNH CHỌN NGÀY GIỜ GỬI QUA
        department = getIntent().getStringExtra("DEPARTMENT_KEY");
        date = getIntent().getStringExtra("DATE_KEY");
        time = getIntent().getStringExtra("TIME_KEY");

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        TextView btnBack = findViewById(R.id.btnBack);
        LinearLayout btnCreateProfile = findViewById(R.id.btnCreateProfile);
        LinearLayout cardProfile1 = findViewById(R.id.cardProfile1);
        MaterialButton btnConfirmProfile = findViewById(R.id.btnConfirmProfile);

        TextView tvName = findViewById(R.id.tvProfileName);
        TextView tvInfo = findViewById(R.id.tvProfileInfo);
        TextView tvPhone = findViewById(R.id.tvProfilePhone);
        TextView tvAddress = findViewById(R.id.tvProfileAddress);

        loadProfileDataFromFirebase(tvName, tvInfo, tvPhone, tvAddress);

        btnBack.setOnClickListener(v -> finish());

        cardProfile1.setOnClickListener(v -> {
            isProfileSelected = true;
            cardProfile1.setBackgroundColor(Color.parseColor("#E8F5E9"));
            String displayName = patientName.isEmpty() ? "Hồ sơ của bạn" : patientName;
            Toast.makeText(this, "Đã chọn hồ sơ: " + displayName, Toast.LENGTH_SHORT).show();
        });

        // BẤM TIẾP TỤC THANH TOÁN -> GỬI TOÀN BỘ DỮ LIỆU QUA PAYMENTACTIVITY
        btnConfirmProfile.setOnClickListener(v -> {
            if (!isProfileSelected) {
                Toast.makeText(this, "Vui lòng chọn một hồ sơ!", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(SelectProfileActivity.this, PaymentActivity.class);
            // ĐÓNG GÓI DỮ LIỆU
            intent.putExtra("DEPARTMENT_KEY", department);
            intent.putExtra("DATE_KEY", date);
            intent.putExtra("TIME_KEY", time);
            intent.putExtra("PATIENT_NAME_KEY", patientName); // patientName đã lấy được từ Firebase ở trên

            startActivity(intent);
        });
    }

    private void loadProfileDataFromFirebase(TextView tvName, TextView tvInfo, TextView tvPhone, TextView tvAddress) {
        if (mAuth.getCurrentUser() == null) return;
        String userId = mAuth.getCurrentUser().getUid();

        db.collection("users").document(userId).get()
                .addOnSuccessListener(document -> {
                    if (document != null && document.exists()) {
                        patientName = document.getString("fullName");
                        String dob = document.getString("dob");
                        String gender = document.getString("gender");
                        String phone = document.getString("phone");
                        String address = document.getString("address");

                        if (tvName != null) tvName.setText("Tên: " + (patientName != null ? patientName.toUpperCase() : "CHƯA CẬP NHẬT"));
                        if (tvInfo != null) tvInfo.setText("Ngày sinh: " + (dob != null ? dob : "...") + " | GT: " + (gender != null ? gender : "..."));
                        if (tvPhone != null) tvPhone.setText("SĐT: " + (phone != null ? phone : "..."));
                        if (tvAddress != null) tvAddress.setText("Địa chỉ: " + (address != null ? address : "..."));
                    }
                });
    }
}
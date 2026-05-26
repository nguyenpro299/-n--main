package com.example.lab6_23520536_21521202.avtivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab6_23520536_21521202.R;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class PaymentActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        // 1. NHẬN DỮ LIỆU TỪ CÁC MÀN HÌNH TRƯỚC GỬI SANG
        String department = getIntent().getStringExtra("DEPARTMENT_KEY");
        String date = getIntent().getStringExtra("DATE_KEY");
        String time = getIntent().getStringExtra("TIME_KEY");
        String patientName = getIntent().getStringExtra("PATIENT_NAME_KEY");

        TextView btnBack = findViewById(R.id.btnBack);
        MaterialButton btnPaymentDone = findViewById(R.id.btnPaymentDone);

        btnBack.setOnClickListener(v -> finish());

        btnPaymentDone.setOnClickListener(v -> {
            String userId = mAuth.getCurrentUser() != null ? mAuth.getCurrentUser().getUid() : "unknown";

            // 2. TẠO DỮ LIỆU ĐỘNG (Dùng biến nhận được ở trên)
            Map<String, Object> appointment = new HashMap<>();
            appointment.put("userId", userId);
            appointment.put("status", "Đã thanh toán");
            appointment.put("timestamp", System.currentTimeMillis());

            // Đưa dữ liệu động vào đây (nếu bị null thì dùng "N/A")
            appointment.put("department", department != null ? department : "N/A");
            appointment.put("date", date != null ? date : "N/A");
            appointment.put("time", time != null ? time : "N/A");
            appointment.put("patientName", patientName != null ? patientName : "N/A");

            db.collection("appointments").add(appointment)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(this, "Thanh toán thành công!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(PaymentActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    })
                    .addOnFailureListener(e -> Toast.makeText(this, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        });
    }
}
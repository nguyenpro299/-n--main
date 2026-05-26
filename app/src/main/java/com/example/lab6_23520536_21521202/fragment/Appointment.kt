package com.example.lab6_23520536_21521202

data class Appointment(
    var department: String = "",
    var date: String = "",
    var time: String = "",
    var patientName: String = "",
    var status: String = "", // Đừng gán giá trị mặc định "Đã thanh toán" ở đây nếu muốn Firestore tự load giá trị thật
    var userId: String = "",
    var timestamp: Long = 0L
)
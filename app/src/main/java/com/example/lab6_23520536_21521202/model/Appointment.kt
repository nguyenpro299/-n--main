package com.example.lab6_23520536_21521202.model

data class Appointment(
    val id: String = "",
    val userId: String = "",
    val patientName: String = "",
    val department: String = "",
    val serviceType: String = "",
    val date: String = "",
    val time: String = "",
    val price: Long = 0,
    val status: String = "pending", // pending, confirmed, completed
    val timestamp: Long = System.currentTimeMillis()
)
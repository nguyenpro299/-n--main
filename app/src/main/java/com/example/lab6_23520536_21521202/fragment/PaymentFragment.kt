package com.example.lab6_23520536_21521202

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.lab6_23520536_21521202.databinding.FragmentPaymentBinding
import com.example.lab6_23520536_21521202.model.Appointment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID

class PaymentFragment : Fragment() {

    private var _binding: FragmentPaymentBinding? = null
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()
    private val mAuth = FirebaseAuth.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnDaThanhToan.setOnClickListener {
            saveAppointmentToFirestore()
        }
    }

    private fun saveAppointmentToFirestore() {
        val userId = mAuth.currentUser?.uid ?: return

        val appointment = Appointment(
            id = UUID.randomUUID().toString(),
            userId = userId,
            patientName = "Phạm Cao Minh Hoàng",   // Lấy từ hồ sơ sau
            department = "Khoa Chấn Thương Chỉnh Hình",
            serviceType = "Khám dịch vụ yêu cầu",
            date = "27/05/2026",
            time = "09:00",
            price = 230000,
            status = "confirmed"
        )

        db.collection("appointments")
            .document(appointment.id)
            .set(appointment)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Thanh toán thành công! Lịch khám đã được lưu.", Toast.LENGTH_LONG).show()
                requireActivity().onBackPressed() // Quay về màn hình lịch khám
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Lưu lịch thất bại: ${it.message}", Toast.LENGTH_LONG).show()
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
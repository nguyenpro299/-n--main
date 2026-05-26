package com.example.lab6_23520536_21521202.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab6_23520536_21521202.Appointment
import com.example.lab6_23520536_21521202.AppointmentAdapter
import com.example.lab6_23520536_21521202.databinding.FragmentPaymentHistoryBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class PaymentHistoryFragment : Fragment() {

    private var _binding: FragmentPaymentHistoryBinding? = null
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()
    private val mAuth = FirebaseAuth.getInstance()
    private val TAG = "CHECK_DATA"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPaymentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvPayments.layoutManager = LinearLayoutManager(requireContext())
        loadPaymentHistory()
    }

    private fun loadPaymentHistory() {
        val user = mAuth.currentUser
        if (user == null) {
            Log.e(TAG, "Lỗi: User chưa đăng nhập!")
            return
        }

        val myUserId = user.uid
        Log.d(TAG, "Đang query với UserID: $myUserId")

        // Truy vấn dữ liệu
        db.collection("appointments")
            .whereEqualTo("userId", myUserId)
            .whereEqualTo("status", "Đã thanh toán")
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { documents ->
                Log.d(TAG, "Số lượng bản ghi tìm thấy: ${documents.size()}")

                if (_binding == null) return@addOnSuccessListener

                if (documents.isEmpty) {
                    binding.emptyView.visibility = View.VISIBLE
                    binding.rvPayments.visibility = View.GONE
                    Log.d(TAG, "Firestore trả về rỗng (Không tìm thấy đơn nào khớp điều kiện)")
                } else {
                    binding.emptyView.visibility = View.GONE
                    binding.rvPayments.visibility = View.VISIBLE

                    val paymentList = documents.toObjects(Appointment::class.java)
                    Log.d(TAG, "Đã chuyển đổi thành công ${paymentList.size} object")

                    binding.rvPayments.adapter = AppointmentAdapter(paymentList)
                }
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Lỗi truy vấn: ${e.message}")
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
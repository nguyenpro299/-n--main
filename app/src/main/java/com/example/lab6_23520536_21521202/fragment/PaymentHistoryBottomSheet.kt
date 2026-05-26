package com.example.lab6_23520536_21521202.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab6_23520536_21521202.Appointment
import com.example.lab6_23520536_21521202.AppointmentAdapter
import com.example.lab6_23520536_21521202.databinding.FragmentPaymentHistoryBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class PaymentHistoryBottomSheet : BottomSheetDialogFragment() {

    private var _binding: FragmentPaymentHistoryBinding? = null
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()
    private val mAuth = FirebaseAuth.getInstance()

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
        val userId = mAuth.currentUser?.uid ?: return
        db.collection("appointments")
            .whereEqualTo("userId", userId)
            .whereEqualTo("status", "Đã thanh toán")
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { documents ->
                if (_binding == null) return@addOnSuccessListener
                if (documents.isEmpty) {
                    binding.emptyView.visibility = View.VISIBLE
                    binding.rvPayments.visibility = View.GONE
                } else {
                    val paymentList = documents.toObjects(Appointment::class.java)
                    binding.rvPayments.adapter = AppointmentAdapter(paymentList)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
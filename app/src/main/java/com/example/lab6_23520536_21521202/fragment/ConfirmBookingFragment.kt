package com.example.lab6_23520536_21521202

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.lab6_23520536_21521202.databinding.FragmentConfirmBookingBinding

class ConfirmBookingFragment : Fragment() {

    private var _binding: FragmentConfirmBookingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfirmBookingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnXacNhan.setOnClickListener {
            Toast.makeText(requireContext(), "Xác nhận đặt lịch thành công!", Toast.LENGTH_SHORT).show()
            // Chuyển sang Payment
            // Navigation.findNavController(it).navigate(R.id.action_to_payment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
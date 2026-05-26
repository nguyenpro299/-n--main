package com.example.lab6_23520536_21521202

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lab6_23520536_21521202.databinding.FragmentPatientSelectBinding

class PatientSelectFragment : Fragment() {

    private var _binding: FragmentPatientSelectBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPatientSelectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnTaoHoSoMoi.setOnClickListener {
            // Chuyển sang màn hình tạo hồ sơ
        }
    }
}
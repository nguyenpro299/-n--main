package com.example.lab6_23520536_21521202.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.lab6_23520536_21521202.ChatFragment
import com.example.lab6_23520536_21521202.R
import com.google.android.material.button.MaterialButton

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Nạp giao diện từ file XML
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Ánh xạ các nút bằng findViewById (Tránh hoàn toàn lỗi ViewBinding)
        val btnHoTroKham = view.findViewById<LinearLayout>(R.id.btn_ho_tro_kham)
        val btnDatKhamMain = view.findViewById<MaterialButton>(R.id.btn_dat_kham_main)

        // Bắt sự kiện click
        btnHoTroKham.setOnClickListener {
            // Tuyệt chiêu tự động tìm khung chứa và nhảy sang ChatFragment
            val chatFragment = ChatFragment()
            val containerId = (requireView().parent as android.view.ViewGroup).id

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(containerId, chatFragment)
                .addToBackStack(null) // Cho phép bấm nút Back để quay lại trang chủ
                .commit()
        }

        btnDatKhamMain.setOnClickListener {
            // Mở màn hình Chọn Khoa
            val intent = android.content.Intent(requireContext(), com.example.lab6_23520536_21521202.avtivity.SelectDepartmentActivity::class.java)
            startActivity(intent)
        }}
    }
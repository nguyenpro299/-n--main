package com.example.lab6_23520536_21521202.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.lab6_23520536_21521202.ChatFragment
import com.example.lab6_23520536_21521202.R
import com.example.lab6_23520536_21521202.auth.LoginActivity
import com.example.lab6_23520536_21521202.avtivity.QrScannerActivity
import com.example.lab6_23520536_21521202.avtivity.SelectDepartmentActivity
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. CÁ NHÂN HÓA LỜI CHÀO
        val currentUser = FirebaseAuth.getInstance().currentUser
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        actionBar?.title = if (currentUser != null) "Hi, ${currentUser.displayName ?: "bạn"} 👋" else "Medical App"

        // 2. ÁNH XẠ CÁC NÚT
        val btnHoTroKham = view.findViewById<LinearLayout>(R.id.btn_ho_tro_kham)
        val btnDatKhamMain = view.findViewById<MaterialButton>(R.id.btn_dat_kham_main)
        val btnQuetQr = view.findViewById<LinearLayout>(R.id.btn_quet_qr)
        val btnHoSo = view.findViewById<LinearLayout>(R.id.btn_ho_so)
        val btnLichSu = view.findViewById<LinearLayout>(R.id.btn_lich_su)
        val btnCaiDat = view.findViewById<LinearLayout>(R.id.btn_cai_dat)

        // 3. XỬ LÝ SỰ KIỆN CLICK
        btnHoTroKham?.setOnClickListener {
            requireLogin {
                requireActivity().supportFragmentManager.beginTransaction()
                    .add(android.R.id.content, ChatFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }

        btnDatKhamMain?.setOnClickListener {
            requireLogin {
                startActivity(Intent(requireContext(), SelectDepartmentActivity::class.java))
            }
        }

        btnQuetQr?.setOnClickListener {
            requireLogin {
                startActivity(Intent(requireContext(), QrScannerActivity::class.java))
            }
        }

        btnHoSo?.setOnClickListener {
            requireLogin {
                requireActivity().supportFragmentManager.beginTransaction()
                    .add(android.R.id.content, ProfileFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }

        btnCaiDat?.setOnClickListener {
            requireLogin {
                requireActivity().supportFragmentManager.beginTransaction()
                    .add(android.R.id.content, SettingsFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }

        // --- PHẦN SỬA ĐỔI: DÙNG BOTTOM SHEET ---
        btnLichSu?.setOnClickListener {
            requireLogin {
                PaymentHistoryBottomSheet().show(parentFragmentManager, "PaymentHistorySheet")
            }
        }

        // 4. CÁC NÚT CHƯA PHÁT TRIỂN
        val unimplementedButtons = listOf(
            view.findViewById<LinearLayout>(R.id.btn_tra_cuu_hoa_don),
            view.findViewById<LinearLayout>(R.id.btn_tra_cuu_kq),
            view.findViewById<LinearLayout>(R.id.btn_lich_su_hien_mau),
            view.findViewById<LinearLayout>(R.id.btn_tra_cuu_ban_do)
        )
        for (btn in unimplementedButtons) {
            btn?.setOnClickListener {
                requireLogin { Toast.makeText(requireContext(), "Tính năng đang phát triển!", Toast.LENGTH_SHORT).show() }
            }
        }
    }

    private fun requireLogin(action: () -> Unit) {
        if (FirebaseAuth.getInstance().currentUser != null) action()
        else {
            Toast.makeText(requireContext(), "Vui lòng đăng nhập!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(requireContext(), LoginActivity::class.java))
        }
    }
}
package com.example.lab6_23520536_21521202.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab6_23520536_21521202.R
import com.example.lab6_23520536_21521202.adapter.DepartmentAdapter
import com.example.lab6_23520536_21521202.databinding.FragmentDepartmentBinding
import com.example.lab6_23520536_21521202.model.DepartmentItem

class DepartmentFragment : Fragment() {

    private var _binding: FragmentDepartmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDepartmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val departments = listOf(
            DepartmentItem("B1.i", "Khoa Khám chấn thương chỉnh hình", "Tầng 1, tòa nhà Viện chấn thương chỉnh hình."),
            DepartmentItem("C1A", "Khoa Khám Bệnh", "Tòa nhà 1000 giường"),
            DepartmentItem("VUB-YHHN", "Khám Ung bướu hoặc Y học hạt nhân", "Tòa Nhà A-B, Viện Ung Bướu và Y Học Hạt Nhân"),
            DepartmentItem("TYC", "Khám dịch vụ yêu cầu", "Tầng 2, Khu khám bệnh theo yêu cầu")
        )

        val adapter = DepartmentAdapter(departments) { department: DepartmentItem ->
            val bundle = Bundle().apply {
                putString("departmentName", department.name)
                putString("departmentCode", department.code)
            }
            findNavController().navigate(R.id.action_departmentFragment_to_serviceFragment, bundle)
        }

        binding.rvDepartments.layoutManager = LinearLayoutManager(requireContext())
        binding.rvDepartments.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
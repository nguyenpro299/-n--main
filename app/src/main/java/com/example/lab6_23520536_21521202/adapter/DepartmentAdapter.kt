package com.example.lab6_23520536_21521202.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab6_23520536_21521202.databinding.ItemDepartmentBinding
import com.example.lab6_23520536_21521202.model.DepartmentItem

class DepartmentAdapter(
    private val departments: List<DepartmentItem>,
    private val onClick: (DepartmentItem) -> Unit
) : RecyclerView.Adapter<DepartmentAdapter.DepartmentViewHolder>() {

    inner class DepartmentViewHolder(private val binding: ItemDepartmentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(department: DepartmentItem) {
            binding.tvCode.text = department.code
            binding.tvName.text = department.name
            binding.tvDescription.text = department.description

            binding.root.setOnClickListener {
                onClick(department)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentViewHolder {
        val binding = ItemDepartmentBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return DepartmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DepartmentViewHolder, position: Int) {
        holder.bind(departments[position])
    }

    override fun getItemCount() = departments.size
}
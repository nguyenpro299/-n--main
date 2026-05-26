package com.example.lab6_23520536_21521202

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab6_23520536_21521202.databinding.FragmentAppointmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

// 1. ADAPTER ĐỂ VẼ DANH SÁCH
class AppointmentAdapter(private val list: List<Appointment>) : RecyclerView.Adapter<AppointmentAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvDep: TextView = view.findViewById(R.id.tvDep)
        val tvDate: TextView = view.findViewById(R.id.tvDate)
        val tvStatus: TextView = view.findViewById(R.id.tvStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_appointment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.tvDep.text = "Khoa: ${item.department}"
        holder.tvDate.text = "Ngày: ${item.date} - ${item.time}"
        holder.tvStatus.text = "Trạng thái: ${item.status}"
    }

    override fun getItemCount() = list.size
}

// 2. FRAGMENT CẬP NHẬT
class AppointmentFragment : Fragment() {
    private var _binding: FragmentAppointmentBinding? = null
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()
    private val mAuth = FirebaseAuth.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAppointmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvAppointments.layoutManager = LinearLayoutManager(requireContext())
        loadAppointments()
    }

    private fun loadAppointments() {
        val userId = mAuth.currentUser?.uid ?: return

        db.collection("appointments")
            .whereEqualTo("userId", userId)
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { documents ->
                if (_binding == null) return@addOnSuccessListener

                if (documents.isEmpty) {
                    binding.emptyView.visibility = View.VISIBLE
                    binding.rvAppointments.visibility = View.GONE
                } else {
                    binding.emptyView.visibility = View.GONE
                    binding.rvAppointments.visibility = View.VISIBLE

                    // CHUYỂN ĐỔI FIREBASE DATA SANG LIST VÀ GẮN VÀO ADAPTER
                    val appointmentList = documents.toObjects(Appointment::class.java)
                    binding.rvAppointments.adapter = AppointmentAdapter(appointmentList)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
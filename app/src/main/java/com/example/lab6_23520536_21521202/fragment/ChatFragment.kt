package com.example.lab6_23520536_21521202

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab6_23520536_21521202.databinding.FragmentChatBinding
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    private val chatMessages = mutableListOf<ChatMessage>()
    private lateinit var adapter: ChatAdapter

    // Gemini Model
    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = "AIzaSyCSAztclmQudJQ0O3NXSsEwVLnGX4hzOfY"   // ← Thay bằng key thật sau
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ChatAdapter(chatMessages)
        binding.rvChat.layoutManager = LinearLayoutManager(requireContext())
        binding.rvChat.adapter = adapter

        addBotMessage("Xin chào! Tôi là trợ lý AI y tế. Bạn cần hỗ trợ gì hôm nay?")

        binding.btnSend.setOnClickListener {
            val message = binding.etMessage.text?.toString()?.trim() ?: ""
            if (message.isNotEmpty()) {
                addUserMessage(message)
                binding.etMessage.text?.clear()
                getGeminiResponse(message)
            }
        }
    }

    private fun addUserMessage(text: String) {
        chatMessages.add(ChatMessage(text, true))
        adapter.notifyItemInserted(chatMessages.size - 1)
        binding.rvChat.scrollToPosition(chatMessages.size - 1)
    }

    private fun addBotMessage(text: String) {
        chatMessages.add(ChatMessage(text, false))
        adapter.notifyItemInserted(chatMessages.size - 1)
        binding.rvChat.scrollToPosition(chatMessages.size - 1)
    }

    private fun getGeminiResponse(question: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = generativeModel.generateContent(question)
                withContext(Dispatchers.Main) {
                    addBotMessage(response.text ?: "Tôi chưa hiểu rõ câu hỏi.")
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    addBotMessage("Xin lỗi, hiện tại tôi không thể trả lời. Vui lòng thử lại sau.")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
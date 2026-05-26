package com.example.lab6_23520536_21521202

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab6_23520536_21521202.databinding.ItemChatBinding

class ChatAdapter(private val messages: List<ChatMessage>) :
    RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    class ChatViewHolder(val binding: ItemChatBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val message = messages[position]
        if (message.isUser) {
            holder.binding.tvUserMessage.text = message.text
            holder.binding.tvUserMessage.visibility = android.view.View.VISIBLE
            holder.binding.tvBotMessage.visibility = android.view.View.GONE
        } else {
            holder.binding.tvBotMessage.text = message.text
            holder.binding.tvBotMessage.visibility = android.view.View.VISIBLE
            holder.binding.tvUserMessage.visibility = android.view.View.GONE
        }
    }

    override fun getItemCount() = messages.size
}
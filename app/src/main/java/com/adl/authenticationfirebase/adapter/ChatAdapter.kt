package com.adl.ujiancrud.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.adl.authenticationfirebase.R
import com.adl.authenticationfirebase.model.Message


class ChatAdapter(val data: ArrayList<Message>):RecyclerView.Adapter<ChatViewHolder>() {
    lateinit var parent:ViewGroup
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        this.parent = parent

        return ChatViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_chat,parent,false))
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
       holder.bindData(this@ChatAdapter,position)
    }

    override fun getItemCount(): Int {
      return data.size
    }
}
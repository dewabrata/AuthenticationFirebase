package com.adl.ujiancrud.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.item_chat.view.*
import java.util.*


class ChatViewHolder(view:View):RecyclerView.ViewHolder(view) {
    val pesan = view.txtPesan
    val time = view.txtTime



    fun bindData(adapter:ChatAdapter, position:Int){


        time.setText(adapter.data.get(position)?.timeStamp.toString())
        pesan.setText(adapter.data.get(position)?.pesan.toString())








    }

}
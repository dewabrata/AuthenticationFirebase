package com.adl.recycleviewadl.adapter

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.adl.authenticationfirebase.model.AktifitasModel
import com.adl.tasklist.adapter.TaskListAdapter

import kotlinx.android.synthetic.main.item_task.view.*


class TasklistVH(view: View):RecyclerView.ViewHolder(view) {

    val txtNo = view.txtNo
    val txtTaskList = view.txtTaskList
    val txtJam = view.txtJam
    val btnDelete = view.btnDelete



    fun tempelData(data: AktifitasModel, position:Int,adapter:TaskListAdapter ){




        txtNo.setText((position+1).toString())
        txtTaskList.setText(data.task)
        txtJam.setText(data.jam)

        btnDelete.setOnClickListener({
            adapter.deleteData(position)
        })



    }



}
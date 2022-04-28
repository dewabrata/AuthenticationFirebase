package com.adl.tasklist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adl.authenticationfirebase.R
import com.adl.authenticationfirebase.model.AktifitasModel
import com.adl.authenticationfirebase.model.Message
import com.adl.recycleviewadl.adapter.TasklistVH
import com.google.firebase.firestore.FirebaseFirestore


class TaskListAdapter(val data: ArrayList<AktifitasModel>): RecyclerView.Adapter<TasklistVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasklistVH {
        return TasklistVH(
            LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task,parent,false))

    }

    override fun onBindViewHolder(holder: TasklistVH, position: Int) {
        holder.tempelData(data.get(position),position,this@TaskListAdapter)
    }

    override fun getItemCount(): Int {
       return data.size
    }

    fun deleteData(position:Int){

       val dataDelete:AktifitasModel = data.get(position)

      //  FirebaseFirestore.getInstance().collection("tasklist").document(dataDelete.id.toString()).addSnapshotListener { value, error ->  }

        FirebaseFirestore.getInstance().collection("tasklist").whereEqualTo("id",data.get(position).id).addSnapshotListener { value, error ->
            if (value!=null){
                for(data in value){
                    data.reference.delete()
                }
            }
        }
        data.removeAt(position)

        notifyDataSetChanged()
    }


}
package com.adl.authenticationfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.adl.authenticationfirebase.model.AktifitasModel
import com.adl.tasklist.adapter.TaskListAdapter
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.aktifitas.*

class ListAktifitas : AppCompatActivity() {
    lateinit var db:FirebaseFirestore
    lateinit var adlAdapter:TaskListAdapter
    lateinit var lstRV:ArrayList<AktifitasModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aktifitas)
        db = FirebaseFirestore.getInstance()

         loadData()

        btnAddTasklist.setOnClickListener({
            val intent = Intent(this@ListAktifitas,AddTaskList::class.java)
           startActivity(intent)
        })

    }

    fun loadData() {
       var  lstTemp: List<AktifitasModel>? = null
        var  lstDummy: ArrayList<AktifitasModel> = ArrayList<AktifitasModel>()
        db.collection("tasklist").get().addOnSuccessListener { document ->


            Log.d("Success", document.toString())

        /*    for(data in document){
                lstDummy.add(AktifitasModel(data.id,data.get("task").toString(),data.get("jam").toString()))
            }

         */
            lstTemp = document.toObjects(AktifitasModel::class.java)

            adlAdapter = TaskListAdapter(ArrayList(lstTemp))
            recyclerView.apply {
                layoutManager = LinearLayoutManager(this@ListAktifitas)
                adapter = adlAdapter
            }



        }.addOnFailureListener { e ->
            Log.d("Erorr", e.toString())
        }

    }

    override fun onResume() {
        super.onResume()
        loadData()
    }
}
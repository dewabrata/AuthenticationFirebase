package com.adl.authenticationfirebase

import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TimePicker
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_add_tasklist.*
import kotlinx.android.synthetic.main.item_task.*
import java.util.*

class AddTaskList : AppCompatActivity() {
    lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_tasklist)
        db = FirebaseFirestore.getInstance()
        val mTimePicker: TimePickerDialog
        val mCurrentTime = Calendar.getInstance()
        val hour = mCurrentTime.get(Calendar.HOUR_OF_DAY)
        val minutes = mCurrentTime.get(Calendar.MINUTE)
        mTimePicker = TimePickerDialog(this, object : TimePickerDialog.OnTimeSetListener {
            override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                txtJamEntry.setText(String.format("%d : %d", hourOfDay, minute))
            }
        }, hour, minutes, false)

        btnJam.setOnClickListener({ v ->
            mTimePicker.show()
        })

        btnAddTaskList.setOnClickListener({

            val data = hashMapOf(
                "id"  to System.currentTimeMillis().toString(),
                "jam" to txtJamEntry.text.toString(),
                "task" to txtTaskListEntry.text.toString()

            )

            // db.collection("tasklist").document(System.currentTimeMillis().toString()).set(data).addOnSuccessListener { document ->

            db.collection("tasklist").add(data).addOnSuccessListener { document ->
                Log.d("Success", "Document referenceid = ${document.id}")
            }.addOnFailureListener { e ->
                Log.d("Erorr", e.toString())
            }


        })

    }
}
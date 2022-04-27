package com.adl.authenticationfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreActivity : AppCompatActivity() {

    lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firestore)

        db = FirebaseFirestore.getInstance()
        saveData()
        readData()
    }

    fun saveData() {
        val data = hashMapOf(
            "alamat" to "pakuan",
            "phone" to "993393939393",
            "istri" to "chelsea"
        )
        val data2 = hashMapOf(
            "alamat" to "pakuan",
            "phone" to "088828328382",
            "hobi" to "mancing"

        )

        db.collection("manusia").document("Tono").set(data).addOnSuccessListener { document ->
            Log.d("Success", "Document referenceid = ${document}")
        }.addOnFailureListener { e ->
            Log.d("Erorr", e.toString())
        }

        db.collection("manusia").document("Tari").set(data2).addOnSuccessListener { document ->
            Log.d("Success", "Document referenceid = ${document}")
        }.addOnFailureListener { failure ->
            Log.d("Erorr", failure.toString())
        }
    }



    fun readData() {

        db.collection("manusia").document("Tono").collection("Aktifitas").whereEqualTo("hobi","mancing").addSnapshotListener { value, error ->


            if (value != null) {
                for (data in value){
                    Log.d("Success", "Document data = ${data.data}")
                }

            }
        }
    }
}
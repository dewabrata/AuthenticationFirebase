package com.adl.authenticationfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.adl.authenticationfirebase.model.Message
import com.adl.ujiancrud.adapter.ChatAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var database:DatabaseReference
    lateinit var lstDataChat : ArrayList<Message>
    lateinit var  chatAdapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = FirebaseDatabase.getInstance().getReference("CHAT")
        lstDataChat = ArrayList<Message>()

        chatAdapter = ChatAdapter(lstDataChat)

        lstChat.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = chatAdapter
        }

        btnSend.setOnClickListener({
            sendData()
        })


        database?.child("message")?.addValueEventListener( object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                lstDataChat.clear()
                for(data in snapshot.children){
                    lstDataChat.add( Message((data.key)!!.toLong() ,data.value.toString()))
                    chatAdapter.notifyDataSetChanged()

                }

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }


    //CHAT -> message(0)
          //    message(1) --> timestring
       //                   -->chattingan

    fun sendData(){

        database?.child("message")?.child(System.currentTimeMillis().toString())?.
                setValue(txtChat.text.toString()).addOnCompleteListener {
                    it -> if(it.isSuccessful){
            Toast.makeText(applicationContext,"Success add data",
                Toast.LENGTH_LONG).show()
        }
        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext,exception.localizedMessage,
                Toast.LENGTH_LONG).show()
        }
    }
}
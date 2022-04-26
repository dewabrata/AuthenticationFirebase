package com.adl.authenticationfirebase.model

class Message {
    var timeStamp: Long = System.currentTimeMillis()
    var pesan: String? = null

    constructor()
    constructor(time:Long,pesan:String){
        this.pesan = pesan
        this.timeStamp = time
    }

}
package com.adl.authenticationfirebase.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
 data class AktifitasModel( @field:SerializedName("id")
                            val id: String? = null,@field:SerializedName("task")
                            val task: String? = null, @field:SerializedName("jam")
val jam: String? = null,) :Parcelable

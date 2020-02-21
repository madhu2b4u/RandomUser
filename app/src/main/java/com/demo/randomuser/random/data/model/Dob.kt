package com.demo.randomuser.random.data.model


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Dob(
    @Expose
    @SerializedName("age")
    var age: Int,
    @Expose
    @SerializedName("date")
    var date: String
) : Parcelable
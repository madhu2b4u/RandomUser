package com.demo.randomuser.random.data.model


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Coordinates(
    @Expose
    @SerializedName("latitude")
    var latitude: String,
    @Expose
    @SerializedName("longitude")
    var longitude: String
) : Parcelable
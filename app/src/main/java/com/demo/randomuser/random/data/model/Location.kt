package com.demo.randomuser.random.data.model


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Location(
    @Expose
    @SerializedName("city")
    var city: String,
    @Expose
    @SerializedName("coordinates")
    var coordinates: Coordinates,
    @Expose
    @SerializedName("country")
    var country: String,
    @Expose
    @SerializedName("state")
    var state: String,
    @Expose
    @SerializedName("street")
    var street: Street
) : Parcelable
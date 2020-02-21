package com.demo.randomuser.random.data.model


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Info(
    @Expose
    @SerializedName("page")
    var page: Int,
    @Expose
    @SerializedName("users")
    var results: Int,
    @Expose
    @SerializedName("seed")
    var seed: String,
    @Expose
    @SerializedName("version")
    var version: String
) : Parcelable
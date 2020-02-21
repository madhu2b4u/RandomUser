package com.demo.randomuser.random.data.model


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ResultModel(
    @Expose
    @SerializedName("info")
    var info: Info,
    @Expose
    @SerializedName("results")
    var users: List<Users>
) : Parcelable
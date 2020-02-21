package com.demo.randomuser.random.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Name(
    @Expose
    @SerializedName("first")
    var first: String,
    @Expose
    @SerializedName("last")
    var last: String,
    @Expose
    @SerializedName("title")
    var title: String
) : Parcelable
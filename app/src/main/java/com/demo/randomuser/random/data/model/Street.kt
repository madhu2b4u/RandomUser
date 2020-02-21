package com.demo.randomuser.random.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Street(
    @Expose
    @SerializedName("name")
    var name: String,
    @Expose
    @SerializedName("number")
    var number: Int
) : Parcelable
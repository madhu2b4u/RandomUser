package com.demo.randomuser.random.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Picture(
    @Expose
    @SerializedName("large")
    var large: String,
    @Expose
    @SerializedName("medium")
    var medium: String,
    @Expose
    @SerializedName("thumbnail")
    var thumbnail: String
) : Parcelable
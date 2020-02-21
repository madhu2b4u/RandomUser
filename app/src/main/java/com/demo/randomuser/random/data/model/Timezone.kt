package com.demo.randomuser.random.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Timezone(
    @Expose
    @SerializedName("description")
    var description: String,
    @Expose
    @SerializedName("offset")
    var offset: String
) : Parcelable
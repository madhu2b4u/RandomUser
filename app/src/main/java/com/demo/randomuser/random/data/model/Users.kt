package com.demo.randomuser.random.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Users(
    @Expose
    @SerializedName("cell")
    var cell: String,
    @Expose
    @SerializedName("dob")
    var dob: Dob,
    @Expose
    @SerializedName("email")
    var email: String,
    @Expose
    @SerializedName("gender")
    var gender: String,
    @Expose
    @SerializedName("location")
    var location: Location,
    @Expose
    @SerializedName("name")
    var name: Name,
    @Expose
    @SerializedName("nat")
    var nat: String,
    @Expose
    @SerializedName("phone")
    var phone: String,
    @Expose
    @SerializedName("picture")
    var picture: Picture
) : Parcelable
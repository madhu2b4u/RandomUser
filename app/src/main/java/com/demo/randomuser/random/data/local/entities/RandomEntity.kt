package com.demo.randomuser.random.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RandomEntity(

    @PrimaryKey val id: String,
    val response: String
)

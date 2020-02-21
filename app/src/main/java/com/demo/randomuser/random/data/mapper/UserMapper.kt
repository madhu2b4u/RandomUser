package com.demo.randomuser.random.data.mapper

import com.demo.randomuser.common.Constants
import com.demo.randomuser.common.Mapper
import com.demo.randomuser.random.data.local.entities.RandomEntity
import com.demo.randomuser.random.data.model.Users
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class UserMapper @Inject constructor(val gson: Gson) : Mapper<RandomEntity, List<Users>> {
    override fun from(e: List<Users>) = RandomEntity(Constants.ID, gson.toJson(e))
    override fun to(t: RandomEntity): List<Users> {
        return gson.fromJson(
            t.response, TypeToken.getParameterized(ArrayList::class.java, Users::class.java).type
        )
    }
}
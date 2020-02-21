package com.demo.randomuser.random.data.remote.services

import com.demo.randomuser.random.data.model.ResultModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomListService {
    @GET("?exc=id,login,nat&results=200")
    fun getRandomUsersAsync(@Query("page") page: Int): Deferred<Response<ResultModel>>

}
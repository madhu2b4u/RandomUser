package com.demo.randomuser.random.data.remote.source

import com.demo.randomuser.random.data.model.Users


interface RandomListRemoteDataSource {
    suspend fun getRandomUsers(page: Int): List<Users>

}
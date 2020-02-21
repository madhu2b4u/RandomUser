package com.demo.randomuser.random.data.local.source

import com.demo.randomuser.random.data.model.Users

interface UsersLocalDataSource {
    suspend fun getUsers(): List<Users>?
    suspend fun saveUsers(articles: List<Users>)
}
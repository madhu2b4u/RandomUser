package com.demo.randomuser.random.data.repository

import androidx.lifecycle.LiveData
import com.demo.randomuser.common.Result
import com.demo.randomuser.random.data.model.Users


interface RandomListRepository {
    suspend fun getRandomUsers(
        mustFetchFromNetwork: Boolean,
        page: Int): LiveData<Result<List<Users>>>
}
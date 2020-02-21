package com.demo.randomuser.random.data.repository

import androidx.lifecycle.LiveData
import com.demo.randomuser.common.Result
import com.demo.randomuser.random.data.model.Users


interface RandomListRepository {
    suspend fun getRandomUsers(
        page: Int,
        mustFetchFromNetwork: Boolean
    ): LiveData<Result<List<Users>>>
}
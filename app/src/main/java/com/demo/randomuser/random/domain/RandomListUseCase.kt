package com.demo.randomuser.random.domain

import androidx.lifecycle.LiveData
import com.demo.randomuser.common.Result
import com.demo.randomuser.random.data.model.Users

interface RandomListUseCase {

    suspend fun getRandomUsers(
        mustFetchFromNetwork: Boolean = false,
        page: Int
    ): LiveData<Result<List<Users>>>

}
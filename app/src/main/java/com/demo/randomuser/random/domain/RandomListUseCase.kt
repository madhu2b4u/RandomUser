package com.demo.randomuser.random.domain

import androidx.lifecycle.LiveData
import com.demo.randomuser.common.Result
import com.demo.randomuser.random.data.model.Users

interface RandomListUseCase {

    suspend fun getRandomUsers(
        page: Int,
        mustFetchFromNetwork: Boolean = false
    ): LiveData<Result<List<Users>>>

}
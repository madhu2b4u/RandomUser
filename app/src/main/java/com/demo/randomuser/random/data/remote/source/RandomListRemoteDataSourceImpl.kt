package com.demo.randomuser.random.data.remote.source

import com.demo.randomuser.di.qualifiers.IO
import com.demo.randomuser.random.data.remote.services.RandomListService
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class RandomListRemoteDataSourceImpl @Inject constructor(
    private val service: RandomListService,
    @IO private val context: CoroutineContext
) : RandomListRemoteDataSource {
    override suspend fun getRandomUsers(page: Int) = withContext(context) {
        val response = service.getRandomUsersAsync(page).await()

        if (response.isSuccessful)
            response.body()?.users ?: throw Exception("no users")
        else
            throw Exception("invalid request with code ${response.code()}")


    }

}

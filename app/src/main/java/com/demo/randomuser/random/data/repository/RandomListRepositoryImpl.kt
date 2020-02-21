package com.demo.randomuser.random.data.repository


import androidx.lifecycle.liveData
import com.demo.randomuser.common.Result
import com.demo.randomuser.random.data.local.source.UsersLocalDataSource
import com.demo.randomuser.random.data.model.Users
import com.demo.randomuser.random.data.remote.source.RandomListRemoteDataSource
import javax.inject.Inject


class RandomListRepositoryImpl @Inject constructor(
    private val remoteDataSource: RandomListRemoteDataSource,
    private val localDataSource: UsersLocalDataSource

) : RandomListRepository {
    override suspend fun getRandomUsers(
        mustFetchFromNetwork: Boolean,
        page: Int) = liveData {
        emit(Result.loading())
        try {

            var articles: List<Users>? = null
            if (!mustFetchFromNetwork)
                articles = localDataSource.getUsers()

            if (articles == null) {
                articles = remoteDataSource.getRandomUsers(page)
                localDataSource.saveUsers(articles)
            }
            emit(Result.success(articles))

        } catch (exception: Exception) {
            emit(Result.error(exception.message ?: ""))
        }
    }

}
package com.demo.randomuser.random.data.local.source

import com.demo.randomuser.common.Constants
import com.demo.randomuser.di.qualifiers.IO
import com.demo.randomuser.random.data.local.database.UsersDao
import com.demo.randomuser.random.data.mapper.UserMapper
import com.demo.randomuser.random.data.model.Users
import kotlinx.coroutines.withContext

import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class UsersLocalDataSourceImpl @Inject constructor(
    private val usersDao: UsersDao,
    private val userMapper: UserMapper,
    @IO private val context: CoroutineContext
) : UsersLocalDataSource {

    override suspend fun getUsers() = withContext(context) {
        val randomEntity = usersDao.getUsers(Constants.ID)
        if (randomEntity != null)
            userMapper.to(randomEntity)
        else
            null
    }

    override suspend fun saveUsers(list: List<Users>) = withContext(context) {
        val randomEntity = userMapper.from(list)
        usersDao.insertUser(randomEntity)

    }

}
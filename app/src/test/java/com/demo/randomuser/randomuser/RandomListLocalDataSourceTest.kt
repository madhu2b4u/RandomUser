package com.demo.randomuser.randomuser

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.demo.randomuser.MainCoroutineRule
import com.demo.randomuser.random.data.local.database.UsersDao
import com.demo.randomuser.random.data.local.source.UsersLocalDataSource
import com.demo.randomuser.random.data.local.source.UsersLocalDataSourceImpl
import com.demo.randomuser.random.data.mapper.UserMapper
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class RandomListLocalDataSourceTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    lateinit var localDataSource: UsersLocalDataSource


    @Mock
    lateinit var usersDao: UsersDao


    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)

        localDataSource = UsersLocalDataSourceImpl(usersDao, UserMapper(Gson()) , mainCoroutineRule.coroutineContext)
    }


    @Test
    fun testInvalidEntityUsers()=mainCoroutineRule.runBlockingTest{
        Mockito.`when`(usersDao.getUsers("123Random")).thenReturn(null)

        val result = localDataSource.getUsers()

        assert(result == null)
    }





}
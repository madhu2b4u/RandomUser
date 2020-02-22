package com.demo.randomuser.randomuser

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.demo.randomuser.MainCoroutineRule
import com.demo.randomuser.TestUtils
import com.demo.randomuser.random.data.model.ResultModel
import com.demo.randomuser.random.data.remote.services.RandomListService
import com.demo.randomuser.random.data.remote.source.RandomListRemoteDataSource
import com.demo.randomuser.random.data.remote.source.RandomListRemoteDataSourceImpl
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class RamdomListRemoteDataSourceTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    private lateinit var remoteDataSource: RandomListRemoteDataSource


    private lateinit var service: RandomListService

    private val response = TestUtils().fakeResponse

    @Before
    fun init() {

        service = mock {
            onBlocking { getRandomUsersAsync(1) } doReturn GlobalScope.async {
                Response.success(response)
            }
        }

        remoteDataSource =
            RandomListRemoteDataSourceImpl(service, mainCoroutineRule.coroutineContext)


    }

    @Test
    fun testGetUsers() = runBlocking {

        service = mock {
            onBlocking { getRandomUsersAsync(1) } doReturn GlobalScope.async {
                Response.success(response)
            }
        }

        remoteDataSource =
            RandomListRemoteDataSourceImpl(service, mainCoroutineRule.coroutineContext)

        // Will be launched in the mainThreadSurrogate dispatcher
        val result = remoteDataSource.getRandomUsers(1)

        assert(result == response.users)


    }

    @Test(expected = Exception::class)
    fun testThrowUserException() = runBlocking {

        service = mock {
            onBlocking { getRandomUsersAsync(1) } doReturn GlobalScope.async {
                Response.error<ResultModel>(404, null)
            }
        }

        remoteDataSource =
            RandomListRemoteDataSourceImpl(service, mainCoroutineRule.coroutineContext)

        assert(remoteDataSource.getRandomUsers(1) == response.users)

    }


}
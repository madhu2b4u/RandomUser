package com.demo.randomuser.randomuser

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.demo.randomuser.LiveDataTestUtil
import com.demo.randomuser.MainCoroutineRule
import com.demo.randomuser.TestUtils
import com.demo.randomuser.common.Status
import com.demo.randomuser.random.data.local.source.UsersLocalDataSource
import com.demo.randomuser.random.data.remote.source.RandomListRemoteDataSource
import com.demo.randomuser.random.data.repository.RandomListRepository
import com.demo.randomuser.random.data.repository.RandomListRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class RandomListRepositoryTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    lateinit var repository: RandomListRepository

    @Mock
    lateinit var localDataSource: UsersLocalDataSource

    @Mock
    lateinit var remoteDataSource: RandomListRemoteDataSource

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)

        repository = RandomListRepositoryImpl(remoteDataSource,localDataSource)
    }

    @Test
    fun testGetUsersFromAPI() = mainCoroutineRule.runBlockingTest {

        Mockito.`when`(localDataSource.getUsers()).thenReturn(null)
        Mockito.`when`(remoteDataSource.getRandomUsers(1)).thenReturn(TestUtils().fakeUsers)


        val result = repository.getRandomUsers(1, false)

        assert(LiveDataTestUtil.getValue(result).status == Status.LOADING)
        assert(LiveDataTestUtil.getValue(result).status == Status.SUCCESS)
        assert(LiveDataTestUtil.getValue(result).data == TestUtils().fakeUsers)


    }

    @Test
    fun testGetUsersFromDb() = mainCoroutineRule.runBlockingTest {

        Mockito.`when`(localDataSource.getUsers()).thenReturn(TestUtils().fakeUsers)


        val result = repository.getRandomUsers(1, false)


        assert(LiveDataTestUtil.getValue(result).status == Status.LOADING)

        kotlinx.coroutines.delay(2000)
        assert(LiveDataTestUtil.getValue(result).status == Status.SUCCESS)
        assert(LiveDataTestUtil.getValue(result).data == TestUtils().fakeUsers)


    }

    @Test(expected = Exception::class)
    fun testGetUsersThrowException() = mainCoroutineRule.runBlockingTest {

        Mockito.`when`(localDataSource.getUsers()).thenReturn(null)
        Mockito.doThrow(Exception("error")).`when`(remoteDataSource.getRandomUsers(1))

        repository.getRandomUsers(1, false)


    }


}

package com.demo.randomuser.randomuser

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.demo.randomuser.LiveDataTestUtil
import com.demo.randomuser.MainCoroutineRule
import com.demo.randomuser.TestUtils
import com.demo.randomuser.common.Status
import com.demo.randomuser.random.data.model.Users
import com.demo.randomuser.random.data.repository.RandomListRepository
import com.demo.randomuser.random.domain.RandomListUseCase
import com.demo.randomuser.common.Result
import com.demo.randomuser.random.domain.RandomListUseCaseImpl
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RandomListUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    lateinit var randomListUseCase: RandomListUseCase

    lateinit var randomListRepository: RandomListRepository

    @Test
    fun testLoadingData()=mainCoroutineRule.runBlockingTest{
            randomListRepository = mock {
                onBlocking { getRandomUsers(1,false) } doReturn object : LiveData<Result<List<Users>>>() {
                    init {
                        value = Result.loading()
                    }
                }
            }
            randomListUseCase = RandomListUseCaseImpl(randomListRepository)

            val result = randomListUseCase.getRandomUsers(1)

            result.observeForever {  }

            assert(LiveDataTestUtil.getValue(result).status == Status.LOADING)

        }


    @Test
    fun testSuccessData()=mainCoroutineRule.runBlockingTest{
        randomListRepository = mock {
            onBlocking { getRandomUsers(1,false) } doReturn object : LiveData<Result<List<Users>>>() {
                init {
                    value = Result.success(TestUtils().fakeUsers)
                }
            }
        }
        randomListUseCase = RandomListUseCaseImpl(randomListRepository)

        val result = randomListUseCase.getRandomUsers(1)

        result.observeForever {  }

        assert(LiveDataTestUtil.getValue(result).status == Status.SUCCESS && LiveDataTestUtil.getValue(result).data == TestUtils().fakeUsers)

    }

    @Test
    fun testErrorData()=mainCoroutineRule.runBlockingTest{
        randomListRepository = mock {
            onBlocking { getRandomUsers(1,false) } doReturn object : LiveData<Result<List<Users>>>() {
                init {
                    value = Result.error("error")
                }
            }
        }
        randomListUseCase = RandomListUseCaseImpl(randomListRepository)

        val result = randomListUseCase.getRandomUsers(1)

        result.observeForever {  }

        assert(LiveDataTestUtil.getValue(result).status == Status.ERROR && LiveDataTestUtil.getValue(result).message == "error")

    }


}
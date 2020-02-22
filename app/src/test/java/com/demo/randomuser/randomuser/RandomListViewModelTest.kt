package com.demo.randomuser.randomuser

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.demo.randomuser.LiveDataTestUtil
import com.demo.randomuser.MainCoroutineRule
import com.demo.randomuser.TestUtils
import com.demo.randomuser.common.Result
import com.demo.randomuser.common.Status
import com.demo.randomuser.random.data.model.Users
import com.demo.randomuser.random.domain.RandomListUseCase
import com.demo.randomuser.random.presentation.viewmodel.RandomListViewModel
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class RandomListViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    lateinit var randomListViewModel: RandomListViewModel

    lateinit var randomListUseCase: RandomListUseCase

    @Before
    fun init(){
       //empty
    }


    @Test
    fun testLoadingData() = mainCoroutineRule.runBlockingTest {

        randomListUseCase = mock {
            onBlocking { getRandomUsers(1, false) } doReturn object : LiveData<Result<List<Users>>>() {
                init {
                    value = Result.loading()
                }
            }
        }


        randomListViewModel = RandomListViewModel(randomListUseCase)

        val result = randomListViewModel.userResult

        result.observeForever {}


        kotlinx.coroutines.delay(2000)
        assert(LiveDataTestUtil.getValue(randomListViewModel.userResult).status == Status.LOADING)

    }


    @Test
    fun testSuccessData() = mainCoroutineRule.runBlockingTest {

        randomListUseCase = mock {
            onBlocking { getRandomUsers(1, false) } doReturn object : LiveData<Result<List<Users>>>() {
                init {
                    value = Result.success(TestUtils().fakeUsers)
                }
            }
        }

        randomListViewModel = RandomListViewModel(randomListUseCase)

        val result = randomListViewModel.userResult

        result.observeForever {}

        kotlinx.coroutines.delay(2000)

        assert(LiveDataTestUtil.getValue(result).status == Status.SUCCESS &&
                LiveDataTestUtil.getValue(result).data == TestUtils().fakeUsers)

    }


    @Test
    fun testErrorData() = mainCoroutineRule.runBlockingTest {

        randomListUseCase = mock {
            onBlocking { getRandomUsers(1, false) } doReturn object : LiveData<Result<List<Users>>>() {
                init {
                    value = Result.error("error")
                }
            }
        }

        randomListViewModel = RandomListViewModel(randomListUseCase)

        val result = randomListViewModel.userResult

        result.observeForever {}

        kotlinx.coroutines.delay(2000)


        assert(LiveDataTestUtil.getValue(result).status == Status.ERROR &&
                LiveDataTestUtil.getValue(result).message == "error")

    }


    @Test
    fun testFetchDataData() = mainCoroutineRule.runBlockingTest {

        randomListUseCase = mock {
            onBlocking { getRandomUsers(1, true) } doReturn object : LiveData<Result<List<Users>>>() {
                init {
                    value = Result.error("error")
                }
            }

            onBlocking { getRandomUsers(1, false) } doReturn object : LiveData<Result<List<Users>>>() {
                init {
                    value = Result.success(TestUtils().fakeUsers)
                }
            }
        }

        randomListViewModel = RandomListViewModel(randomListUseCase)

        val result = randomListViewModel.userResult

        result.observeForever {}


        assert(LiveDataTestUtil.getValue(result).status == Status.SUCCESS &&
                LiveDataTestUtil.getValue(result).data == TestUtils().fakeUsers)

        kotlinx.coroutines.delay(2000)

        randomListViewModel.loadUsers(1, true)


        kotlinx.coroutines.delay(2000)


        assert(LiveDataTestUtil.getValue(result).status == Status.ERROR &&
                LiveDataTestUtil.getValue(result).message == "error")

    }



}
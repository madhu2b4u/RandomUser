package com.demo.randomuser.random.presentation.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.randomuser.common.Result
import com.demo.randomuser.random.data.model.Users
import com.demo.randomuser.random.domain.RandomListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject


class RandomListViewModel @Inject constructor(
    private val mRandomListUseCase: RandomListUseCase
) : ViewModel() {

    val userResult = MediatorLiveData<Result<List<Users>>>()

    init {
        loadUsers(false,1)
    }

    fun loadUsers(mustFetchFromNetwork: Boolean, page: Int) {
        viewModelScope.launch {
            userResult.addSource(mRandomListUseCase.getRandomUsers(page, mustFetchFromNetwork)) {
                userResult.value = it
            }
        }
    }

}
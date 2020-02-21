package com.demo.randomuser.random.domain

import com.demo.randomuser.random.data.repository.RandomListRepository
import javax.inject.Inject

class RandomListUseCaseImpl @Inject constructor(private val mRandomListRepository: RandomListRepository) :
    RandomListUseCase {
    override suspend fun getRandomUsers(
        mustFetchFromNetwork: Boolean,
        page: Int
    ) = mRandomListRepository.getRandomUsers(mustFetchFromNetwork, page)
}

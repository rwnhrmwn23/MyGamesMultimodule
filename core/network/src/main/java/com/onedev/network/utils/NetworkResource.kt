package com.onedev.network.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

abstract class NetworkResource<ResultType> {

    private var result: Flow<StateEvent<ResultType>> = flow {
        emit(StateEvent.Loading())
        when (val apiResponse = createCall().first()) {
            is ApiResponse.Success -> emit(StateEvent.Success(apiResponse.data))
            is ApiResponse.Error -> emit(StateEvent.Error(apiResponse.errorMessage))
            is ApiResponse.Empty -> { emit(StateEvent.Error("Empty Data")) }
        }
    }

    protected abstract suspend fun createCall(): Flow<ApiResponse<ResultType>>

    fun asFlow(): Flow<StateEvent<ResultType>> = result
}
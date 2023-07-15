package com.hwaryun.domain

import com.hwaryun.common.UiResult
import com.hwaryun.common.di.DispatcherProvider
import com.hwaryun.common.domain.FlowUseCase
import com.hwaryun.common.ext.suspendSubscribe
import com.hwaryun.data.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CatchPokemonUseCase @Inject constructor(
    private val repository: PokemonRepository,
    dispatcherProvider: DispatcherProvider
) : FlowUseCase<Pair<String, Boolean>, UiResult<Unit>>(dispatcherProvider.io) {

    override fun buildFlowUseCase(param: Pair<String, Boolean>?): Flow<UiResult<Unit>> = flow {
        emit(UiResult.Loading())
        param?.let { isCatched ->
            repository.catchOrReleasePokemon(isCatched.first, isCatched.second).collect { result ->
                result.suspendSubscribe(
                    doOnSuccess = {
                        emit(UiResult.Success(Unit))
                    },
                    doOnError = {
                        emit(UiResult.Failure(it.throwable))
                    }
                )
            }
        } ?: emit(UiResult.Failure(IllegalArgumentException()))
    }
}
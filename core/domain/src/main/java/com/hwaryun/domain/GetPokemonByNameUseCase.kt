package com.hwaryun.domain

import com.hwaryun.common.UiResult
import com.hwaryun.common.di.DispatcherProvider
import com.hwaryun.common.domain.FlowUseCase
import com.hwaryun.common.ext.suspendSubscribe
import com.hwaryun.data.repository.PokemonRepository
import com.hwaryun.domain.model.PokemonInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPokemonByNameUseCase @Inject constructor(
    private val repository: PokemonRepository,
    dispatcherProvider: DispatcherProvider
) : FlowUseCase<String, UiResult<PokemonInfo>>(dispatcherProvider.io) {

    override fun buildFlowUseCase(param: String?): Flow<UiResult<PokemonInfo>> = flow {
        emit(UiResult.Loading())
        param?.let { name ->
            repository.getPokemonByName(name).collect { result ->
                result.suspendSubscribe(
                    doOnSuccess = {
                        result.value?.let { pokemon ->
                            emit(UiResult.Success(pokemon.toPokemonInfo()))
                        }
                    },
                    doOnError = {
                        emit(UiResult.Failure(it.throwable))
                    }
                )
            }
        } ?: emit(UiResult.Failure(IllegalArgumentException()))
    }
}
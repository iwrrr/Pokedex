package com.hwaryun.domain

import com.hwaryun.common.UiResult
import com.hwaryun.common.di.DispatcherProvider
import com.hwaryun.common.domain.FlowUseCase
import com.hwaryun.common.ext.suspendSubscribe
import com.hwaryun.data.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val repository: PokemonRepository,
    dispatcherProvider: DispatcherProvider
) : FlowUseCase<Int, UiResult<List<Pokemon>>>(dispatcherProvider.io) {

    override fun buildFlowUseCase(param: Int?): Flow<UiResult<List<Pokemon>>> = flow {
        emit(UiResult.Loading())
        param?.let { page ->
            repository.getPokemonList(page).collect { result ->
                result.suspendSubscribe(
                    doOnSuccess = {
                        result.value?.let { pokemons ->
                            emit(UiResult.Success(pokemons.map { it.toPokemon(page) }))
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
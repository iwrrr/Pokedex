package com.hwaryun.domain

import com.hwaryun.common.UiResult
import com.hwaryun.common.di.DispatcherProvider
import com.hwaryun.common.domain.FlowUseCase
import com.hwaryun.common.ext.suspendSubscribe
import com.hwaryun.data.repository.PokemonRepository
import com.hwaryun.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCatchedPokemonsUseCase @Inject constructor(
    private val repository: PokemonRepository,
    dispatcherProvider: DispatcherProvider
) : FlowUseCase<Nothing, UiResult<List<Pokemon>>>(dispatcherProvider.io) {

    override fun buildFlowUseCase(param: Nothing?): Flow<UiResult<List<Pokemon>>> = flow {
        emit(UiResult.Loading())
        repository.getCatchedPokemons().collect { result ->
            result.suspendSubscribe(
                doOnSuccess = {
                    result.value?.let { pokemons ->
                        if (pokemons.isNotEmpty()) {
                            emit(UiResult.Success(pokemons.map { it.toPokemon(it.id) }))
                        } else {
                            emit(UiResult.Empty())
                        }
                    }
                },
                doOnError = {
                    emit(UiResult.Failure(it.throwable))
                }
            )
        }
    }
}
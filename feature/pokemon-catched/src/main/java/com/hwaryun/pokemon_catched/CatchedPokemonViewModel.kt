package com.hwaryun.pokemon_catched

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hwaryun.common.ext.subscribe
import com.hwaryun.data.repository.PokemonRepository
import com.hwaryun.domain.GetCatchedPokemonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatchedPokemonViewModel @Inject constructor(
    private val getCatchedPokemonsUseCase: GetCatchedPokemonsUseCase,
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CatchedPokemonState())
    val state = _state.asStateFlow()

    fun init() {
        loadCatchedPokemons()
    }

    private fun loadCatchedPokemons() {
        viewModelScope.launch {
            getCatchedPokemonsUseCase.invoke().collect { result ->
                result.subscribe(
                    doOnLoading = {
                        _state.update { state ->
                            state.copy(
                                isLoading = true
                            )
                        }
                    },
                    doOnSuccess = {
                        result.value?.let { pokemons ->
                            _state.update { state ->
                                state.copy(
                                    pokemonList = pokemons,
                                    isLoading = false,
                                )
                            }
                        }
                    },
                    doOnEmpty = {
                        _state.update { state ->
                            state.copy(
                                pokemonList = emptyList(),
                                isLoading = false,
                            )
                        }
                    },
                    doOnError = {
                        _state.update { state ->
                            state.copy(
                                isLoading = false,
                                error = it.throwable?.message ?: "Unexpected error occurred"
                            )
                        }
                    }
                )
            }
        }
    }

    fun releasePokemon(name: String) {
        viewModelScope.launch {
            pokemonRepository.catchOrReleasePokemon(name, false).collect()
            loadCatchedPokemons()
        }
    }
}
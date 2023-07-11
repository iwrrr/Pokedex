package com.hwaryun.pokemon_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hwaryun.common.ConnectivityException
import com.hwaryun.common.ext.subscribe
import com.hwaryun.domain.GetPokemonByNameUseCase
import com.hwaryun.pokemon_detail.navigation.POKEMON_NAME
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getPokemonByNameUseCase: GetPokemonByNameUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val pokemonName = savedStateHandle.get<String>(POKEMON_NAME).orEmpty()

    private val _state: MutableStateFlow<PokemonDetailState> =
        MutableStateFlow(PokemonDetailState())
    val state = _state.asStateFlow()

    init {
        getPokemonByName(pokemonName)
    }

    private fun getPokemonByName(name: String) {
        viewModelScope.launch {
            getPokemonByNameUseCase.invoke(name).collect { result ->
                result.subscribe(
                    doOnLoading = {
                        _state.update { state ->
                            state.copy(
                                isLoading = true
                            )
                        }
                    },
                    doOnSuccess = {
                        _state.update { state ->
                            state.copy(
                                pokemonInfo = it.value,
                                isLoading = false,
                            )
                        }
                    },
                    doOnError = {
                        when (it.throwable) {
                            is ConnectivityException -> {
                                _state.update { state ->
                                    state.copy(
                                        isLoading = false,
                                        error = ""
                                    )
                                }
                            }

                            else -> {
                                _state.update { state ->
                                    state.copy(
                                        isLoading = false,
                                        error = it.throwable?.message ?: "Unexpected error occurred"
                                    )
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}
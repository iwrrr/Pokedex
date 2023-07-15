package com.hwaryun.pokemon_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hwaryun.common.ConnectivityException
import com.hwaryun.common.ext.subscribe
import com.hwaryun.data.repository.PokemonRepository
import com.hwaryun.domain.GetPokemonByNameUseCase
import com.hwaryun.domain.GetPokemonMoveUseCase
import com.hwaryun.pokemon_detail.navigation.POKEMON_NAME
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getPokemonByNameUseCase: GetPokemonByNameUseCase,
    private val getPokemonMoveUseCase: GetPokemonMoveUseCase,
    private val pokemonRepository: PokemonRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val pokemonName = savedStateHandle.get<String>(POKEMON_NAME).orEmpty()

    private val _state = MutableStateFlow(PokemonDetailState())
    val state = _state.asStateFlow()

    private val _catchState = MutableStateFlow(CatchPokemonState())
    val catchState = _catchState.asStateFlow()

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
                        _catchState.update { state ->
                            state.copy(
                                isCatched = it.value?.isCatched == true,
                                isLoading = false
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

    fun catchOrReleasePokemon(name: String, isCatched: Boolean) {
        viewModelScope.launch {
            if (!isCatched) {
                pokemonRepository.catchOrReleasePokemon(name, false).collect()
                _catchState.update { state ->
                    state.copy(
                        showDialog = true,
                        isCatched = false
                    )
                }
                return@launch
            }

            _catchState.update { state ->
                state.copy(
                    isLoading = true
                )
            }
            delay(1000L)

            val chance = (0..100).random()
            if (chance >= 50) pokemonRepository.catchOrReleasePokemon(name, true).collect()

            _catchState.update { state ->
                state.copy(
                    showDialog = true,
                    isCatched = chance >= 50,
                    isLoading = false,
                    failed = chance <= 50
                )
            }
        }
    }

    fun dismissDialog() {
        _catchState.update { state ->
            state.copy(showDialog = false)
        }
    }
}
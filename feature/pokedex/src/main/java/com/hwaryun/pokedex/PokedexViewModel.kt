package com.hwaryun.pokedex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hwaryun.common.ext.subscribe
import com.hwaryun.domain.GetPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokedexViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<PokedexState> = MutableStateFlow(PokedexState())
    val state = _state.asStateFlow()

    init {
        loadPokemonListByPage(page = 0)
    }

    fun loadPokemonListByPage(
        page: Int,
        isLastPageLoaded: Boolean = false
    ) {
        if (isLastPageLoaded) return
        viewModelScope.launch {
            getPokemonListUseCase.invoke(page).collect { result ->
                result.subscribe(
                    doOnLoading = {
                        _state.update { state ->
                            state.copy(
                                isLoading = true
                            )
                        }
                    },
                    doOnSuccess = {
                        if (result.value.isNullOrEmpty()) {
                            _state.update { state ->
                                state.copy(
                                    isLoading = false,
                                    isLastPageLoaded = true,
                                )
                            }
                        } else {
                            _state.update { state ->
                                state.copy(
                                    pokemonList = state.pokemonList + result.value.orEmpty(),
                                    isLoading = false,
                                    isLastPageLoaded = false,
                                )
                            }
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
}
package com.hwaryun.pokemon_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hwaryun.common.UiResult
import com.hwaryun.domain.GetPokemonMoveUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class PokemonMoveViewModel @Inject constructor(
    private val getPokemonMoveUseCase: GetPokemonMoveUseCase,
) : ViewModel() {

    fun load(name: String) = getPokemonMoveUseCase.invoke(name)
        .onStart { delay(500) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(500),
            initialValue = UiResult.Loading()
        )
}
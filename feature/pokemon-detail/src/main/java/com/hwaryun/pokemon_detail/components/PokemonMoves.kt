package com.hwaryun.pokemon_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hwaryun.domain.model.PokemonInfo
import com.hwaryun.pokemon_detail.PokemonMoveViewModel

@Composable
internal fun PokemonMoves(
    pokemonInfo: PokemonInfo,
    modifier: Modifier = Modifier,
    viewModel: PokemonMoveViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp)
    ) {
        MoveHeaders(modifier = Modifier.padding(horizontal = 20.dp))
        pokemonInfo.moves.forEach { moveItems ->
            key(moveItems.name) {
                val moveResult by remember { viewModel.load(moveItems.name) }.collectAsStateWithLifecycle()
                PokemonMoveItem(result = moveResult)
            }
        }
    }
}
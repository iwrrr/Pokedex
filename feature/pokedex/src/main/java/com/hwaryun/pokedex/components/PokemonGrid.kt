package com.hwaryun.pokedex.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hwaryun.designsystem.PokedexTheme
import com.hwaryun.domain.model.Pokemon

@Composable
internal fun PokemonGrid(
    pokemonList: List<Pokemon>,
    isLoading: Boolean,
    onPokemonClicked: (name: String) -> Unit,
    loadMoreItems: () -> Unit = {},
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 24.dp),
    ) {
        items(pokemonList, key = { it.name }) { pokemon ->
            PokemonItem(
                onClick = { onPokemonClicked(pokemon.name) },
                pokemon = pokemon,
                modifier = Modifier.fillMaxWidth()
            )
        }

        if (isLoading) {
            items(6) { index ->
                LaunchedEffect(Unit) {
                    if (index == 0) loadMoreItems()
                }

                PokemonLoadingItem()
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun DefaultPreview() {
    PokedexTheme {
        PokemonGrid(
            pokemonList = emptyList(),
            isLoading = false,
            onPokemonClicked = {}
        ) {}
    }
}
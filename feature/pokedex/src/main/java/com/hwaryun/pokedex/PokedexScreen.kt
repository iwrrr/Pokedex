package com.hwaryun.pokedex

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hwaryun.designsystem.PokedexTheme
import com.hwaryun.pokedex.components.AppBar
import com.hwaryun.pokedex.components.PokemonGrid

@Composable
internal fun PokedexRoute(
    navigateToPokemonDetailsScreen: (String) -> Unit,
    viewModel: PokedexViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    PokedexScreen(
        state = state,
        loadPokemonListByPage = viewModel::loadPokemonListByPage,
        navigateToPokemonDetailsScreen = navigateToPokemonDetailsScreen
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun PokedexScreen(
    state: PokedexState,
    loadPokemonListByPage: (Int, Boolean) -> Unit,
    navigateToPokemonDetailsScreen: (String) -> Unit,
) {
    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            AppBar(title = "Pokedex", subtitle = "What Pokémon are you looking for?")
        }
    ) {

        PokemonGrid(
            pokemonList = state.pokemonList,
            isLoading = !state.isLastPageLoaded,
            onPokemonClicked = navigateToPokemonDetailsScreen
        ) {
            if (state.pokemonList.isEmpty()) return@PokemonGrid

            val nextPage = state.pokemonList.last().page + 1
            loadPokemonListByPage(nextPage, state.isLastPageLoaded)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun DefaultPreview() {
    PokedexTheme {
        PokedexScreen(
            state = PokedexState(),
            loadPokemonListByPage = { _, _ -> },
            navigateToPokemonDetailsScreen = {}
        )
    }
}
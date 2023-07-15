package com.hwaryun.pokemon_catched

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hwaryun.designsystem.PokedexTheme
import com.hwaryun.pokemon_catched.components.AppBar
import com.hwaryun.pokemon_catched.components.PokemonGrid

@Composable
internal fun CatchedPokemonRoute(
    popBackStack: () -> Unit,
    navigateToPokemonDetailsScreen: (String) -> Unit,
    viewModel: CatchedPokemonViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.init()
    }

    CatchedPokemonScreen(
        state = state,
        releasePokemon = viewModel::releasePokemon,
        popBackStack = popBackStack,
        navigateToPokemonDetailsScreen = navigateToPokemonDetailsScreen
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun CatchedPokemonScreen(
    state: CatchedPokemonState,
    releasePokemon: (String) -> Unit,
    popBackStack: () -> Unit,
    navigateToPokemonDetailsScreen: (String) -> Unit,
) {
    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            AppBar(title = "Your Pokémon List", onNavigationClick = popBackStack)
        }
    ) {
        PokemonGrid(
            pokemonList = state.pokemonList,
            releasePokemon = releasePokemon,
            onPokemonClicked = navigateToPokemonDetailsScreen
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun DefaultPreview() {
    PokedexTheme {
        CatchedPokemonScreen(
            state = CatchedPokemonState(),
            releasePokemon = {},
            popBackStack = {},
            navigateToPokemonDetailsScreen = {}
        )
    }
}
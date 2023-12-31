package com.hwaryun.pokedex.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.hwaryun.pokedex.MainAppState
import com.hwaryun.pokemon_catched.navigation.catchedPokemonsScreen
import com.hwaryun.pokemon_catched.navigation.navigateToCatchedPokemons
import com.hwaryun.pokemon_detail.navigation.navigateToPokemonDetails
import com.hwaryun.pokemon_detail.navigation.pokemonDetailsScreen

@ExperimentalAnimationApi
@Composable
fun MainAppNavHost(
    modifier: Modifier = Modifier,
    mainAppState: MainAppState,
    startDestination: String = pokedexRoute,
    onShowSnackbar: suspend (String, String?) -> Boolean
) {
    val navController = mainAppState.navHostController
    AnimatedNavHost(
        navController = navController,
        modifier = modifier,
        startDestination = startDestination
    ) {
        pokedexScreen(
            navigateToCatchedPokemonsScreen = navController::navigateToCatchedPokemons,
            navigateToPokemonDetailsScreen = navController::navigateToPokemonDetails
        )
        catchedPokemonsScreen(
            popBackStack = navController::popBackStack,
            navigateToPokemonDetailsScreen = navController::navigateToPokemonDetails
        )
        pokemonDetailsScreen(
            popBackStack = navController::popBackStack
        )
    }
}
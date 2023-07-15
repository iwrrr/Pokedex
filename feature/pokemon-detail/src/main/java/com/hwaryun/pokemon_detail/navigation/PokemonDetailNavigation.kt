package com.hwaryun.pokemon_detail.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.composable
import com.hwaryun.pokemon_detail.PokemonDetailRoute

const val POKEMON_NAME = "name"
const val pokemonDetailsRoute = "pokemon_details_route/{$POKEMON_NAME}"

fun NavController.navigateToPokemonDetails(name: String, navOptions: NavOptions? = null) {
    this.navigate("pokemon_details_route/$name", navOptions)
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.pokemonDetailsScreen(
    popBackStack: () -> Unit,
    enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = {
        fadeIn(tween(300))
    },
    exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = {
        fadeOut(tween(300))
    },
    popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = {
        fadeIn(tween(300))
    },
    popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = {
        fadeOut(tween(300))
    },
) {
    composable(
        route = pokemonDetailsRoute,
        arguments = listOf(
            navArgument(POKEMON_NAME) { type = NavType.StringType }
        ),
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
    ) {
        PokemonDetailRoute(popBackStack = popBackStack)
    }
}
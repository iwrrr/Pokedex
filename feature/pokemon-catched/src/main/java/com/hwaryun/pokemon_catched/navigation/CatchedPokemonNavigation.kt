package com.hwaryun.pokemon_catched.navigation

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
import com.google.accompanist.navigation.animation.composable
import com.hwaryun.pokemon_catched.CatchedPokemonRoute

const val catchedPokemonsRoute = "catched_pokemons_route"

fun NavController.navigateToCatchedPokemons(navOptions: NavOptions? = null) {
    this.navigate(catchedPokemonsRoute, navOptions)
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.catchedPokemonsScreen(
    popBackStack: () -> Unit,
    navigateToPokemonDetailsScreen: (String) -> Unit,
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
        route = catchedPokemonsRoute,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
    ) {
        CatchedPokemonRoute(
            popBackStack = popBackStack,
            navigateToPokemonDetailsScreen = navigateToPokemonDetailsScreen
        )
    }
}
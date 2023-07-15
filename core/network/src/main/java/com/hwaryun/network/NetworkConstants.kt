package com.hwaryun.network

object NetworkConstants {
    const val baseUrl = "https://pokeapi.co/api/v2/"

    object Pokemon {
        const val pokemon = baseUrl + "pokemon"
        const val pokemonByName = "$pokemon/{name}"
        const val moveByName = "move/{name}"
    }
}
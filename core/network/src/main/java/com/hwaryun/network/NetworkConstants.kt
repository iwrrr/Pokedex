package com.hwaryun.network

object NetworkConstants {
    const val baseUrl = "https://pokeapi.co/api/v2/"

    object Pokemon {
        const val route = baseUrl + "pokemon"
        const val byName = "$route/{name}"
    }
}
package com.hwaryun.network.model

data class PokemonDto(
  var page: Int = 0,
  val name: String,
  val url: String
)
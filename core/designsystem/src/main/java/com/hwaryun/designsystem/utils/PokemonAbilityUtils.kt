package com.hwaryun.designsystem.utils

import androidx.compose.ui.graphics.Color
import com.hwaryun.designsystem.Bug
import com.hwaryun.designsystem.Dark
import com.hwaryun.designsystem.Dragon
import com.hwaryun.designsystem.Electric
import com.hwaryun.designsystem.Fairy
import com.hwaryun.designsystem.Fighting
import com.hwaryun.designsystem.Fire
import com.hwaryun.designsystem.Flying
import com.hwaryun.designsystem.Ghost
import com.hwaryun.designsystem.Grass
import com.hwaryun.designsystem.Gray400
import com.hwaryun.designsystem.Ground
import com.hwaryun.designsystem.Ice
import com.hwaryun.designsystem.Poison
import com.hwaryun.designsystem.Psychic
import com.hwaryun.designsystem.Rock
import com.hwaryun.designsystem.Steel
import com.hwaryun.designsystem.Water

object PokemonAbilityUtils {

    fun getAbilityColor(name: String): Color = when (name) {
        "fighting" -> Fighting
        "flying" -> Flying
        "poison" -> Poison
        "ground" -> Ground
        "rock" -> Rock
        "bug" -> Bug
        "ghost" -> Ghost
        "steel" -> Steel
        "fire" -> Fire
        "water" -> Water
        "grass" -> Grass
        "electric" -> Electric
        "psychic" -> Psychic
        "ice" -> Ice
        "dragon" -> Dragon
        "fairy" -> Fairy
        "dark" -> Dark
        else -> Gray400
    }
}
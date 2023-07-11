package com.hwaryun.pokemon_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hwaryun.designsystem.utils.PokemonAbilityUtils
import com.hwaryun.domain.model.PokemonInfo

@Composable
internal fun AbilityRow(
    types: List<PokemonInfo.TypeResponse>,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        items(types, key = { it.slot }) { typeResponse ->
            AbilityItem(
                name = typeResponse.type.name,
                containerColor = PokemonAbilityUtils.getAbilityColor(typeResponse.type.name)
            )
        }
    }
}
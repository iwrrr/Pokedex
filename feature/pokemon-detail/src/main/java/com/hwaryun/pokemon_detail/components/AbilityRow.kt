package com.hwaryun.pokemon_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hwaryun.designsystem.utils.PokemonAbilityUtils
import com.hwaryun.domain.model.Info

@Composable
internal fun AbilityRow(
    types: List<Info>,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        items(types, key = { it.id }) { typeResponse ->
            AbilityItem(
                name = typeResponse.name,
                containerColor = PokemonAbilityUtils.getAbilityColor(typeResponse.name)
            )
        }
    }
}
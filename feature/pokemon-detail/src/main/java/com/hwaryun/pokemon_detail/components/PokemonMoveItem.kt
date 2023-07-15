package com.hwaryun.pokemon_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.material.placeholder
import com.hwaryun.common.UiResult
import com.hwaryun.common.ext.capitalizeAndRemoveHyphen
import com.hwaryun.designsystem.utils.PokemonType
import com.hwaryun.domain.model.PokemonMove

@Composable
internal fun PokemonMoveItem(
    result: UiResult<PokemonMove>,
    modifier: Modifier = Modifier
) {
    when (result) {
        is UiResult.Success -> {
            result.value?.let {
                MoveItem(move = it, modifier = modifier)
            }
        }

        else -> {
            MovePlaceholder()
        }
    }
}

@Composable
private fun MovePlaceholder() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(42.dp)
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .placeholder(visible = true, shape = RoundedCornerShape(12.dp))
    )
}

@Composable
private fun MoveItem(
    move: PokemonMove,
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .clickable { }
            .padding(horizontal = 20.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = move.name.capitalizeAndRemoveHyphen(),
            style = MaterialTheme.typography.body1
        )

        MoveStatus(status = move.power, width = 40.dp)
        MoveStatus(status = move.accuracy, width = 40.dp)
        MoveStatus(status = move.pp, width = 40.dp)

        MoveType(type = PokemonType.from(move.type.name))
    }
}

@Composable
private fun MoveType(type: PokemonType) {
    Box(
        Modifier
            .size(width = 80.dp, height = 24.dp)
            .shadow(elevation = 4.dp, shape = CircleShape)
            .background(color = colorResource(id = type.typeColorResId), shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(text = type.name)
    }
}

@Composable
private fun MoveStatus(status: Int, width: Dp) {
    Text(
        modifier = Modifier.width(width),
        textAlign = TextAlign.Center,
        text = status.toString(),
        style = MaterialTheme.typography.body1
    )
}
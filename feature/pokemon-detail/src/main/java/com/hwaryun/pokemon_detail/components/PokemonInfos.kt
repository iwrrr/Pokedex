package com.hwaryun.pokemon_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Scale
import androidx.compose.material.icons.outlined.Straighten
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hwaryun.domain.model.PokemonInfo

@Composable
internal fun PokemonInfos(
    pokemonInfo: PokemonInfo,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colors.onBackground.copy(.05f))
            .padding(horizontal = 12.dp, vertical = 16.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Outlined.Scale,
                    contentDescription = null
                )
                Spacer(Modifier.width(4.dp))

                val weightInKg = pokemonInfo.weight / 10f

                Text(
                    text = "$weightInKg kg",
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Spacer(Modifier.height(4.dp))

            Text(
                text = "Weight",
                color = MaterialTheme.colors.onBackground.copy(.8f),
                style = MaterialTheme.typography.body2
            )
        }

        Box(
            modifier = Modifier
                .width(1.dp)
                .height(30.dp)
                .background(color = MaterialTheme.colors.onBackground)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Outlined.Straighten,
                    contentDescription = null,
                    modifier = Modifier.rotate(90f)
                )
                Spacer(Modifier.width(4.dp))

                val heightInMeter = pokemonInfo.height / 10f

                Text(
                    text = "$heightInMeter m",
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Spacer(Modifier.height(4.dp))

            Text(
                text = "Height",
                color = MaterialTheme.colors.onBackground.copy(.8f),
                style = MaterialTheme.typography.body2
            )
        }
    }
}
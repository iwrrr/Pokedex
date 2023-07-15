package com.hwaryun.pokedex.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hwaryun.designsystem.PokedexTheme

@Composable
internal fun AppBar(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
    onPokeballClick: () -> Unit
) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colors.background)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
            )
            subtitle?.let {
                Text(
                    text = subtitle,
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(.4f),
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }
        IconButton(onClick = onPokeballClick) {
            Image(
                painter = painterResource(id = com.hwaryun.designsystem.R.drawable.ic_pokeball_colored),
                contentDescription = "Catched Pokemons",
                modifier = Modifier.size(48.dp)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun DefaultPreview() {
    PokedexTheme {
        AppBar(
            title = "Title",
            onPokeballClick = {}
        )
    }
}
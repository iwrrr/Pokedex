package com.hwaryun.pokedex.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hwaryun.designsystem.PokedexTheme
import com.hwaryun.domain.Pokemon

@Composable
internal fun PokemonItem(
    modifier: Modifier = Modifier,
    pokemon: Pokemon,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier.height(120.dp),
        shape = RoundedCornerShape(16.dp),
        contentColor = MaterialTheme.colors.onBackground,
        border = BorderStroke(0.5.dp, Color.LightGray),
        elevation = 0.dp
    ) {
        Box(
            contentAlignment = Alignment.BottomEnd
        ) {
            Image(
                painter = painterResource(id = com.hwaryun.designsystem.R.drawable.pokeball),
                contentDescription = null,
                modifier = Modifier
                    .offset(x = 5.dp, y = 10.dp)
                    .size(96.dp),
                alpha = 0.05f
            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(pokemon.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = pokemon.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(80.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onClick() }
                    .padding(10.dp)
            ) {
                Text(
                    text = pokemon.numberString,
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.alpha(.4f)
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = pokemon.name.replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.alpha(.8f)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun DefaultPreview() {
    PokedexTheme {
        PokemonItem(
            modifier = Modifier
                .width(160.dp)
                .height(120.dp),
            pokemon = Pokemon(
                0,
                "bulbasaur",
                "https://pokeapi.co/api/v2/pokemon/1/"
            ),
            onClick = {}
        )
    }
}
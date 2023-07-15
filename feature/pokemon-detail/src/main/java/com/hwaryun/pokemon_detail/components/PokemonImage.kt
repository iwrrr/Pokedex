package com.hwaryun.pokemon_detail.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hwaryun.designsystem.R
import com.hwaryun.domain.model.PokemonInfo

@Composable
fun PokemonImage(
    pokemonInfo: PokemonInfo
) {
    Box(contentAlignment = Alignment.TopCenter) {
        val context = LocalContext.current

        var currentRotation by remember { mutableFloatStateOf(0f) }
        val rotation = remember { Animatable(currentRotation) }

        LaunchedEffect(Unit) {
            // Infinite repeatable rotation when is playing
            rotation.animateTo(
                targetValue = currentRotation + 360f,
                animationSpec = infiniteRepeatable(
                    animation = tween(10000, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                )
            ) {
                currentRotation = value
            }
        }

        Image(
            painter = painterResource(id = R.drawable.pokeball),
            contentDescription = null,
            modifier = Modifier
                .offset(y = 0.dp)
                .widthIn(max = 800.dp)
                .fillMaxWidth(.8f)
                .wrapContentHeight(Alignment.Top, true)
                .rotate(currentRotation),
            contentScale = ContentScale.FillWidth,
            alpha = 0.05f
        )
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(pokemonInfo.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = pokemonInfo.name,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .widthIn(max = 500.dp)
                .fillMaxWidth()
                .aspectRatio(1.2f)
                .fillMaxHeight()
        )
    }
}
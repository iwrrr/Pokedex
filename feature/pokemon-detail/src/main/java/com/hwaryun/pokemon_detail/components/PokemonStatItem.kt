package com.hwaryun.pokemon_detail.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hwaryun.designsystem.Green300
import com.hwaryun.designsystem.Yellow400
import com.hwaryun.domain.model.PokemonInfo
import kotlin.math.roundToInt

@Composable
internal fun PokemonStatItem(
    statResponse: PokemonInfo.Stats,
    modifier: Modifier = Modifier
) {
    val animationProgress = remember {
        Animatable(
            initialValue = 0f,
        )
    }

    LaunchedEffect(Unit) {
        animationProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 8 * statResponse.value,
                easing = LinearEasing
            )
        )
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(
            text = statResponse.name,
            color = MaterialTheme.colors.onBackground.copy(.8f),
            style = MaterialTheme.typography.body2,
            modifier = Modifier.weight(.3f)
        )

        Text(
            text = "${(statResponse.value * animationProgress.value).roundToInt()}",
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.body1.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.weight(.2f)
        )

        val progress = statResponse.value.toFloat() / statResponse.maxValue.toFloat()
        val animatedProgress = progress * animationProgress.value

        val progressColor = if (progress >= .5f) Green300 else Yellow400
        val progressTrackColor = MaterialTheme.colors.onBackground.copy(.05f)

        Box(
            modifier = Modifier
                .weight(.5f)
                .height(10.dp)
                .drawBehind {
                    drawRoundRect(
                        color = progressTrackColor,
                        topLeft = Offset.Zero,
                        size = size,
                        cornerRadius = CornerRadius(size.height, size.height),
                    )

                    drawRoundRect(
                        color = progressColor,
                        topLeft = Offset.Zero,
                        size = Size(width = size.width * animatedProgress, height = size.height),
                        cornerRadius = CornerRadius(size.height, size.height),
                    )
                }
        )
    }
}
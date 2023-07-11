package com.hwaryun.pokemon_detail

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hwaryun.designsystem.PokedexTheme
import com.hwaryun.designsystem.R
import com.hwaryun.pokemon_detail.components.AbilityRow
import com.hwaryun.pokemon_detail.components.PokemonInfos
import com.hwaryun.pokemon_detail.components.PokemonStats
import timber.log.Timber

@Composable
internal fun PokemonDetailRoute(
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    PokemonDetailScreen(state = state)
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun PokemonDetailScreen(
    state: PokemonDetailState,
) {
    val context = LocalContext.current
    Box(contentAlignment = Alignment.TopCenter) {
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
                .offset(y = 120.dp)
                .widthIn(max = 800.dp)
                .fillMaxWidth(.8f)
                .wrapContentHeight(Alignment.Top, true)
                .rotate(currentRotation),
            contentScale = ContentScale.FillWidth,
            alpha = 0.05f
        )
        //        state.pokemonInfo?.let { pokemonInfo ->
        //            AsyncImage(
        //                model = ImageRequest.Builder(context)
        //                    .data(pokemonInfo.imageUrl)
        //                    .crossfade(true)
        //                    .build(),
        //                contentDescription = pokemonInfo.name,
        //                contentScale = ContentScale.FillWidth,
        //                colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(3f) }),
        //                modifier = Modifier
        //                    .widthIn(max = 800.dp)
        //                    .fillMaxWidth(.9f)
        //                    .wrapContentHeight(Alignment.Top, true)
        //                    .scale(1f, 1.8f)
        //                    .blur(70.dp, BlurredEdgeTreatment.Unbounded)
        //                    .alpha(.5f)
        //            )
        //        }

        Scaffold(
            topBar = {
                Row(
                    modifier = Modifier
                        .systemBarsPadding()
                        .fillMaxWidth()
                        .height(56.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(Icons.Rounded.ArrowBackIosNew, contentDescription = null)
                    }
                    state.pokemonInfo?.let { pokemonInfo ->
                        Text(
                            text = pokemonInfo.name.replaceFirstChar { it.uppercaseChar() },
                            color = MaterialTheme.colors.onBackground,
                            style = MaterialTheme.typography.h6.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    IconButton(
                        onClick = {
                            state.pokemonInfo?.let { pokemonInfo ->
                                //                                    onEvent(
                                //                                        DetailsStore.Intent.UpdatePokemonFavoriteState(
                                //                                            isFavorite = !pokemonInfo.isFavorite
                                //                                        )
                                //                                    )
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (state.pokemonInfo?.isFavorite == true) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = if (state.pokemonInfo?.isFavorite == true) {
                                MaterialTheme.colors.primary
                            } else {
                                MaterialTheme.colors.onBackground
                            }
                        )
                    }
                }
                //                TopAppBar(
                //                    modifier = Modifier.statusBarsPadding(),
                //                    navigationIcon = {
                //                        IconButton(
                //                            onClick = {}
                //                        ) {
                //                            Icon(Icons.Rounded.ArrowBackIosNew, contentDescription = null)
                //                        }
                //                    },
                //                    title = {
                //                        state.pokemonInfo?.let { pokemonInfo ->
                //                            Text(
                //                                text = pokemonInfo.name.replaceFirstChar { it.uppercaseChar() },
                //                                color = MaterialTheme.colors.onBackground,
                //                                style = MaterialTheme.typography.h6.copy(
                //                                    fontWeight = FontWeight.Bold
                //                                ),
                //                                textAlign = TextAlign.Center,
                //                                modifier = Modifier.fillMaxWidth()
                //                            )
                //                        }
                //                    },
                //                    actions = {
                //                        IconButton(
                //                            onClick = {
                //                                state.pokemonInfo?.let { pokemonInfo ->
                //                                    //                                    onEvent(
                //                                    //                                        DetailsStore.Intent.UpdatePokemonFavoriteState(
                //                                    //                                            isFavorite = !pokemonInfo.isFavorite
                //                                    //                                        )
                //                                    //                                    )
                //                                }
                //                            }
                //                        ) {
                //                            Icon(
                //                                imageVector = if (state.pokemonInfo?.isFavorite == true) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                //                                contentDescription = "Favorite",
                //                                tint = if (state.pokemonInfo?.isFavorite == true) {
                //                                    MaterialTheme.colors.primary
                //                                } else {
                //                                    MaterialTheme.colors.onBackground
                //                                }
                //                            )
                //                        }
                //                    },
                //                    backgroundColor = Color.Transparent,
                //                    elevation = 0.dp
                //                )
            },
            backgroundColor = Color.Transparent,
        ) { paddingValue ->
            Box(
                modifier = Modifier
                    .padding(paddingValue)
                    .padding(20.dp)
            ) {
                if (state.isLoading) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator()
                    }
                }

                state.error?.let { error ->
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(text = error)
                    }
                }

                state.pokemonInfo?.let { pokemonInfo ->
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        item("image") {
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

                        item("name") {
                            Text(
                                text = pokemonInfo.name.replaceFirstChar { it.uppercaseChar() },
                                color = MaterialTheme.colors.onBackground,
                                style = MaterialTheme.typography.h5.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        item("id") {
                            Text(
                                text = pokemonInfo.idString,
                                color = MaterialTheme.colors.onBackground.copy(alpha = .6f),
                                style = MaterialTheme.typography.h6.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        item("abilities") {
                            AbilityRow(
                                types = pokemonInfo.types
                            )
                        }

                        item("infos") {
                            PokemonInfos(
                                pokemonInfo = pokemonInfo,
                                modifier = Modifier
                                    .padding(top = 18.dp)
                                    .fillMaxWidth(.9f)
                            )
                        }

                        item("stats") {
                            Timber.d("DEBUG ====> $pokemonInfo")
                            PokemonStats(
                                pokemonInfo = pokemonInfo,
                                modifier = Modifier
                                    .padding(top = 12.dp)
                                    .fillMaxWidth(.9f)
                            )
                        }
                    }
                }
            }
        }
    }
    //    Scaffold(
    //        modifier = Modifier.statusBarsPadding(),
    //        topBar = {
    ////            AppBar(title = "Pokedex", subtitle = "What Pokémon are you looking for?")
    //        }
    //    ) {
    //
    //    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun DefaultPreview() {
    PokedexTheme {
        PokemonDetailScreen(
            state = PokemonDetailState()
        )
    }
}
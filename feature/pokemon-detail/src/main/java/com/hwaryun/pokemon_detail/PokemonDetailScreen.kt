package com.hwaryun.pokemon_detail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hwaryun.designsystem.PokedexTheme
import com.hwaryun.designsystem.components.TabItem
import com.hwaryun.designsystem.components.Tabs
import com.hwaryun.domain.model.Info
import com.hwaryun.domain.model.PokemonInfo
import com.hwaryun.pokemon_detail.components.AbilityRow
import com.hwaryun.pokemon_detail.components.AppBar
import com.hwaryun.pokemon_detail.components.CatchDialog
import com.hwaryun.pokemon_detail.components.PokemonImage
import com.hwaryun.pokemon_detail.components.PokemonInfos
import com.hwaryun.pokemon_detail.components.PokemonMoves
import com.hwaryun.pokemon_detail.components.PokemonStats
import com.hwaryun.pokemon_detail.components.ProgressDialog

@Composable
internal fun PokemonDetailRoute(
    popBackStack: () -> Unit,
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val catchState by viewModel.catchState.collectAsStateWithLifecycle()

    PokemonDetailScreen(
        state = state,
        catchState = catchState,
        onCatchOrReleasePokemon = viewModel::catchOrReleasePokemon,
        dismissDialog = viewModel::dismissDialog,
        popBackStack = popBackStack
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun PokemonDetailScreen(
    state: PokemonDetailState,
    catchState: CatchPokemonState,
    onCatchOrReleasePokemon: (String, Boolean) -> Unit,
    dismissDialog: () -> Unit,
    popBackStack: () -> Unit
) {
    Scaffold(
        topBar = {
            state.pokemonInfo?.let { pokemonInfo ->
                AppBar(pokemonInfo = pokemonInfo, onNavigationClick = popBackStack)
            }
        },
        backgroundColor = Color.Transparent,
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(
                top = innerPadding.calculateTopPadding(),
                bottom = innerPadding.calculateBottomPadding()
            ),
            contentAlignment = Alignment.BottomCenter
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
                        PokemonImage(pokemonInfo = pokemonInfo)
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
                        Tabs(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            tabItems = listOf(
                                TabItem(
                                    title = "Stats",
                                    screen = {
                                        PokemonStats(pokemonInfo = pokemonInfo)
                                    },
                                ),
                                TabItem(
                                    title = "Moves",
                                    screen = {
                                        PokemonMoves(pokemonInfo = pokemonInfo)
                                    },
                                ),
                            )
                        )
                    }

                    item("catch") {
                        Column(
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 24.dp),
                        ) {
                            val catchText =
                                if (catchState.isCatched) "Release pokémon" else "Catch!"

                            Button(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                elevation = ButtonDefaults.elevation(0.dp),
                                onClick = {
                                    onCatchOrReleasePokemon(
                                        pokemonInfo.name,
                                        !catchState.isCatched
                                    )
                                },
                            ) {
                                Text(text = catchText)
                            }
                        }
                    }
                }
            }
        }

        if (catchState.isLoading) {
            ProgressDialog()
        }

        if (catchState.showDialog) {
            CatchDialog(
                catched = catchState.isCatched,
                failed = catchState.failed,
                onDismissRequest = dismissDialog
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun DefaultPreview() {
    PokedexTheme {
        PokemonDetailScreen(
            state = PokemonDetailState(
                pokemonInfo = PokemonInfo(
                    id = 0,
                    name = "bulbasaur",
                    height = 1,
                    weight = 1,
                    experience = 1,
                    moves = listOf(),
                    types = listOf(Info(name = "Water", id = 1)),
                    stats = listOf(PokemonInfo.Stats(1, Info("Hp"))),
                    isCatched = false,
                )
            ),
            catchState = CatchPokemonState(),
            onCatchOrReleasePokemon = { _, _ -> },
            dismissDialog = {}
        ) {}
    }
}
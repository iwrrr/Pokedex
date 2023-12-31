package com.hwaryun.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun Tabs(
    modifier: Modifier = Modifier,
    tabItems: List<TabItem>
) {
    var tabIndex by rememberSaveable { mutableIntStateOf(0) }

    Column(modifier = modifier.fillMaxWidth()) {
        TabRow(
            selectedTabIndex = tabIndex,
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.onBackground,
            indicator = { tabPositions ->
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[tabIndex])
                        .height(3.dp)
                        .padding(horizontal = 85.dp)
                        .background(color = Black, shape = RoundedCornerShape(8.dp))
                )
            },
            divider = {},
        ) {
            tabItems.forEachIndexed { index, item ->
                Tab(
                    text = { Text(item.title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    interactionSource = object : MutableInteractionSource {
                        override val interactions: Flow<Interaction> = emptyFlow()

                        override suspend fun emit(interaction: Interaction) = Unit

                        override fun tryEmit(interaction: Interaction): Boolean = true
                    }
                )
            }
        }

        tabItems[tabIndex].screen()
    }
}

data class TabItem(
    val title: String = "",
    val screen: @Composable () -> Unit
)
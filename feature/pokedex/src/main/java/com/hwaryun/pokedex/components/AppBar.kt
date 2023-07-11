package com.hwaryun.pokedex.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hwaryun.designsystem.PokedexTheme

@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
    content: @Composable() (() -> Unit)? = null
) {
    Column {
        Row(
            modifier = modifier
                .background(MaterialTheme.colors.background)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.padding(vertical = 16.dp),
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
        }
        content?.invoke()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun DefaultPreview() {
    PokedexTheme {
        AppBar(
            title = "Title"
        )
    }
}
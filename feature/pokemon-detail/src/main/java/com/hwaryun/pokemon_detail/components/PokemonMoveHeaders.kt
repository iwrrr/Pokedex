package com.hwaryun.pokemon_detail.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MoveHeaders(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CompositionLocalProvider(
            LocalTextStyle provides MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Medium)
        ) {
            Text(text = stringResource(com.hwaryun.designsystem.R.string.move_name_label))
            Spacer(modifier = Modifier.weight(1f))
            MoveHeader(title = com.hwaryun.designsystem.R.string.move_power_label, width = 40.dp)
            MoveHeader(title = com.hwaryun.designsystem.R.string.move_accurary_label, width = 40.dp)
            MoveHeader(
                title = com.hwaryun.designsystem.R.string.move_power_point_label,
                width = 40.dp
            )
            MoveHeader(title = com.hwaryun.designsystem.R.string.move_type_label, width = 80.dp)
        }
    }
}

@Composable
private fun MoveHeader(@StringRes title: Int, width: Dp) {
    Text(
        modifier = Modifier.width(width),
        textAlign = TextAlign.Center,
        text = stringResource(id = title)
    )
}
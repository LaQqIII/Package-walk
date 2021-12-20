package com.example.packagewalk.ui.widgets.text

import androidx.annotation.StringRes
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun TextCaption(
    @StringRes stringId: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = stringId),
        modifier = modifier,
        color = MaterialTheme.colors.onBackground.copy(0.60f),
        style = MaterialTheme.typography.caption
    )
}
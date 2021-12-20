package com.example.packagewalk.ui.widgets.text

import androidx.annotation.StringRes
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign

@Composable
fun PackageWalkTextButton(@StringRes stringId: Int) {
    Text(
        text = stringResource(stringId),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.button
    )
}
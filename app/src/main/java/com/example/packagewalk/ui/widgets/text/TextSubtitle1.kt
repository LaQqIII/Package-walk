package com.example.packagewalk.ui.widgets.text

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun TextSubtitle1(
    value: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Center
) {
    Text(
        text = value,
        modifier = modifier,
        color = MaterialTheme.colors.onBackground.copy(0.87f),
        textAlign = textAlign,
        style = MaterialTheme.typography.subtitle1
    )
}
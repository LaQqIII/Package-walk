package com.example.packagewalk.ui.widgets

import androidx.annotation.StringRes
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.packagewalk.R

@Composable
fun TextButton(@StringRes stringId: Int) {
    Text(
        text = stringResource(stringId),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.button
    )
}

@Preview(showBackground = true)
@Composable
private fun TextButtonPreview() {
    TextButton(R.string.verification_text)
}
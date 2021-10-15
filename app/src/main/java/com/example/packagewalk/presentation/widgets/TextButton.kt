package com.example.packagewalk.presentation.widgets

import androidx.annotation.StringRes
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.packagewalk.R

@Composable
fun PackageWalkTextButton(@StringRes stringId: Int) {
    Text(
        text = stringResource(stringId),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.button
    )
}

@Preview(showBackground = true)
@Composable
private fun PackageWalkTextButtonPreview() {
    PackageWalkTextButton(R.string.verification_text)
}
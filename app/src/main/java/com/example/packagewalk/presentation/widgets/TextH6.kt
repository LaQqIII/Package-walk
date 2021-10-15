package com.example.packagewalk.presentation.widgets

import androidx.annotation.StringRes
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.packagewalk.R
import com.example.packagewalk.presentation.theme.PackageWalkTheme

private const val ALPHA_HEAD_TEXT = 0.87f

/**
 * Отображает заголовочный текст по середине, стиль H6 материал дизайна.
 * @param stringId id ресурса с текстом
 */
@Composable
fun TextH6(@StringRes stringId: Int, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(stringId),
        modifier = modifier,
        color = MaterialTheme.colors.onBackground.copy(ALPHA_HEAD_TEXT),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h6
    )
}

@Preview(showBackground = true)
@Composable
private fun TextH6Preview() {
    PackageWalkTheme() {
        TextH6(R.string.verification_text)
    }
}
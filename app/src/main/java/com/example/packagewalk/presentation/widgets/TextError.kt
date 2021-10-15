package com.example.packagewalk.presentation.widgets

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.packagewalk.R

@Composable
fun TextError(@StringRes stringId: Int) {
    Text(
        text = stringResource(stringId),
        color = MaterialTheme.colors.error,
        style = MaterialTheme.typography.caption,
        modifier = Modifier.padding(start = dimensionResource(R.dimen.around_base))
    )
}
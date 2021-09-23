package com.example.packagewalk.ui.widgets

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.packagewalk.R

@Composable
fun StandartButton(onClick: () -> Unit, modifier: Modifier = Modifier, @StringRes stringId: Int) {
    Button(
        onClick = { onClick() }, modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen.on_the_sides_base))
    ) {
        Text(text = stringResource(stringId))
    }
}
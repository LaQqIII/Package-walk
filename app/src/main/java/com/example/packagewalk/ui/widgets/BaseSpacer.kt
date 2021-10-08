package com.example.packagewalk.ui.widgets

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.packagewalk.R

@Composable
fun StandartSpacer(modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.height(dimensionResource(R.dimen.between_the_lines_base)))
}
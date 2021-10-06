package com.example.packagewalk.ui.widgets

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.packagewalk.R

/**
 * Наиболее часто используемая "заполненная" кнопка
 * @param onClick событие, вызываемое по клику на кнопку
 * @param modifier модификатор кнопки
 * @param stringId текст, отображаемый внутри кнопки
 */
@Composable
fun StandartButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    @StringRes stringId: Int,
    enabled: Boolean = true,
) {
    Button(
        onClick = { onClick() },
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen.height_button_base))
            .padding(horizontal = dimensionResource(R.dimen.on_the_sides_base)),
        enabled = enabled,
        shape = CircleShape
    ) {
        TextButton(stringId = stringId)
    }
}

/**
 * Наиболее часто используемая "пустая" кнопка
 * @param onClick событие, вызываемое по клику на кнопку
 * @param modifier модификатор кнопки
 * @param stringId текст, отображаемый внутри кнопки
 */
@Composable
fun StandartOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    @StringRes stringId: Int
) {
    OutlinedButton(
        onClick = { onClick() },
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen.height_button_base))
            .padding(horizontal = dimensionResource(R.dimen.on_the_sides_base)),
        shape = CircleShape
    ) {
        TextButton(stringId = stringId)
    }
}

@Preview(showBackground = true)
@Composable
private fun StandartButtonPreview() {
    StandartButton(onClick = {}, stringId = R.string.register)
}

@Preview(showBackground = true)
@Composable
private fun StandartOutlinedButton() {
    StandartOutlinedButton(onClick = {}, stringId = R.string.login)
}
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
import com.example.packagewalk.ui.widgets.text.PackageWalkTextButton

/**
 * Наиболее часто используемая "заполненная" кнопка
 * @param onClick событие, вызываемое по клику на кнопку
 * @param modifier модификатор кнопки
 * @param stringId текст, отображаемый внутри кнопки
 * @param enabled признак доступна кнопка для нажатия или нет
 */
@Composable
fun PackageWalkButton(
    @StringRes stringId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Button(
        onClick = { onClick() },
        modifier = modifier.height(dimensionResource(R.dimen.height_button_base)),
        enabled = enabled,
        shape = CircleShape
    ) {
        PackageWalkTextButton(stringId = stringId)
    }
}

/**
 * Наиболее часто используемая "пустая" кнопка
 * @param onClick событие, вызываемое по клику на кнопку
 * @param modifier модификатор кнопки
 * @param stringId текст, отображаемый внутри кнопки
 */
@Composable
fun PackageWalkOutlinedButton(
    @StringRes stringId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedButton(
        onClick = { onClick() },
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen.height_button_base))
            .padding(horizontal = dimensionResource(R.dimen.on_the_sides_base)),
        shape = CircleShape
    ) {
        PackageWalkTextButton(stringId = stringId)
    }
}

@Preview(showBackground = true)
@Composable
private fun PackageWalkButtonPreview() {
    PackageWalkButton(onClick = {}, stringId = R.string.register)
}

@Preview(showBackground = true)
@Composable
private fun PackageWalkOutlinedButtonPreview() {
    PackageWalkOutlinedButton(onClick = {}, stringId = R.string.login)
}
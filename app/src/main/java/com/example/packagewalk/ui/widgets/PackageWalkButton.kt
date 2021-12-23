package com.example.packagewalk.ui.widgets

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    loading: Boolean = false
) {
    Button(
        onClick = { onClick() },
        modifier = modifier.height(dimensionResource(R.dimen.height_button_base)),
        enabled = enabled,
        shape = CircleShape
    ) {
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(dimensionResource(id = R.dimen.size_progress_bar)),
                color = MaterialTheme.colors.surface
            )
        } else {
            PackageWalkTextButton(stringId = stringId)
        }
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
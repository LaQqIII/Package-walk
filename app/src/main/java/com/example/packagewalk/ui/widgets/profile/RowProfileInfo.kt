package com.example.packagewalk.ui.widgets.profile

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.packagewalk.ui.widgets.text.TextCaption
import com.example.packagewalk.ui.widgets.text.TextSubtitle1

/**
 * Строка с отображением какой-то одной конкретной информации о сервисе.
 * @param caption - текст заголовка
 * @param value - данные об автосервисе
 */
@Composable
fun RowProfileInfo(
    @StringRes caption: Int,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        TextCaption(stringId = caption, modifier = Modifier.padding(top = 8.dp))
        TextSubtitle1(value = value, modifier = Modifier.padding(bottom = 8.dp))
        Divider()
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
}
package com.example.packagewalk.ui.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/**
 * Наиболее часто используемое поле для ввода
 * @param value значение поле ввода
 * @param onValueChange функция, вызываемая при изменении поля ввода
 */
@Composable
fun StandartOutlinedTextField(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
private fun StandartOutlinedTextFieldPreview() {
    StandartOutlinedTextField("", {})
}
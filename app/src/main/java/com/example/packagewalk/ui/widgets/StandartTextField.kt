package com.example.packagewalk.ui.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.*
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

/**
 * Стандартное поле для ввода мобильного телефона, пока только для номеров, начинающихся с +7
 * @param value значение поле ввода
 * @param onValueChange функция, вызываемая при изменении поля ввода
 * @param onDoneClick функция, вызываемая после набора номера, для отправки проверочного кода
 */
@Composable
fun StandartMobileTextField(
    value: String,
    onValueChange: (String) -> Unit,
    onDoneClick: () -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = { if (it.length <= 10) onValueChange(it) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = { onDoneClick() }),
        visualTransformation = { inputText ->

            // должно получиться вот так: +7 xxx xxx xx xx
            val trimmed =
                if (inputText.text.length >= 10)
                    inputText.text.substring(0..9)
                else
                    inputText.text

            var outputText = "+7 "
            trimmed.forEachIndexed { index, c ->
                outputText += c
                if ((index == 2 || index == 5 || index == 7) && index != 9) {
                    outputText += " "
                }
            }

            val numberOffsetTranslator = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    if (offset <= 2) return offset + 3
                    if (offset <= 5) return offset + 4
                    if (offset <= 7) return offset + 5
                    if (offset <= 10) return offset + 6
                    return 16
                }

                override fun transformedToOriginal(offset: Int): Int {
                    if (offset <= 2) return offset
                    if (offset <= 6) return offset - 1
                    if (offset <= 10) return offset - 2
                    return 9
                }
            }

            TransformedText(AnnotatedString(outputText), numberOffsetTranslator)
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun StandartOutlinedTextFieldPreview() {
    StandartOutlinedTextField("", {})
}

@Preview(showBackground = true)
@Composable
private fun StandartMobileTextFieldPreview() {
    StandartMobileTextField("", {}, {})
}
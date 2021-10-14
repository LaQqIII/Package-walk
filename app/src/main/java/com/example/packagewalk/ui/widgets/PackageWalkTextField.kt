package com.example.packagewalk.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.packagewalk.R

/**
 * Стандартное поле для ввода мобильного телефона, пока только для номеров, начинающихся с +7
 * @param value значение поле ввода
 * @param onValueChange функция, вызываемая при изменении поля ввода
 * @param onDoneClick функция, вызываемая после набора номера, для отправки проверочного кода
 */
@Composable
fun PackageWalkMobileTextField(
    value: String,
    onValueChange: (String) -> Unit,
    onDoneClick: () -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = Modifier.fillMaxWidth(),
        textStyle = MaterialTheme.typography.subtitle1.copy(fontSize = 18.sp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = { onDoneClick() }),
        visualTransformation = { mobileNumberTransformation(it.text) }
    )
}

/**
 * Функция преобразующая ввод номера в удобно читаемый формат
 * на выходе должно получиться вот так +7 xxx xxx xx xx
 * @param inputText входящий текст
 */
private fun mobileNumberTransformation(inputText: String): TransformedText {

    val prefix = "+7 "
    val prefixOffset = prefix.length

    val trimmed =
        if (inputText.length >= 10)
            inputText.substring(0..9)
        else
            inputText

    var outputText = prefix + ""
    trimmed.forEachIndexed { index, c ->
        outputText += c
        if ((index == 2 || index == 5 || index == 7) && index != 9) {
            outputText += " "
        }
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 2) return offset + prefixOffset
            if (offset <= 5) return offset + 1 + prefixOffset
            if (offset <= 7) return offset + 2 + prefixOffset
            if (offset <= 10) return offset + 3 + prefixOffset
            return 13 + prefixOffset
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= prefixOffset - 1) return prefixOffset
            return offset - prefixOffset
        }
    }

    return TransformedText(AnnotatedString(outputText), numberOffsetTranslator)
}

@Composable
fun EnterCodeTextField(
    value: String,
    onValueChange: (String) -> Unit,
    onDoneClick: () -> Unit,
    isError: Boolean
) {
    Column {
        OutlinedTextField(
            value = value,
            onValueChange = { onValueChange(it) },
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.subtitle1.copy(fontSize = 18.sp),
            isError = isError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { onDoneClick() })
        )
        if (isError) {
            TextError(stringId = R.string.error_code)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PackageWalkMobileTextFieldPreview() {
    PackageWalkMobileTextField("", {}, {})
}
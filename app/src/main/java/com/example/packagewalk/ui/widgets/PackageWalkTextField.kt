package com.example.packagewalk.ui.widgets

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextFieldApp(
    value: String,
    onValueChange: (String) -> Unit,
    onDoneClick: () -> Unit,
    modifier: Modifier = Modifier,
    @StringRes label: Int,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    enabled: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    @StringRes error: Int? = null
) {
    Column(
        modifier = Modifier.padding(
            bottom = if (isError) 0.dp else 16.dp
        )
    ) {
        TextField(
            value = value,
            onValueChange = { onValueChange(it) },
            modifier = modifier,
            enabled = enabled,
            textStyle = MaterialTheme.typography.subtitle1.copy(fontSize = 18.sp),
            label = { Text(text = stringResource(id = label)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { onDoneClick() }),
            visualTransformation = visualTransformation
        )
        if (isError && error != null) {
            Text(
                text = stringResource(id = error),
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

/**
 * Функция преобразующая ввод номера в удобно читаемый формат
 * на выходе должно получиться вот так +7 xxx xxx xx xx
 * @param inputText входящий текст
 */
fun mobileNumberTransformation(inputText: String): TransformedText {
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
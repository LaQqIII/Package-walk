package com.example.packagewalk.ui.widgets

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText

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

/** Функция преобразующая ввод даты в удобно читаемый формат
 * На выходе XX/XX/XXXX */
fun dateTransformation(inputText: String): TransformedText {
    var out = ""
    inputText.forEachIndexed { index, char ->
        when (index) {
            2 -> out += "/$char"
            4 -> out += "/$char"
            else -> out += char
        }
    }
    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 2) return offset
            if (offset <= 4) return offset + 1
            return offset + 2
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 2) return offset
            if (offset <= 5) return offset - 1
            return offset - 2
        }
    }
    return TransformedText(AnnotatedString(out), numberOffsetTranslator)
}
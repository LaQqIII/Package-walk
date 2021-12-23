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
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.packagewalk.R

@Composable
fun TextFieldApp(
    value: String,
    onValueChange: (String) -> Unit,
    onDoneClick: () -> Unit,
    modifier: Modifier = Modifier,
    @StringRes label: Int,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    enabled: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    @StringRes error: Int? = R.string.error_text,
    trailingIcon: @Composable() (() -> Unit)? = null,
    inputChecks: ((String) -> Boolean)? = null,
    startCheck: Boolean = false
) {
    val isError = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.padding(
            bottom = if (isError.value) 0.dp else 16.dp
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
            visualTransformation = visualTransformation,
            trailingIcon = trailingIcon
        )
        if (isError.value && error != null) {
            Text(
                text = stringResource(id = error),
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
    SideEffect {
        if (startCheck && inputChecks != null) isError.value = inputChecks(value)
    }
}
package com.example.packagewalk.ui.widgets

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.packagewalk.R

@Composable
fun PackageWalkTextField(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes label: Int,
    modifier: Modifier = Modifier,
    onDoneClick: (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    enabled: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    @StringRes error: Int? = R.string.error_text,
    trailingIcon: @Composable (() -> Unit)? = null,
    inputChecks: ((String) -> Boolean)? = null,
    startCheck: Boolean = false
) {
    val inputService = LocalTextInputService.current
    val isError = remember { mutableStateOf(false) }
    Column(
        modifier = modifier.padding(
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
                capitalization = KeyboardCapitalization.Sentences,
                keyboardType = keyboardType,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                if (onDoneClick != null) {
                    onDoneClick()
                } else {
                    inputService?.hideSoftwareKeyboard()
                }
            }),
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

@Composable
fun TextFieldWithDropdownMenu(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes label: Int,
    listOfValues: List<String>,
    expanded: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    onDoneClick: (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    enabled: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    @StringRes error: Int? = R.string.error_text,
    trailingIcon: @Composable (() -> Unit)? = null,
    inputChecks: ((String) -> Boolean)? = null,
    startCheck: Boolean = false,
) {
    val inputService = LocalTextInputService.current
    val isError = remember { mutableStateOf(false) }
    ConstraintLayout {
        val (refTextField, refMenu) = createRefs()
        Column(
            modifier = Modifier
                .padding(
                    bottom = if (isError.value) 0.dp else 16.dp
                )
                .constrainAs(refTextField) {}
        ) {
            TextField(
                value = value,
                onValueChange = { onValueChange(it) },
                modifier = modifier,
                enabled = enabled,
                textStyle = MaterialTheme.typography.subtitle1.copy(fontSize = 18.sp),
                label = { Text(text = stringResource(id = label)) },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    keyboardType = keyboardType,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    if (onDoneClick != null) {
                        onDoneClick()
                    } else {
                        inputService?.hideSoftwareKeyboard()
                    }
                }),
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
        PackageWalkDropdownMenu(
            expanded = expanded,
            listOfValues = listOfValues,
            onClickItem = {
                onValueChange(it)
                expanded.value = false
            },
            modifier = Modifier.constrainAs(refMenu) { top.linkTo(refTextField.bottom) })
    }
    SideEffect {
        if (startCheck && inputChecks != null) isError.value = inputChecks(value)
    }
}
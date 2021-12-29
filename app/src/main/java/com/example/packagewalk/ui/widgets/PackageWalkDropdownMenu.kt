package com.example.packagewalk.ui.widgets

import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.PopupProperties
import com.example.packagewalk.ui.widgets.text.TextSubtitle1

@Composable
fun PackageWalkDropdownMenu(
    expanded: MutableState<Boolean>,
    listOfValues: List<String>,
    onClickItem: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = { expanded.value = false },
        modifier = modifier,
        properties = PopupProperties(focusable = false)
    ) {
        listOfValues.forEach {
            DropdownMenuItem(onClick = { onClickItem(it) }) {
                TextSubtitle1(value = it)
            }
        }
    }
}
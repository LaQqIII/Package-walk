package com.example.packagewalk.ui.widgets

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.packagewalk.R
import com.example.packagewalk.ui.widgets.input_checks.emptyInputCheck

@Composable
fun InputMainDealInfo(
    from: MutableState<String>,
    to: MutableState<String>,
    date: MutableState<String>,
    cities: List<String>,
    startCheck: Boolean,
    @StringRes fromLabel: Int,
    @StringRes toLabel: Int,
    loadingCities: (String) -> Unit,
    @StringRes dateLabel: Int = R.string.whenn,
) {
    val isFromExpanded = remember { mutableStateOf(false) }
    val isToExpanded = remember { mutableStateOf(false) }
    TextFieldWithDropdownMenu(
        value = from.value,
        onValueChange = {
            from.value = it
            if (from.value.length >= 3) {
                loadingCities(from.value)
                isFromExpanded.value = true
            } else {
                isFromExpanded.value = false
            }
        },
        label = fromLabel,
        listOfValues = cities,
        expanded = isFromExpanded,
        modifier = Modifier.fillMaxWidth(),
        inputChecks = { emptyInputCheck(it) },
        startCheck = startCheck
    )
    TextFieldWithDropdownMenu(
        value = to.value,
        onValueChange = {
            to.value = it
            if (to.value.length >= 3) {
                loadingCities(to.value)
                isToExpanded.value = true
            } else {
                isToExpanded.value = false
            }
        },
        label = toLabel,
        listOfValues = cities,
        expanded = isToExpanded,
        modifier = Modifier.fillMaxWidth(),
        inputChecks = { emptyInputCheck(it) },
        startCheck = startCheck
    )
    PackageWalkTextFieldWithDateDialog(
        date = date,
        modifier = Modifier.fillMaxWidth(),
        inputChecks = { emptyInputCheck(it) },
        startCheck = startCheck,
        label = dateLabel
    )
}
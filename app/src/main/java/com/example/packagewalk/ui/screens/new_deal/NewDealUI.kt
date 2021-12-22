package com.example.packagewalk.ui.screens.new_deal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.packagewalk.R
import com.example.packagewalk.ui.screens.authorization.AuthorizationUI
import com.example.packagewalk.ui.widgets.PackageWalkButton
import com.example.packagewalk.ui.widgets.PackageWalkTopBar
import com.example.packagewalk.ui.widgets.TextFieldApp
import com.example.packagewalk.ui.widgets.text.TextSubtitle1

@Preview(showBackground = true)
@Composable
fun NewDealUI() {
    val from = remember { mutableStateOf("") }
    val to = remember { mutableStateOf("") }
    val data = remember { mutableStateOf("") }
    Scaffold(topBar = { PackageWalkTopBar(titleId = R.string.new_order) }) {
        Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.around_base))) {
            TextFieldApp(
                value = from.value,
                onValueChange = { from.value = it },
                onDoneClick = { /*TODO*/ },
                label = R.string.from,
                modifier = Modifier.fillMaxWidth()
            )
            TextFieldApp(
                value = to.value,
                onValueChange = { to.value = it },
                onDoneClick = { /*TODO*/ },
                label = R.string.to,
                modifier = Modifier.fillMaxWidth()
            )
            TextFieldApp(
                value = data.value,
                onValueChange = { data.value },
                onDoneClick = { /*TODO*/ },
                label = R.string.whenn,
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "",
                        modifier = Modifier.clickable { })
                })
            TextSubtitle1(value = stringResource(id = R.string.approximate_size))
            PackageWalkRadioButton()
            PackageWalkButton(
                stringId = R.string.create_order,
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.around_base))
            )
        }
    }
}

@Composable
private fun PackageWalkRadioButton() {
    val radioOptions = listOf("Маленькая", "Средняя", "Большая")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
    // Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
    Column(Modifier.selectableGroup()) {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = null // null recommended for accessibility with screenreaders
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.body1.merge(),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}
package com.example.packagewalk.ui.screens.new_deal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.packagewalk.R
import com.example.packagewalk.data.PackageSize
import com.example.packagewalk.ui.screens.new_deal.models.NewDealEventState.*
import com.example.packagewalk.ui.widgets.PackageWalkButton
import com.example.packagewalk.ui.widgets.PackageWalkTopBar
import com.example.packagewalk.ui.widgets.TextFieldApp
import com.example.packagewalk.ui.widgets.input_checks.emptyInputCheck
import com.example.packagewalk.ui.widgets.text.TextSubtitle1

@Preview(showBackground = true)
@Composable
fun NewDealUI() {
    val viewModel = hiltViewModel<NewDealViewModel>()
    val newDealState = viewModel.newDealEvent.observeAsState()
    when (newDealState.value) {
        CREATE -> {}
        CREATED -> {}
        NOT_CREATED -> {}
        ERROR -> {}
    }
    NewDealUI(
        from = viewModel.from,
        to = viewModel.to,
        data = viewModel.data,
        size = viewModel.size,
        createNewDeal = { viewModel.createNewDeal() },
        startCheck = viewModel.startCheck.value,
        loading = viewModel.loading.value
    )
}

@Composable
private fun NewDealUI(
    from: MutableState<String>,
    to: MutableState<String>,
    data: MutableState<String>,
    size: MutableState<PackageSize>,
    createNewDeal: () -> Unit,
    startCheck: Boolean,
    loading: Boolean
) {
    Scaffold(topBar = { PackageWalkTopBar(titleId = R.string.new_order) }) {
        Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.around_base))) {
            TextFieldApp(
                value = from.value,
                onValueChange = { from.value = it },
                onDoneClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
                label = R.string.from,
                inputChecks = { emptyInputCheck(it) },
                startCheck = startCheck
            )
            TextFieldApp(
                value = to.value,
                onValueChange = { to.value = it },
                onDoneClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
                label = R.string.to,
                inputChecks = { emptyInputCheck(it) },
                startCheck = startCheck
            )
            TextFieldApp(
                value = data.value,
                onValueChange = { data.value = it },
                onDoneClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
                label = R.string.whenn,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "",
                        modifier = Modifier.clickable { })
                },
                inputChecks = { emptyInputCheck(it) },
                startCheck = startCheck,
                keyboardType = KeyboardType.Number
            )
            TextSubtitle1(value = stringResource(id = R.string.approximate_size))
            PackageWalkRadioButton(size)
            PackageWalkButton(
                stringId = R.string.create_order,
                onClick = { createNewDeal() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.around_base)),
                loading = loading
            )
        }
    }
}

@Composable
private fun PackageWalkRadioButton(size: MutableState<PackageSize>) {
    val radioOptions = PackageSize.values()
    val (selectedOption, onOptionSelected) = size
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
                    onClick = null /* null recommended for accessibility with screenreaders */
                )
                Text(
                    text = text.description,
                    style = MaterialTheme.typography.body1.merge(),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}
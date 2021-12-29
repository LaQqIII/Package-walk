package com.example.packagewalk.ui.screens.new_deal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.packagewalk.R
import com.example.packagewalk.data.PackageSize
import com.example.packagewalk.ui.screens.new_deal.models.NewDealEventState.*
import com.example.packagewalk.ui.widgets.*
import com.example.packagewalk.ui.widgets.input_checks.emptyInputCheck
import com.example.packagewalk.ui.widgets.text.TextSubtitle1
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun NewDealUI(navigateToDeals: () -> Unit) {
//    AuthorizationUI {}
    val viewModel = hiltViewModel<NewDealViewModel>()
    when (viewModel.newDealEvent.value) {
        CREATED -> navigateToDeals()
        NOT_CREATED -> {}
        ERROR -> {}
    }
    NewDealUI(
        from = viewModel.from,
        to = viewModel.to,
        data = viewModel.data,
        size = viewModel.size,
        startCheck = viewModel.startCheck.value,
        loading = viewModel.loading.value,
        cities = viewModel.cities,
        loadingCities = { viewModel.loadingCities(it) },
        createNewDeal = { viewModel.createNewDeal() }
    )
}

@Composable
private fun NewDealUI(
    from: MutableState<String>,
    to: MutableState<String>,
    data: MutableState<String>,
    size: MutableState<PackageSize>,
    startCheck: Boolean,
    loading: Boolean,
    cities: List<String>,
    loadingCities: (String) -> Unit,
    createNewDeal: () -> Unit,
) {
    val isFromExpanded = remember { mutableStateOf(false) }
    val isToExpanded = remember { mutableStateOf(false) }
    Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.around_base))) {
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
            listOfValues = cities,
            expanded = isFromExpanded,
            modifier = Modifier.fillMaxWidth(),
            label = R.string.from,
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
            label = R.string.to,
            listOfValues = cities,
            expanded = isToExpanded,
            modifier = Modifier.fillMaxWidth(),
            inputChecks = { emptyInputCheck(it) },
            startCheck = startCheck
        )
        PackageWalkTextField(
            value = data.value,
            onValueChange = { if (it.length <= 8) data.value = it },
            modifier = Modifier.fillMaxWidth(),
            label = R.string.whenn,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "",
                    modifier = Modifier.clickable { }
                )
            },
            inputChecks = { emptyInputCheck(it) },
            startCheck = startCheck,
            keyboardType = KeyboardType.Number,
            visualTransformation = { dateTransformation(data.value) }
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
package com.example.packagewalk.ui.screens.find_deal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.packagewalk.R
import com.example.packagewalk.data.Deal
import com.example.packagewalk.ui.screens.find_deal.models.FindDealEventState
import com.example.packagewalk.ui.screens.find_deal.models.FindDealEventState.*
import com.example.packagewalk.ui.widgets.*
import com.example.packagewalk.ui.widgets.input_checks.emptyInputCheck
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun FindDealUI(navigateToDeal: (Deal.OpenDeal) -> Unit) {
    val viewModel = hiltViewModel<FindDealViewModel>()
    FindDealUI(
        from = viewModel.from,
        to = viewModel.to,
        data = viewModel.data,
        findDealState = viewModel.findDealEvent,
        deals = viewModel.deals,
        loadingDeals = { viewModel.loadingDeals() },
        navigateToDeal = navigateToDeal,
        loadingCities = { viewModel.loadingCities(it) },
        startCheck = viewModel.startCheck.value,
        cities = viewModel.cities
    )
}

@Composable
private fun FindDealUI(
    from: MutableState<String>,
    to: MutableState<String>,
    data: MutableState<String>,
    findDealState: State<FindDealEventState?>,
    deals: List<Deal.OpenDeal>,
    cities: List<String>,
    startCheck: Boolean,
    loadingDeals: () -> Unit,
    loadingCities: (String) -> Unit,
    navigateToDeal: (Deal.OpenDeal) -> Unit,
) {
    val isFromExpanded = remember { mutableStateOf(false) }
    val isToExpanded = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = dimensionResource(id = R.dimen.around_base),
                top = dimensionResource(id = R.dimen.around_base),
                end = dimensionResource(id = R.dimen.around_base),
                bottom = dimensionResource(id = R.dimen.height_top_bar)
            )
    ) {
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
            label = R.string.from,
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
            label = R.string.whenn,
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Number,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "",
                    modifier = Modifier.clickable {  }
                )
            },
            inputChecks = { emptyInputCheck(it) },
            startCheck = startCheck,
            visualTransformation = { dateTransformation(data.value) }
        )
        PackageWalkButton(
            stringId = R.string.find,
            onClick = { loadingDeals() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.around_base))
        )
        when (findDealState.value) {
            LOADING -> LoadingUI()
            LOADED -> ListDeals(deals, navigateToDeal)
            EMPTY -> {}
            ERROR -> {}
        }
    }
}
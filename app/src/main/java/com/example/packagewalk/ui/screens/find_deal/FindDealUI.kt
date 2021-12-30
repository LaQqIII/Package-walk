package com.example.packagewalk.ui.screens.find_deal

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.packagewalk.R
import com.example.packagewalk.data.Deal
import com.example.packagewalk.ui.screens.find_deal.models.FindDealEventState
import com.example.packagewalk.ui.screens.find_deal.models.FindDealEventState.*
import com.example.packagewalk.ui.theme.PackageWalkTheme
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
        date = viewModel.data,
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
    date: MutableState<String>,
    findDealState: State<FindDealEventState?>,
    deals: List<Deal.OpenDeal>,
    cities: List<String>,
    startCheck: Boolean,
    loadingDeals: () -> Unit,
    loadingCities: (String) -> Unit,
    navigateToDeal: (Deal.OpenDeal) -> Unit
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
        PackageWalkTextFieldWithDateDialog(
            date = date,
            modifier = Modifier.fillMaxWidth(),
            inputChecks = { emptyInputCheck(it) },
            startCheck = startCheck
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

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
private fun Preview() {
    PackageWalkTheme {
        FindDealUI(
            from = mutableStateOf("Саров"),
            to = mutableStateOf("Нижний Новгород"),
            date = mutableStateOf("30122021"),
            findDealState = mutableStateOf(LOADED),
            deals = listOf(
                Deal.OpenDeal(
                    from = "Саров",
                    to = "Нижний довгород",
                    data = "30.12.2021"
                )
            ),
            cities = listOf(),
            startCheck = false,
            loadingDeals = { /*TODO*/ },
            loadingCities = {},
            navigateToDeal = {}
        )
    }
}
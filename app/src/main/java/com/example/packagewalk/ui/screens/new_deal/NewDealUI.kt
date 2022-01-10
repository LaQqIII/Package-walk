package com.example.packagewalk.ui.screens.new_deal

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.packagewalk.R
import com.example.packagewalk.data.enums.PackageSize
import com.example.packagewalk.ui.screens.new_deal.models.NewDealEventState.*
import com.example.packagewalk.ui.theme.PackageWalkTheme
import com.example.packagewalk.ui.widgets.InputMainDealInfo
import com.example.packagewalk.ui.widgets.PackageWalkButton
import com.example.packagewalk.ui.widgets.PackageWalkRadioButton
import com.example.packagewalk.ui.widgets.TextFieldCost
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
        date = viewModel.data,
        size = viewModel.size,
        startCheck = viewModel.startCheck.value,
        cost = viewModel.cost,
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
    date: MutableState<String>,
    size: MutableState<PackageSize>,
    cost: MutableState<Int>,
    startCheck: Boolean,
    loading: Boolean,
    cities: List<String>,
    loadingCities: (String) -> Unit,
    createNewDeal: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.around_base))
    ) {
        InputMainDealInfo(
            from = from,
            to = to,
            date = date,
            cities = cities,
            startCheck = startCheck,
            fromLabel = R.string.from,
            toLabel = R.string.to,
            loadingCities = { loadingCities(it) }
        )
        TextSubtitle1(value = stringResource(id = R.string.approximate_size))
        PackageWalkRadioButton(size)
        TextFieldCost(
            value = cost.value.toString(),
            onClickAddButton = { cost.value += 50 },
            onClickRemoveButton = { if (cost.value >= 50) cost.value -= 50 }
        )
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

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
private fun Preview() {
    PackageWalkTheme {
        NewDealUI(
            from = mutableStateOf("Саров"),
            to = mutableStateOf("Нижний Новгород"),
            date = mutableStateOf("30122021"),
            cost = mutableStateOf(100),
            size = mutableStateOf(PackageSize.MEDIUM),
            startCheck = false,
            loading = false,
            cities = listOf(),
            loadingCities = {}
        ) {

        }
    }
}
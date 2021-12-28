package com.example.packagewalk.ui.screens.find_deal

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.packagewalk.R
import com.example.packagewalk.data.Deal
import com.example.packagewalk.ui.screens.find_deal.models.FindDealEventState
import com.example.packagewalk.ui.screens.find_deal.models.FindDealEventState.*
import com.example.packagewalk.ui.theme.PackageWalkTheme
import com.example.packagewalk.ui.widgets.*

@Composable
fun FindDealUI(navigateToDeal: (Deal.OpenDeal) -> Unit) {
    val viewModel = hiltViewModel<FindDealViewModel>()
    Scaffold(topBar = { PackageWalkTopBar(titleId = R.string.screen_find_order) }) {
        FindDealUI(
            from = viewModel.from,
            to = viewModel.to,
            data = viewModel.data,
            findDealState = viewModel.findDealEvent,
            deals = viewModel.deals,
            loadingDeals = { viewModel.loadingDeals() },
            navigateToDeal = navigateToDeal
        )
    }
}

@Composable
private fun FindDealUI(
    from: MutableState<String>,
    to: MutableState<String>,
    data: MutableState<String>,
    findDealState: State<FindDealEventState?>,
    deals: List<Deal.OpenDeal>,
    loadingDeals: () -> Unit,
    navigateToDeal: (Deal.OpenDeal) -> Unit
) {
    val isFromError = remember { mutableStateOf(false) }
    val isToError = remember { mutableStateOf(false) }
    val isDataError = remember { mutableStateOf(false) }
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
        TextFieldApp(
            value = from.value,
            onValueChange = {
                isFromError.value = false
                from.value = it
            },
            onDoneClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth(),
            label = R.string.from
        )
        TextFieldApp(
            value = to.value,
            onValueChange = {
                isToError.value = false
                to.value = it
            },
            onDoneClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth(),
            label = R.string.to
        )
        TextFieldApp(
            value = data.value,
            onValueChange = {
                isDataError.value = false
                data.value = it
            },
            onDoneClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth(),
            label = R.string.whenn,
            keyboardType = KeyboardType.Number,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "",
                    modifier = Modifier.clickable { })
            }
        )
        PackageWalkButton(
            stringId = R.string.find,
            onClick = {
                isFromError.value = from.value.isEmpty()
                isToError.value = to.value.isEmpty()
                isDataError.value = data.value.isEmpty()
                loadingDeals()
            },
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

@Composable
private fun ListDeals(deals: List<Deal.OpenDeal>, navigateToDetail: (Deal.OpenDeal) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(deals) { deal ->
            DealCard(deal = deal, onClick = { navigateToDetail(deal) })
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
            data = mutableStateOf("31.12.2021"),
            findDealState = mutableStateOf(LOADED),
            deals = listOf(),
            loadingDeals = {},
            navigateToDeal = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ListDealsPreview() {
    PackageWalkTheme {
        ListDeals(deals = listOf()) {}
    }
}
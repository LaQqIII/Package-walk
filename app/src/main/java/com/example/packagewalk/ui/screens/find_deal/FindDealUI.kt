package com.example.packagewalk.ui.screens.find_deal

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.TouchApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.packagewalk.R
import com.example.packagewalk.data.documents.Deal
import com.example.packagewalk.extensions.allPadding
import com.example.packagewalk.ui.screens.find_deal.models.FindDealEventState.*
import com.example.packagewalk.ui.widgets.InputMainDealInfo
import com.example.packagewalk.ui.widgets.ListDeals
import com.example.packagewalk.ui.widgets.LoadingUI
import com.example.packagewalk.ui.widgets.PackageWalkButton
import com.example.packagewalk.ui.widgets.text.TextSubtitle1
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun FindDealUI(navigateToDeal: (Deal) -> Unit) {
    val viewModel = hiltViewModel<FindDealViewModel>()
    when (viewModel.findDealEvent.value) {
        FILTER -> FilterUI(
            from = viewModel.from,
            to = viewModel.to,
            date = viewModel.data,
            cities = viewModel.cities,
            startCheck = viewModel.startCheck.value,
            loadingDeals = { viewModel.loadingDeals() },
            loadingCities = { viewModel.loadingCities(it) }
        )
        SEARCH -> SearchUI(
            from = viewModel.from.value,
            to = viewModel.to.value,
            date = viewModel.data.value,
            deals = viewModel.deals,
            navigateToDetail = { navigateToDeal(it) },
            loading = viewModel.loading.value,
            showFilter = { viewModel.findDealEvent.value = FILTER }
        )
        ERROR -> {}
    }
}

@Composable
private fun FilterUI(
    from: MutableState<String>,
    to: MutableState<String>,
    date: MutableState<String>,
    cities: List<String>,
    startCheck: Boolean,
    loadingDeals: () -> Unit,
    loadingCities: (String) -> Unit
) {
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
        InputMainDealInfo(
            from = from,
            to = to,
            date = date,
            cities = cities,
            startCheck = startCheck,
            fromLabel = R.string.from_find,
            toLabel = R.string.to_find,
            dateLabel = R.string.when_find,
            loadingCities = { loadingCities(it) }
        )
        PackageWalkButton(
            stringId = R.string.find,
            onClick = { loadingDeals() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.around_base))
        )
    }
}

@Composable
private fun SearchUI(
    from: String,
    to: String,
    date: String,
    deals: List<Deal>,
    navigateToDetail: (Deal) -> Unit,
    loading: Boolean,
    showFilter: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .allPadding()
    ) {
        OutlinedButton(onClick = { showFilter() }) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.TouchApp,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(32.dp)
                )
                Column {
                    Row {
                        TextSubtitle1(value = from)
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = ""
                        )
                        TextSubtitle1(value = to)
                    }
                    TextSubtitle1(value = date, modifier = Modifier.padding(top = 8.dp))
                }
            }
        }
        if (loading) {
            LoadingUI()
        } else if (!loading && deals.isEmpty()) {
            TextSubtitle1(
                value = stringResource(id = R.string.no_find_deal),
                modifier = Modifier
                    .fillMaxWidth()
                    .allPadding()
            )
        } else {
            ListDeals(deals = deals, navigateToDetail = navigateToDetail)
        }
    }
}
package com.example.packagewalk.ui.screens.deals

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.packagewalk.R
import com.example.packagewalk.data.Deal
import com.example.packagewalk.extensions.allPadding
import com.example.packagewalk.ui.screens.deals.models.DealsEventState.*
import com.example.packagewalk.ui.theme.PackageWalkTheme
import com.example.packagewalk.ui.widgets.LoadingUI
import com.example.packagewalk.ui.widgets.PackageWalkButton
import com.example.packagewalk.ui.widgets.PackageWalkTopBar
import com.example.packagewalk.ui.widgets.RowDeal
import com.example.packagewalk.ui.widgets.text.TextSubtitle1

@Composable
fun DealsUI(navigateToDetail: () -> Unit) {
    val viewModel = hiltViewModel<DealsViewModel>()
    val dealsState = viewModel.dealsEvent.observeAsState()
    Scaffold(topBar = { PackageWalkTopBar(titleId = R.string.screen_progress) }) {
        when (dealsState.value) {
            CREATE -> viewModel.loadingDeals()
            LOADING -> LoadingUI()
            LOADED -> DealsUI(viewModel.deals, navigateToDetail)
            EMPTY -> EmptyUI()
            ERROR -> {}
        }
    }
}

@Composable
private fun DealsUI(deals: List<Deal>, navigateToDetail: () -> Unit) {
    LazyColumn {
        items(deals) { deal ->
            RowDeal(deal = deal, onClick = navigateToDetail)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyUI() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .allPadding()
    ) {
        TextSubtitle1(
            value = stringResource(id = R.string.empty_deals),
            modifier = Modifier.fillMaxWidth()
        )
        TextSubtitle1(
            value = stringResource(id = R.string.try_app_now),
            modifier = Modifier.fillMaxWidth()
        )
        PackageWalkButton(
            stringId = R.string.send_deal,
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.around_base))
        )
        PackageWalkButton(
            stringId = R.string.find_deal,
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.around_base))
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    PackageWalkTheme {
        DealsUI(
            deals = listOf(
                Deal("Саров", "Нижний Новгород", "12.12"),
                Deal("Саров", "Нижний Новгород", "12.12"),
                Deal("Саров", "Нижний Новгород", "12.12"),
                Deal("Саров", "Нижний Новгород", "12.12")
            )
        ) {}
    }
}
package com.example.packagewalk.ui.screens.deals

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.packagewalk.R
import com.example.packagewalk.data.Deal
import com.example.packagewalk.ui.screens.deals.models.DealsEventState.*
import com.example.packagewalk.ui.theme.PackageWalkTheme
import com.example.packagewalk.ui.widgets.LoadingUI
import com.example.packagewalk.ui.widgets.PackageWalkTopBar
import com.example.packagewalk.ui.widgets.RowDeal

@Preview(showBackground = true)
@Composable
fun DealsUI() {
    val viewModel = hiltViewModel<DealsViewModel>()
    val dealsState = viewModel.dealsEvent.observeAsState()
    Scaffold(topBar = { PackageWalkTopBar(titleId = R.string.screen_progress) }) {
        when (dealsState.value) {
            CREATE -> viewModel.loadingDeals()
            LOADING -> LoadingUI()
            LOADED -> DealsUI(viewModel.deals)
            EMPTY -> {}
            ERROR -> {}
        }
    }
}

@Composable
private fun DealsUI(deals: List<Deal>) {
    LazyColumn {
        items(deals) { deal ->
            RowDeal(deal = deal)
        }
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
        )
    }
}
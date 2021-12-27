package com.example.packagewalk.ui.screens.deals

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
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
fun DealsUI(
    navigateToDetail: (Deal) -> Unit,
    navigateToNewDeal: () -> Unit,
    navigateToFindDeal: () -> Unit
) {
    val viewModel = hiltViewModel<DealsViewModel>()
    Scaffold(topBar = { PackageWalkTopBar(titleId = R.string.screen_progress) }) {
        when (viewModel.dealsEvent.value) {
            CREATE -> viewModel.loadingDeals()
            LOADING -> LoadingUI()
            LOADED -> DealsUI(viewModel.deals, navigateToDetail)
            EMPTY -> EmptyUI(navigateToNewDeal, navigateToFindDeal)
            ERROR -> {}
        }
    }
}

@Composable
private fun DealsUI(deals: List<Deal>, navigateToDetail: (Deal) -> Unit) {
    LazyColumn {
        items(deals) { deal ->
            RowDeal(deal = deal, onClick = { navigateToDetail(deal) })
        }
    }
}

@Composable
private fun EmptyUI(
    navigateToNewDeal: () -> Unit,
    navigateToFindDeal: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .allPadding()
    ) {
        TextSubtitle1(
            value = stringResource(id = R.string.empty_deals),
            modifier = Modifier.fillMaxWidth()
        )
        PackageWalkButton(
            stringId = R.string.send_deal,
            onClick = navigateToNewDeal,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.around_base))
        )
        PackageWalkButton(
            stringId = R.string.find_deal,
            onClick = navigateToFindDeal,
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
        DealsUI(deals = listOf()) {}
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyUIPreview() {
    PackageWalkTheme {
        EmptyUI(navigateToNewDeal = { /*TODO*/ }) {

        }
    }
}
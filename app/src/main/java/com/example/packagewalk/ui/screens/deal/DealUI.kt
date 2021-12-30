package com.example.packagewalk.ui.screens.deal

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.packagewalk.R
import com.example.packagewalk.data.Deal
import com.example.packagewalk.data.User
import com.example.packagewalk.extensions.allPadding
import com.example.packagewalk.extensions.horizontalPadding
import com.example.packagewalk.ui.screens.deal.models.DealEventState.*
import com.example.packagewalk.ui.theme.PackageWalkTheme
import com.example.packagewalk.ui.widgets.PackageWalkButton
import com.example.packagewalk.ui.widgets.PackageWalkTopBar
import com.example.packagewalk.ui.widgets.RowInfo

@Composable
fun DealUI(
    deal: Deal?,
    navigateToDeals: () -> Unit,
    backPressed: () -> Unit
) {
    val viewModel = hiltViewModel<DealViewModel>()
    when (viewModel.state.value) {
        CLOSE_DEAL -> navigateToDeals()
        CANCEL_DEAL -> navigateToDeals()
        ERROR -> {}
    }
    Scaffold(topBar = {
        PackageWalkTopBar(
            titleId = R.string.deal,
            hasBackArrow = true,
            onClickIcon = backPressed
        )
    }) {
        when (deal) {
            is Deal.OpenDeal -> DealUI(
                deal = deal,
                cancelDeal = { viewModel.cancelDeal(deal) },
                closeDeal = { viewModel.closeDeal(deal) },
                loading = viewModel.loading.value
            )
            is Deal.CloseDeal -> DealUI(deal = deal)
        }
    }
}

@Composable
private fun DealUI(
    deal: Deal.OpenDeal,
    cancelDeal: () -> Unit,
    closeDeal: () -> Unit,
    loading: Boolean
) {
    Column(
        modifier = Modifier.padding(dimensionResource(id = R.dimen.around_base)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val showContacts = remember { mutableStateOf(false) }
        RowInfo(caption = R.string.from, value = deal.from)
        RowInfo(caption = R.string.to, value = deal.to)
        RowInfo(caption = R.string.whenn, value = deal.data)
        if (showContacts.value) {
            RowInfo(caption = R.string.number_phone, value = deal.phoneNumber)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = dimensionResource(id = R.dimen.around_base)),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (deal.phoneNumber == User.phoneNumber) {
                PackageWalkButton(
                    stringId = R.string.cancel_deal,
                    onClick = cancelDeal,
                    modifier = Modifier
                        .fillMaxWidth()
                        .allPadding(),
                    loading = loading
                )
                PackageWalkButton(
                    stringId = R.string.close_deal,
                    onClick = closeDeal,
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalPadding(),
                    loading = loading
                )
            } else {
                PackageWalkButton(
                    stringId = R.string.show_contacts,
                    onClick = { showContacts.value = true },
                    modifier = Modifier.allPadding(),
                    enabled = !showContacts.value
                )
            }
        }
    }
}

@Composable
private fun DealUI(deal: Deal.CloseDeal) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.around_base)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RowInfo(caption = R.string.from, value = deal.from)
        RowInfo(caption = R.string.to, value = deal.to)
        RowInfo(caption = R.string.whenn, value = deal.data)
    }
}

@Preview(showBackground = true)
@Composable
private fun OpenDealPreview() {
    PackageWalkTheme {
        DealUI(
            deal = Deal.OpenDeal(from = "Саров", to = "Нижний довгород", data = "30.12.2021"),
            cancelDeal = { /*TODO*/ },
            closeDeal = { /*TODO*/ },
            loading = false
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CloseDealPreview() {
    PackageWalkTheme {
        DealUI(deal = Deal.CloseDeal(from = "Саров", to = "Нижний довгород", data = "30.12.2021"))
    }
}
package com.example.packagewalk.ui.screens.deal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
import com.example.packagewalk.ui.screens.deal.models.DealEventState.CANCEL_DEAL
import com.example.packagewalk.ui.screens.deal.models.DealEventState.ERROR
import com.example.packagewalk.ui.widgets.PackageWalkButton
import com.example.packagewalk.ui.widgets.PackageWalkTopBar
import com.example.packagewalk.ui.widgets.RowInfo

@Composable
fun DealUI(deal: Deal, backPressed: () -> Unit) {
    val viewModel = hiltViewModel<DealViewModel>()
    val showContacts = remember { mutableStateOf(false) }
    when (viewModel.state.value) {
        CANCEL_DEAL -> {}
        ERROR -> {}
    }
    Scaffold(topBar = {
        PackageWalkTopBar(
            titleId = R.string.deal,
            hasBackArrow = true,
            onClickIcon = backPressed
        )
    }) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.around_base)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RowInfo(caption = R.string.from, value = deal.from)
            RowInfo(caption = R.string.to, value = deal.to)
            RowInfo(caption = R.string.whenn, value = deal.data)
            if (showContacts.value) {
                RowInfo(caption = R.string.number_phone, value = deal.phoneNumber)
            }
            if (deal.phoneNumber == User.phoneNumber && deal.isOpen) {
                PackageWalkButton(
                    stringId = R.string.cancel_deal,
                    onClick = { viewModel.cancelDeal(deal) },
                    modifier = Modifier.allPadding(),
                    loading = viewModel.loading.value
                )
            } else if (deal.isOpen) {
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

@Preview(showBackground = true)
@Composable
private fun Preview() {
}
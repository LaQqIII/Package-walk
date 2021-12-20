package com.example.packagewalk.ui.screens.find_deal

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.packagewalk.R
import com.example.packagewalk.data.Deal
import com.example.packagewalk.ui.screens.find_deal.models.FindDealEventState
import com.example.packagewalk.ui.screens.find_deal.models.FindDealEventState.*
import com.example.packagewalk.ui.theme.PackageWalkTheme
import com.example.packagewalk.ui.widgets.PackageWalkButton
import com.example.packagewalk.ui.widgets.PackageWalkTopBar
import com.example.packagewalk.ui.widgets.RowDeal
import com.example.packagewalk.ui.widgets.TextFieldApp
import com.example.packagewalk.ui.widgets.text.TextSubtitle1

@Composable
fun FindDealUI() {
    val viewModel = hiltViewModel<FindDealViewModel>()
    val findDealState = viewModel.findDealEvent.observeAsState()
    Scaffold(topBar = { PackageWalkTopBar(titleId = R.string.screen_find_order) }) {
        FindDealUI(
            from = viewModel.from,
            to = viewModel.to,
            data = viewModel.data,
            findDealState = findDealState,
            deals = viewModel.deals,
            loadingDeals = { viewModel.loadingDeals() }
        )
    }
}

@Composable
private fun FindDealUI(
    from: MutableState<String>,
    to: MutableState<String>,
    data: MutableState<String>,
    findDealState: State<FindDealEventState?>,
    deals: List<Deal>,
    loadingDeals: () -> Unit
) {
    val isFromError = remember { mutableStateOf(false) }
    val isToError = remember { mutableStateOf(false) }
    val isDataError = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.around_base))
    ) {
        TextFieldApp(
            value = from.value,
            onValueChange = {
                isFromError.value = false
                from.value = it
            },
            onDoneClick = { /*TODO*/ },
            label = R.string.from,
            modifier = Modifier.fillMaxWidth(),
            isError = isFromError.value
        )
        TextFieldApp(
            value = to.value,
            onValueChange = {
                isToError.value = false
                to.value = it
            },
            onDoneClick = { /*TODO*/ },
            label = R.string.to,
            modifier = Modifier.fillMaxWidth(),
            isError = isToError.value
        )
        TextFieldApp(
            value = data.value,
            onValueChange = {
                isDataError.value = false
                data.value = it
            },
            onDoneClick = { /*TODO*/ },
            label = R.string.whenn,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "",
                    modifier = Modifier.clickable { })
            },
            keyboardType = KeyboardType.Number,
            isError = isDataError.value
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
            LOADING -> {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensionResource(id = R.dimen.around_base)),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            LOADED -> ListDeals(deals)
            EMPTY -> {}
            ERROR -> {}
        }
    }
}

@Composable
private fun ListDeals(deals: List<Deal>) {
    LazyColumn {
        items(deals) { RowDeal(deal = it) }
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
            deals = listOf()
        ) {

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ListDealsPreview() {
    PackageWalkTheme {
        ListDeals(deals = listOf(Deal("Саров", "Нижний Новгород", "31.12.2021")))
    }
}
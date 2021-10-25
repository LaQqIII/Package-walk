package com.example.packagewalk.presentation.screens.createorder

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.packagewalk.presentation.widgets.OutlineTextFieldWithErrorView
import timber.log.Timber

private const val TAG_SCREEN = "create new order"

@Composable
fun CreateOrder(viewModel: CreateOrderViewModel, navigateToScreenAuthorization: () -> Unit) {

    Timber.d("$TAG_SCREEN !@#")

    val userLoggedIn by viewModel.userLoggedIn.collectAsState(false)

    if (userLoggedIn) {
        val from by viewModel.from
        val to by viewModel.to
        CreateOrder(from, to)
    } else {
        navigateToScreenAuthorization()
    }
}

@Composable
private fun CreateOrder(from: String, to: String) {

    Timber.d("Rendering screen for create new order !@# $TAG_SCREEN")

    OutlineTextFieldWithErrorView(value = from, onValueChange = {})

    OutlineTextFieldWithErrorView(value = to, onValueChange = {})
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
}
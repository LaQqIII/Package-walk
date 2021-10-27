package com.example.packagewalk.presentation.screens.createorder

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.packagewalk.R
import com.example.packagewalk.presentation.widgets.*
import timber.log.Timber

private const val TAG_SCREEN = "create new order"

@Composable
fun CreateOrder(viewModel: CreateOrderViewModel, navigateToScreenAuthorization: () -> Unit) {

    Timber.d("$TAG_SCREEN !@#")

    val userLoggedIn by viewModel.userLoggedIn.collectAsState(false)

    if (userLoggedIn) {
        val (from, setFrom) = viewModel.from
        val (to, setTo) = viewModel.to
        CreateOrder(from, setFrom, to, setTo, { viewModel.createOrder() })
    } else {
        navigateToScreenAuthorization()
    }
}

@Composable
private fun CreateOrder(
    from: String,
    setFrom: (String) -> Unit,
    to: String,
    setTo: (String) -> Unit,
    createOrder: () -> Unit
) {

    Timber.d("Rendering screen for create new order !@# $TAG_SCREEN")
    Scaffold(topBar = {
        PackageWalkTopBar(
            titleId = R.string.new_order,
            icon = null
        ) {}
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.around_base))
        ) {

            // TODO: 27.10.2021 Вынести эту конструкцию в отдельную ф-ю
            TextH6(stringId = R.string.from)

            StandartSpacer()

            OutlineTextFieldWithErrorView(
                value = from,
                onValueChange = { setFrom(it) },
                modifier = Modifier.fillMaxWidth()
            )

            StandartSpacer()

            TextH6(stringId = R.string.to)

            StandartSpacer()

            OutlineTextFieldWithErrorView(
                value = to,
                onValueChange = { setTo(it) },
                modifier = Modifier.fillMaxWidth()
            )

            // TODO: 27.10.2021 Доделать верстку экрана

            // TODO: 27.10.2021 Сделать функция, которая распологает эл-ты внизу
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom
            ) {
                PackageWalkButton(
                    stringId = R.string.create_order,
                    onClick = { createOrder() }
                )
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    CreateOrder(
        from = "Саров",
        setFrom = {},
        to = "Нижний Новгород",
        setTo = {},
        createOrder = {}
    )
}
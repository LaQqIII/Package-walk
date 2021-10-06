package com.example.packagewalk.ui.screens.authorization.mobileAuth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import com.example.packagewalk.R
import com.example.packagewalk.ui.PackageWalkTopBar
import com.example.packagewalk.ui.widgets.StandartButton
import com.example.packagewalk.ui.widgets.StandartMobileTextField
import com.example.packagewalk.ui.widgets.StandartSpacer
import com.example.packagewalk.ui.widgets.TextH6
import timber.log.Timber

private const val TAG_SCREEN = "Авторизация по телефону"

/**
 * Экран, который отвечает за авторизацию пользователь с помощью мобильного телефона
 */
@ExperimentalComposeUiApi
@Composable
fun MobileAuthorizationScreen(viewModel: MobileAuthorizationViewModel, navigateBack: () -> Unit) {

    Timber.d("Отрисовка экрана авторизации по телефону $TAG_SCREEN")

    val keyboardController = LocalSoftwareKeyboardController.current

    val phoneNumber by viewModel.phoneNumber
    val phoneNumberIsValid by viewModel.phoneNumberIsValid

    Scaffold(topBar = {
        PackageWalkTopBar(
            titleId = R.string.registration,
            icon = Icons.Default.ArrowBack,
            onClickIcon = navigateBack
        )
    }) {
        Column(
            modifier = Modifier.padding(dimensionResource(R.dimen.around_base)),
            verticalArrangement = Arrangement.Center
        ) {
            TextH6(stringId = R.string.number_phone)

            StandartSpacer()

            StandartMobileTextField(
                value = phoneNumber,
                onValueChange = { viewModel.setPhoneNumber(it) },
                onDoneClick = { keyboardController?.hide() })

            StandartSpacer()

            StandartButton(
                onClick = { /*TODO*/ },
                stringId = R.string.get_code,
                enabled = phoneNumberIsValid
            )
        }
    }
}
package com.example.packagewalk.presentation.screens.authorization.mobileAuth

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import com.example.packagewalk.R
import com.example.packagewalk.presentation.widgets.PackageWalkTopBar
import com.example.packagewalk.presentation.widgets.PackageWalkButton
import com.example.packagewalk.presentation.widgets.PackageWalkMobileTextField
import com.example.packagewalk.presentation.widgets.StandartSpacer
import com.example.packagewalk.presentation.widgets.TextH6
import kotlinx.coroutines.DelicateCoroutinesApi
import timber.log.Timber

private const val TAG_SCREEN = "Авторизация по телефону"

/**
 * Экран, который отвечает за авторизацию пользователь с помощью мобильного телефона
 */
@DelicateCoroutinesApi
@ExperimentalComposeUiApi
@Composable
fun MobileAuthorizationScreen(
    viewModel: MobileAuthorizationViewModel,
    navigateBack: () -> Unit,
    navigateToScreenEnterCode: (String) -> Unit
) {
    Timber.d("Отрисовка экрана авторизации по телефону $TAG_SCREEN")

    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

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

            PackageWalkMobileTextField(
                value = phoneNumber,
                onValueChange = { viewModel.setPhoneNumber(it) },
                onDoneClick = { focusManager.clearFocus() })

            StandartSpacer()

            PackageWalkButton(
                onClick = {
                    viewModel.sendCode(context)
                    navigateToScreenEnterCode(phoneNumber)
                },
                stringId = R.string.get_code,
                enabled = phoneNumberIsValid
            )
        }
    }
}
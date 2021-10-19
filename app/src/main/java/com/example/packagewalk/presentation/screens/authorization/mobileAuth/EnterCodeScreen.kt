package com.example.packagewalk.presentation.screens.authorization.mobileAuth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.packagewalk.R
import com.example.packagewalk.presentation.widgets.PackageWalkTopBar
import com.example.packagewalk.presentation.widgets.*
import timber.log.Timber

private const val TAG_SCREEN = "Ввод проверочного кода"

@Composable
fun EnterCodeScreen(
    viewModel: EnterCodeViewModel,
    phoneNumber: String,
    navigateBack: () -> Unit,
    navigateToScreenProfile: () -> Unit
) {

    Timber.d("Отрисовка экрана ввода проверочного кода $TAG_SCREEN")

    val focusManager = LocalFocusManager.current

    val code by viewModel.code
    val codeIsValid by viewModel.codeIsValid
    val codeIsError by viewModel.codeIsError
    val userLoggedIn by viewModel.userLoggedIn

    if (userLoggedIn) {
        navigateToScreenProfile()
        return
    }

    Timber.d("!@# codeIsError=$codeIsError")

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
            TextH6(stringId = R.string.sms_code)

            Text(
                text = stringResource(R.string.send_on_number, phoneNumber),
                style = MaterialTheme.typography.body1
            )

            StandartSpacer()

            EnterCodeTextField(
                value = code,
                onValueChange = { viewModel.setCode(it) },
                onDoneClick = { focusManager.clearFocus() },
                isError = codeIsError
            )

            StandartSpacer()

            PackageWalkButton(
                stringId = R.string.continuee,
                onClick = { viewModel.signInWithCheckCode() },
                enabled = codeIsValid
            )

            StandartSpacer()

            PackageWalkTextButton(stringId = R.string.send_code_repeat, onClick = { /*TODO*/ })
        }
    }
}
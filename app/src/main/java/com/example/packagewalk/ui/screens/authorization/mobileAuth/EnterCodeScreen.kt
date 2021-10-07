package com.example.packagewalk.ui.screens.authorization.mobileAuth

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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.packagewalk.R
import com.example.packagewalk.ui.PackageWalkTopBar
import com.example.packagewalk.ui.widgets.StandartOutlinedTextField
import com.example.packagewalk.ui.widgets.StandartSpacer
import com.example.packagewalk.ui.widgets.TextH6
import timber.log.Timber

private const val TAG_SCREEN = "Ввод проверочного кода"

@Composable
fun EnterCodeScreen(viewModel: EnterCodeViewModel, navigateBack: () -> Unit, phoneNumber: String) {

    Timber.d("Отрисовка экрана ввода проверочного кода $TAG_SCREEN")

    val code by viewModel.code

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

            StandartOutlinedTextField(value = code, onValueChange = { viewModel.setCode(it) })
        }
    }
}
package com.example.packagewalk.ui.screens.authorization.mobileAuth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.example.packagewalk.R
import com.example.packagewalk.ui.PackageWalkTopBar
import com.example.packagewalk.ui.widgets.PackageWalkButton
import com.example.packagewalk.ui.widgets.PackageWalkTextButton
import com.example.packagewalk.ui.widgets.StandartSpacer
import com.example.packagewalk.ui.widgets.TextH6
import timber.log.Timber

private const val TAG_SCREEN = "Ввод проверочного кода"

@Composable
fun EnterCodeScreen(viewModel: EnterCodeViewModel, navigateBack: () -> Unit, phoneNumber: String) {

    Timber.d("Отрисовка экрана ввода проверочного кода $TAG_SCREEN")
    val focusManager = LocalFocusManager.current

    val code by viewModel.code
    val codeIsValid by viewModel.codeIsValid

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
                onDoneClick = { focusManager.clearFocus() })

            StandartSpacer()

            PackageWalkButton(
                stringId = R.string.continuee,
                onClick = { /*TODO*/ },
                enabled = codeIsValid
            )

            StandartSpacer()

            PackageWalkTextButton(stringId = R.string.send_code_repeat, onClick = { /*TODO*/ })
        }
    }
}

@Composable
private fun EnterCodeTextField(
    value: String,
    onValueChange: (String) -> Unit,
    onDoneClick: () -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = Modifier.fillMaxWidth(),
        textStyle = MaterialTheme.typography.subtitle1.copy(fontSize = 18.sp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = { onDoneClick() })
    )
}
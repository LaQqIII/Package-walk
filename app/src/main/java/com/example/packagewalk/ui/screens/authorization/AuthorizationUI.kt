package com.example.packagewalk.ui.screens.authorization

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.packagewalk.R
import com.example.packagewalk.ui.screens.authorization.models.AuthenticationState
import com.example.packagewalk.ui.screens.authorization.models.AuthorizationEventState.*
import com.example.packagewalk.ui.theme.PackageWalkTheme
import com.example.packagewalk.ui.widgets.PackageWalkButton
import com.example.packagewalk.ui.widgets.PackageWalkTopBar
import com.example.packagewalk.ui.widgets.TextFieldApp
import com.example.packagewalk.ui.widgets.mobileNumberTransformation

/**
 * Экран проверяет залогинен пользователь или нет.
 * Если нет, то будет показан экран авторизации
 * Если залогинен, то будет показан content.
 */
@Composable
fun AuthorizationUI(content: @Composable (() -> Unit)) {
    val viewModel = hiltViewModel<AuthorizationViewModel>()
    val authenticationState = viewModel.authenticationState.observeAsState()
    when (authenticationState.value) {
        AuthenticationState.AUTHENTICATED -> content()
        AuthenticationState.UNAUTHENTICATED -> MobileAuthorizationUI(viewModel)
    }
}

@Composable
private fun MobileAuthorizationUI(viewModel: AuthorizationViewModel) {
    val context = LocalContext.current
    val authorizationEvent = viewModel.authorizationEvent.observeAsState()
    val codeInCorrect by viewModel.codeInCorrect
    when (authorizationEvent.value) {
        CODE_SEND -> {}
        CODE_NOT_SEND -> {}
        CODE_CORRECT -> viewModel.signIn()
        USER_LOGIN -> {}
        USER_FAILED_LOGIN -> {}
    }
    MobileAuthorizationUI(
        sendCode = { viewModel.sendCode(it, context) },
        checkCode = { viewModel.checkCode(it) },
        codeIncorrect = codeInCorrect
    )
}

@Composable
private fun MobileAuthorizationUI(
    sendCode: (phoneNumber: String) -> Unit,
    checkCode: (code: String) -> Unit,
    codeIncorrect: Boolean
) {
    val phone = remember { mutableStateOf("") }
    val codeSend = remember { mutableStateOf(false) }
    val code = remember { mutableStateOf("") }
    Scaffold(topBar = {
        PackageWalkTopBar(titleId = R.string.entrance, hasBackArrow = true) {}
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.around_base))
        ) {
            TextFieldApp(
                value = phone.value,
                onValueChange = { phone.value = it },
                onDoneClick = { /*TODO*/ },
                label = R.string.number_phone,
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = { mobileNumberTransformation(it.text) },
                enabled = !codeSend.value,
                keyboardType = KeyboardType.Number
            )
            if (codeSend.value) {
                TextFieldApp(
                    value = code.value,
                    onValueChange = { code.value = it },
                    onDoneClick = { /*TODO*/ },
                    label = R.string.sms_code,
                    isError = codeIncorrect,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensionResource(id = R.dimen.around_base)),
                    keyboardType = KeyboardType.Number,
                    error = R.string.error_code
                )
            }
            PackageWalkButton(
                stringId = if (codeSend.value) R.string.check_code else R.string.send_code,
                onClick = {
                    if (codeSend.value) {
                        checkCode(code.value)
                    } else {
                        sendCode(phone.value)
                        codeSend.value = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.around_base))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    PackageWalkTheme {
        MobileAuthorizationUI(sendCode = {}, checkCode = {}, codeIncorrect = false)
    }
}
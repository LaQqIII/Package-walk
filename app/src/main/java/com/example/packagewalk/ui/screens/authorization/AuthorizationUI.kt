package com.example.packagewalk.ui.screens.authorization

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.packagewalk.R
import com.example.packagewalk.ui.screens.authorization.models.AuthenticationState
import com.example.packagewalk.ui.screens.authorization.models.AuthorizationEventState.*
import com.example.packagewalk.ui.widgets.PackageWalkButton
import com.example.packagewalk.ui.widgets.PackageWalkTextField
import com.example.packagewalk.ui.widgets.PackageWalkTopBar
import com.example.packagewalk.ui.widgets.mobileNumberTransformation

/**
 * Экран проверяет залогинен пользователь или нет.
 * Если нет, то будет показан экран авторизации
 * Если залогинен, то будет показан content.
 */
@Composable
fun AuthorizationUI(showAuthorization: Boolean = true, content: @Composable () -> Unit) {
    val viewModel = hiltViewModel<AuthorizationViewModel>()
    val authenticationState = viewModel.authenticationState.observeAsState()
    when (authenticationState.value) {
        AuthenticationState.AUTHENTICATED -> content()
        AuthenticationState.UNAUTHENTICATED -> if (showAuthorization) {
            MobileAuthorizationUI(viewModel)
        } else {
            content()
        }
    }
}

@Composable
private fun MobileAuthorizationUI(viewModel: AuthorizationViewModel) {
    val context = LocalContext.current
    when (viewModel.authorizationEvent.value) {
        CODE_SEND -> {}
        CODE_NOT_SEND -> {}
        CODE_CORRECT -> viewModel.signIn()
        USER_LOGIN -> {}
        USER_FAILED_LOGIN -> {}
        CREATE -> {}
    }
    MobileAuthorizationUI(
        phoneNumber = viewModel.phoneNumber,
        name = viewModel.name,
        sendCode = { viewModel.sendCode(context) }
    ) { viewModel.checkCode(it) }
}

@Composable
private fun MobileAuthorizationUI(
    phoneNumber: MutableState<String>,
    name: MutableState<String>,
    sendCode: () -> Unit,
    checkCode: (code: String) -> Unit
) {
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
            PackageWalkTextField(
                value = name.value,
                onValueChange = { name.value = it },
                onDoneClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
                label = R.string.what_your_name
            )
            PackageWalkTextField(
                value = phoneNumber.value,
                onValueChange = { phoneNumber.value = it },
                onDoneClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
                label = R.string.number_phone,
                visualTransformation = { mobileNumberTransformation(it.text) },
                enabled = !codeSend.value,
                keyboardType = KeyboardType.Number
            )
            if (codeSend.value) {
                PackageWalkTextField(
                    value = code.value,
                    onValueChange = { code.value = it },
                    onDoneClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensionResource(id = R.dimen.around_base)),
                    label = R.string.sms_code,
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
                        sendCode()
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
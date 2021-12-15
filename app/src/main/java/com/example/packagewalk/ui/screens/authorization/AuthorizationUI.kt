package com.example.packagewalk.ui.screens.authorization

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.packagewalk.R
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
        AuthenticationState.AUTHENTICATED -> {
            content()
        }
        AuthenticationState.UNAUTHENTICATED -> {
            MobileAuthorizationUI()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MobileAuthorizationUI() {
    val phone = remember { mutableStateOf("") }
    val codeSend = remember { mutableStateOf(false) }
    val code = remember { mutableStateOf("") }
    Scaffold(topBar = {
        PackageWalkTopBar(titleId = R.string.entrance) {}
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
                visualTransformation = { mobileNumberTransformation(it.text) }
            )
            if (codeSend.value) {
                TextFieldApp(
                    value = code.value,
                    onValueChange = { code.value = it },
                    onDoneClick = { /*TODO*/ },
                    label = R.string.sms_code,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensionResource(id = R.dimen.around_base))
                )
            }
            PackageWalkButton(
                stringId = R.string.get_code,
                onClick = { codeSend.value = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.around_base)),
                enabled = !codeSend.value
            )
        }
    }
}
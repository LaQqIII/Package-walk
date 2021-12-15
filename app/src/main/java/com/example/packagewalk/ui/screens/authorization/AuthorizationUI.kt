package com.example.packagewalk.ui.screens.authorization

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel

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

@Composable
private fun MobileAuthorizationUI() {
    Text(text = "Не зашел")
}
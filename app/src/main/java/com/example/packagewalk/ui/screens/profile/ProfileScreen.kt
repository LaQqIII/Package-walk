package com.example.packagewalk.ui.screens.profile

import androidx.compose.runtime.Composable

@Composable
fun Profile(navigateToScreenAuthorization: () -> Unit) {

    // todo добавить проверку залогинен пользователь или нет
    if (true) {

    } else {
        navigateToScreenAuthorization()
    }

}
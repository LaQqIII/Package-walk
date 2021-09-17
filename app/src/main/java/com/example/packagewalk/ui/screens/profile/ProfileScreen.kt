package com.example.packagewalk.ui.screens.profile

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import timber.log.Timber

@Composable
fun Profile(navigateToScreenAuthorization: () -> Unit) {

    Timber.d("Отрисовка экрана профиля пользователя !@#")

    // todo добавить проверку залогинен пользователь или нет
    if (false) {
        Text(text = "4")
    } else {
        navigateToScreenAuthorization()
    }

}
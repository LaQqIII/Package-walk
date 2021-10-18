package com.example.packagewalk.presentation.screens.profile

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import timber.log.Timber

@Composable
fun Profile(viewModel: ProfileViewModel, navigateToScreenAuthorization: () -> Unit) {

    Timber.d("Отрисовка экрана профиля пользователя !@#")

    val userLoggedIn by viewModel.userLoggedIn.collectAsState(false)

    if (userLoggedIn) {
        Text(text = "Зашел")
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Выйти")
        }
    } else {
        Text(text = "Не зашел")
        //navigateToScreenAuthorization()
    }
}
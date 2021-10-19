package com.example.packagewalk.presentation.screens.profile

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.DelicateCoroutinesApi
import timber.log.Timber

@DelicateCoroutinesApi
@Composable
fun Profile(viewModel: ProfileViewModel, navigateToScreenAuthorization: () -> Unit) {

    Timber.d("Отрисовка экрана профиля пользователя !@#")

    val userLoggedIn by viewModel.userLoggedIn.collectAsState(false)

    if (userLoggedIn) {
        Text(text = "Зашел")
        Button(onClick = { viewModel.logOutProfile() }) {
            Text(text = "Выйти")
        }
    } else {
        navigateToScreenAuthorization()
    }
}
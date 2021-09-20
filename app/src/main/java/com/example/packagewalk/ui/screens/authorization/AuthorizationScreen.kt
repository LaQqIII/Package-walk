package com.example.packagewalk.ui.screens.authorization

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import timber.log.Timber

@Composable
fun Authorization(viewModel: AuthorizationViewModel) {

    Timber.d("Отрисовка экрана авторизации пользователя !@#")

    val numberPhone = remember { mutableStateOf("") }
    Column() {
        TextField(value = numberPhone.value, onValueChange = { numberPhone.value = it })
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Отправить код")
        }
    }
}
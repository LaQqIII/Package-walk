package com.example.packagewalk.ui.screens.authorization

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.packagewalk.R
import timber.log.Timber

@Composable
fun Authorization(
    navigateToScreenLoginIn: () -> Unit,
    navigateToScreenRegistration: () -> Unit
) {

    Timber.d("Отрисовка экрана выбора зайти или зарегистрироваться !@#")

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = { navigateToScreenRegistration() }) {
            Text(text = stringResource(R.string.register))
        }

        Button(onClick = { navigateToScreenLoginIn() }) {
            Text(text = stringResource(R.string.login))
        }

    }
}
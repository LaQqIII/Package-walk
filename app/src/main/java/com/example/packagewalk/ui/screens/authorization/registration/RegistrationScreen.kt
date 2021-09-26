package com.example.packagewalk.ui.screens.authorization.registration

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.packagewalk.R
import com.example.packagewalk.ui.widgets.StandartButton
import com.example.packagewalk.ui.widgets.StandartSpacer
import com.example.packagewalk.ui.widgets.TextH6
import timber.log.Timber

private const val TAG_SCREEN = "регистрация"

/**
 * Экран, который отвечает за следующее:
 * 1. выбор варианты регистрации в приложении
 * 2. регистрация в приложении
 */
@Composable
fun RegistrationScreen() {

    Timber.d("Отрисовка экрана регистрации !@# $TAG_SCREEN")

    Scaffold(topBar = {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) { TextH6(R.string.registration) }
    }) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StandartButton(onClick = { /*TODO*/ }, stringId = R.string.email)

            StandartSpacer()

            StandartButton(onClick = { /*TODO*/ }, stringId = R.string.number_phone)
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun RegistrationScreenPreview() {
    RegistrationScreen()
}
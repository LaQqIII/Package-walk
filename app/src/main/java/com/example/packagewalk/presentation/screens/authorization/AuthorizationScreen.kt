package com.example.packagewalk.presentation.screens.authorization

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.packagewalk.R
import com.example.packagewalk.presentation.PackageWalkTopBar
import com.example.packagewalk.presentation.widgets.PackageWalkButton
import com.example.packagewalk.presentation.widgets.PackageWalkOutlinedButton
import com.example.packagewalk.presentation.widgets.StandartSpacer
import timber.log.Timber

private const val TAG_SCREEN = "Авторизация"

/**
 * Отрисововывает экран, который показывает пользователю выбор
 * между входом и регистрацией в приложении
 * @param navigateToScreenLoginIn функция, отвечающая за переход на экран для входа
 * @param navigateToScreenRegistration функция, отвечающая за переход на экран регистрации
 * @param navigateBack функция, отвечающая за переход назад
 */
@Composable
fun Authorization(
    navigateToScreenLoginIn: () -> Unit,
    navigateToScreenRegistration: () -> Unit,
    navigateBack: () -> Unit
) {
    //Timber.d("Отрисовка экрана выбора зайти или зарегистрироваться !@# $TAG_SCREEN")

    Scaffold(topBar = {
        PackageWalkTopBar(
            titleId = null,
            icon = Icons.Default.Close,
            onClickIcon = navigateBack
        )
    }) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PackageWalkButton(onClick = navigateToScreenRegistration, stringId = R.string.register)

            StandartSpacer()

            PackageWalkOutlinedButton(onClick = navigateToScreenLoginIn, stringId = R.string.login)

            StandartSpacer()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AuthorizationPreview() {
    Authorization({}, {}, {})
}
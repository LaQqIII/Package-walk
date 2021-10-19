package com.example.packagewalk.presentation.screens.authorization.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.packagewalk.R
import com.example.packagewalk.presentation.widgets.PackageWalkTopBar
import com.example.packagewalk.presentation.widgets.PackageWalkButton
import com.example.packagewalk.presentation.widgets.StandartSpacer
import timber.log.Timber

private const val TAG_SCREEN = "регистрация"

/**
 * Экран, который отвечает за следующее:
 * 1. выбор варианты регистрации в приложении
 * 2. регистрация в приложении
 * @param navigateBack функция, возвращающая на предыдущий экран
 * @param navigateToScreenMobileAuthorization функция, которая отвечает за переход
 * на экран авторизации с помощью телефона
 */
@Composable
fun RegistrationScreen(
    navigateBack: () -> Unit,
    navigateToScreenMobileAuthorization: () -> Unit
) {

    Timber.d("Отрисовка экрана регистрации !@# $TAG_SCREEN")

    Scaffold(topBar = {
        PackageWalkTopBar(
            titleId = R.string.registration,
            icon = Icons.Default.ArrowBack,
            onClickIcon = navigateBack
        )
    }) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PackageWalkButton(onClick = { /*TODO*/ }, stringId = R.string.email)

            StandartSpacer()

            PackageWalkButton(
                onClick = { navigateToScreenMobileAuthorization() },
                stringId = R.string.number_phone
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun RegistrationScreenPreview() {
    RegistrationScreen({}, {})
}
package com.example.packagewalk.ui.screens.authorization

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.packagewalk.R
import com.example.packagewalk.data.authorization.Authorization
import com.example.packagewalk.ui.widgets.StandartButton
import timber.log.Timber

private const val TAG_SCREEN = "Авторизация"

/**
 * Отрисововывает экран, который показывает пользователю выбор
 * между входом и регистрацией в приложении
 * @param navigateToScreenLoginIn функция, отвечающая за переход на экран для входа
 * @param navigateToScreenRegistration функция, отвечающая за переход на экран регистрации
 */
@Composable
fun Authorization(
    navigateToScreenLoginIn: () -> Unit,
    navigateToScreenRegistration: () -> Unit
) {

    Timber.d("Отрисовка экрана выбора зайти или зарегистрироваться !@# $TAG_SCREEN")

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        StandartButton(onClick = navigateToScreenRegistration, stringId = R.string.register)

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.between_the_lines_base)))

        StandartButton(onClick = navigateToScreenLoginIn, stringId = R.string.login)

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.between_the_lines_base)))

    }
}

@Preview(showBackground = true)
@Composable
private fun AuthorizationPreview() {
    Authorization({}, {})
}
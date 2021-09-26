package com.example.packagewalk.ui.screens.authorization

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.packagewalk.R
import com.example.packagewalk.ui.widgets.StandartButton
import com.example.packagewalk.ui.widgets.StandartOutlinedButton
import com.example.packagewalk.ui.widgets.StandartSpacer
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
    Timber.d("Отрисовка экрана выбора зайти или зарегистрироваться !@# $TAG_SCREEN")

    Scaffold(topBar = {
        // TopAppBar(actions = {})
    })
//    {
//        Row(modifier = Modifier.fillMaxWidth()) {
//            IconButton(onClick = { navigateBack() }) {
//                Icon(imageVector = Icons.Default.Close, contentDescription = "")
//            }
//        }
//    }) {
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StandartButton(onClick = navigateToScreenRegistration, stringId = R.string.register)

            StandartSpacer()

            StandartOutlinedButton(onClick = navigateToScreenLoginIn, stringId = R.string.login)

            StandartSpacer()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AuthorizationPreview() {
    Authorization({}, {}, {})
}
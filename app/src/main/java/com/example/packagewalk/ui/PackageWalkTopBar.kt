package com.example.packagewalk.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.packagewalk.R
import com.example.packagewalk.ui.widgets.TextH6

/**
 * Отрисовывает верхнюю панель приложения
 * @param titleId id строки из ресурсов, которая будет выведена в панели
 * @param icon иконка, для отображения слева
 * @param onClickIcon действие по нажатию на иконку
 */
@Composable
fun PackageWalkTopBar(@StringRes titleId: Int?, icon: ImageVector?, onClickIcon: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen.height_top_bar)),
        contentAlignment = Alignment.CenterStart
    ) {
        icon?.let {
            IconButton(onClick = { onClickIcon() }) {
                Icon(imageVector = icon, contentDescription = "")
            }
        }
        titleId?.let {
            TextH6(
                stringId = R.string.registration,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}